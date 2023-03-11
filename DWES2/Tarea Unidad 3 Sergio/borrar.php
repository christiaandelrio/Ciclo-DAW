<!-- incluir el head del archivo head.php-->
<?php include 'head.php'; ?>

<body>
    <?php
        // Conexión a la base de datos
        require_once('conexion.php');

        // Si viene el id por POST, borramos el producto
        if(isset($_POST['id'])){
            try {
                //Recogemos el id del producto que queremos borrar
                $id = $_POST['id'];
                //Preparamos la consulta
                $sql = "DELETE FROM productos WHERE id = $id";
                //Ejecutamos la consulta
                $delete = $conexion->query($sql);
            } catch(PDOException $e){
                echo "<div class='alert alert-danger' role='alert'>Error: " . $e->getMessage() . "</div>";
            }
        }else{
            // Si no viene el id redirigimos a la página de listado
            header("Location: listado.php");
        }
    
    ?>

    <!-- Mensaje que se mostrará cuando se borre el elemento -->
    <div class="container mt-4 col-6">
        <!-- Mensaje de borrado -->
        <div class='alert alert-success' role='alert'>Producto de codigo: <?php echo $id ?> Borrado correctamente</div>
        <!-- Botón para regresar al listado tras borrar el producto -->
        <form action="listado.php">
            <button class="btn btn-primary col-2">Volver</button>
        </form>
    </div>

</body>
</html>