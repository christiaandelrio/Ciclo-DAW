<!-- incluir el head del archivo head.php-->
<?php include 'head.php'; ?>

<body class="bg-info">
    <div class="container">
        <h3 class="text-center">Crear Producto</h1>

        <?php
            //Conexion a la base de datos
            require_once('conexion.php');

            //Si se ha pulsado el botón de crear
            if(isset($_POST['crear'])){
                $nombre = htmlspecialchars($_POST['nombre']);
                $nombreCorto = htmlspecialchars($_POST['nombre-corto']);
                $precio = htmlspecialchars($_POST['precio']);
                $familia = htmlspecialchars($_POST['familia']);
                $descripcion = htmlspecialchars($_POST['descripcion']);

                if(empty($nombre) || empty($nombreCorto) || empty($precio) || empty($familia) || empty($descripcion)){
                    echo "<div class='alert alert-danger' role='alert'>Todos los campos son obligatorios</div>";                 
                }else{
                    try{
                        //Consulta a la base de datos. Insertamos el producto
                        $sql = "INSERT INTO PRODUCTOS (nombre, nombre_corto, descripcion, pvp, familia) VALUES('$nombre', '$nombreCorto', '$descripcion', $precio, '$familia')";
                        
                        //Forma 1 de insertar
                        //$insert = $conexion->query($sql);
                        
                        //Forma 2 de insertar
                        $insert = $conexion->prepare($sql);
                        $insert->execute();
        
                        //Redirigimos a la página de listado
                        header('Location: listado.php');

                    }catch(PDOException $e){
                        echo "<div class='alert alert-danger' role='alert'>Error: " . $e->getMessage() . "</div>";
                    }
                }
            //Si se ha pulsado el botón de volver
            }else if(isset($_POST['volver'])){
                //Redirigimos a la página de listado
                header('Location: listado.php');
            }
        ?>

        <!-- Formulario -->

        <form method="POST">
            <div class="row">
                <div class="col-6">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" />
                </div>
                <div class="col-6">
                    <label for="nombre-corto">Nombre Corto</label>
                    <input type="text" class="form-control" name="nombre-corto" id="nombre-corto" />
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <label for="precio">Precio (€)</label>
                    <input type="text" class="form-control" name="precio" id="precio" />
                </div>
                <div class="col-6">
                    <label for="familia">Familia</label>
                    <select name="familia" id="familia" class="form-control">
                        <?php
                            //Consulta a la base de datos.
                            $query=$conexion->query('SELECT * FROM familias');
                            //Utilizamos la consulta para obtener un array de objetos con todas las familias
                            $familias=$query->fetchAll(PDO::FETCH_OBJ);

                            //Recorrer los datos del array de objetos
                            foreach($familias as $familia){
                                echo"<option value='$familia->cod'>$familia->nombre</option>";
                            }
                        ?>
                    </select>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col-12">
                    <label for="descripcion">Descripcion</label>
                    <textarea class="form-control" name="descripcion" id="" cols="100" rows="10"></textarea>
                </div>
            </div>
            <input type="submit" class="btn btn-primary" name="crear" value="Crear">
            <input type="reset" class="btn btn-success" value="Limpiar">
            <input type="submit" class="btn btn-light" name="volver" value="Volver">
        </form>
    </div>
</body>
</html>