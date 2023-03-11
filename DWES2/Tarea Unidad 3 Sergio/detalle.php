<!-- incluir el head del archivo head.php-->
<?php include 'head.php'; ?>

<body class="bg-info">
    <div class="container">
        <h3 class="text-center">Detalle de producto</h1>

        <?php
            // Si no viene el id por GET, redirigimos a la página de listado
            if(!isset($_GET['id'])){
                header("Location: listado.php");
            }else{
                //Traemos la conexión a la base de datos
                require_once('conexion.php');

                try {
                    //Recogemos el id del producto que queremos mostrar
                    $id = $_GET['id'];
                    //Preparamos la consulta
                    $sql = "SELECT * FROM PRODUCTOS WHERE id = $id";
                    //El método query de PDO nos devuelve un objeto PDOStatement, una sentencia preparada (sirve para evitar inyecciones SQL y mejorar el rendimiento de la aplicación)
                    $query = $conexion->query($sql);
                    // Obtenemos el producto como un objeto
                    $producto = $query->fetch(PDO::FETCH_OBJ);
    
                    // Si el producto no existe, redirigimos a la página de listado
                    if($producto == null){
                        header("Location: listado.php");
                    }
                } catch(PDOException $e){
                    echo "<div class='alert alert-danger' role='alert'>Error: " . $e->getMessage() . "</div>";
                }
            }
        ?>

        <div class="card bg-info mt-4">
            <div class="card-header text-center ">
                <!-- Mostrar el nombre del producto -->
                <?php echo $producto->nombre; ?>
            </div>
            <div class="card-body">
                <p class="card-text">
                    <p>
                        <span class="font-weight-bold">Nombre: </span>
                        <!-- Mostrar el nombre del producto -->
                        <span><?php echo $producto->nombre; ?></span>
                    </p>
                    <p>
                        <span class="font-weight-bold">Nombre corto: </span>
                        <!-- Mostrar el nombre corto del producto -->
                        <span><?php echo $producto->nombre_corto; ?></span>
                    </p>
                    <p>
                        <span class="font-weight-bold">Código familia: </span>
                        <!-- Mostrar el código de familia del producto -->
                        <span><?php echo $producto->familia; ?></span>
                    </p>
                    <p>
                        <span class="font-weight-bold">PVP (€): </span>
                        <!-- Mostrar el nombre del producto -->
                        <span><?php echo $producto->pvp; ?></span>
                    </p>
                    <p>
                        <span class="font-weight-bold">Descipción: </span>
                        <!-- Mostrar el nombre del producto -->
                        <span><?php echo $producto->descripcion; ?></span>
                    </p>
                </p>
            </div>
        </div>
            <div class="mt-2 text-center">
                <form action="listado.php">
                    <input type="submit" class="btn btn-light" name="volver" value="Volver" />
                </form>
            </div>
    </div>
</body>
</html>