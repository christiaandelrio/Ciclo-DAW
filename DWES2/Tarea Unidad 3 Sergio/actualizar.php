<!-- incluir el head del archivo head.php-->
<?php include 'head.php'; ?>

<body class="bg-info">
    <div class="container">
        <h3 class="text-center">Editar Producto</h1>

        <?php
            //Conexion a la base de datos
            require_once('conexion.php');

            //ESTA PARTE ES PARA MODIFICAR LOS DATOS DEL PRODUCTO
            //Si se ha pulsado el botón de modificar
            if(isset($_POST['modificar'])){

                //Recogemos los datos del formulario
                $id = htmlspecialchars($_POST['id']);
                $nombre = htmlspecialchars($_POST['nombre']);
                $nombreCorto = htmlspecialchars($_POST['nombre-corto']);
                $precio = htmlspecialchars($_POST['precio']);
                $familia = htmlspecialchars($_POST['familia']);
                $descripcion = htmlspecialchars($_POST['descripcion']);

                //Comprobamos que no estén vacíos
                if(empty($nombre) || empty($nombreCorto) || empty($precio) || empty($familia) || empty($descripcion)){
                    echo "<div class='alert alert-danger' role='alert'>Todos los campos son obligatorios</div>";                 
                }else{
                    try {
                        //Consulta a la base de datos. Actualizamos el producto
                        $sql = "UPDATE PRODUCTOS SET nombre = '$nombre', nombre_corto = '$nombreCorto', descripcion = '$descripcion', pvp = $precio, familia = '$familia' WHERE id = $id";
                        //Ejecutamos la consulta
                        $insert = $conexion->query($sql);
                        //Redirigimos a la página de listado
                        header('Location: listado.php');
                    } catch(PDOException $e){
                        echo "<div class='alert alert-danger' role='alert'>Error: " . $e->getMessage() . "</div>";
                    }
                }
            }

            //ESTA PARTE ES PARA MOSTRAR LOS DATOS DEL PRODUCTO
            // Si no viene el id o se ha pulsado el botón volver, redirigimos a la página de listado
            if(!isset($_GET['id']) || isset($_POST['volver'])){
                header("Location: listado.php");
            }else{
                try {
                    //Si viene el id tomamos los datos del producto
                    $id = $_GET['id'];
                    //Consulta a la base de datos
                    $sql = "SELECT * FROM PRODUCTOS WHERE id = $id";
                    //Ejecutamos la consulta
                    $result = $conexion->query($sql);
                    //Guardamos el resultado en un objeto
                    $producto = $result->fetch(PDO::FETCH_OBJ);
                    //Si no existe el producto, redirigimos a la página de listado
                    if($producto == null){
                        header("Location: listado.php");
                    }
                } catch(PDOException $e){
                    echo "<div class='alert alert-danger' role='alert'>Error: " . $e->getMessage() . "</div>";
                }
            }
        ?>

        <form method="POST">

            <!-- Campo oculto para enviar el id del producto -->
            <input type="hidden" name="id" value="<?php echo $producto->id; ?>">

            <!-- Inputs Importante: En el value vamos a poner la info que traemos de la base de datos-->
            <div class="row">
                <div class="col-6">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" id="nombre" value="<?php echo $producto->nombre; ?>"/>
                </div>
                <div class="col-6">
                    <label for="nombre-corto">Nombre corto</label>
                    <input type="text" class="form-control" name="nombre-corto" id="nombre-corto"  value="<?php echo $producto->nombre_corto; ?>"/>
                </div>
            </div>

            <div class="row">
                <div class="col-6">
                    <label for="precio">Precio (€)</label>
                    <input type="text" class="form-control" name="precio" id="precio"  value="<?php echo $producto->pvp; ?>"/>
                </div>
                <div class="col-6">
                    <label for="familia">Familia</label>
                    <select class="form-control" name="familia" id="familia">
                        <!-- Tenemos que traer el listado de opciones de familia para el select -->
                        <?php
                            $result = $conexion->query("SELECT * FROM familias");
                            
                            while($row=$result->fetch(PDO::FETCH_OBJ)){
                                if($row->cod == $producto->familia){
                                    // Como opción seleccionada ponemos la que traemos de la base de datos
                                    echo "<option selected value='$row->cod'>$row->nombre</option>";
                                }else{
                                    echo "<option value='$row->cod'>$row->nombre</option>";
                                }
                            }
                        ?>
                    </select>
                </div>
            </div>

            <div class="row mb-2">
                <div class="col-12">
                    <label for="descripcion">Descripción</label>
                    <textarea class="form-control" name="descripcion" id="descripcion" cols="100" rows="10"><?php echo $producto->descripcion; ?></textarea>
                </div>
            </div>

            <input type="submit" class="btn btn-primary" name="modificar" value="Modificar">
            <input type="submit" class="btn btn-light" name="volver" value="Volver">

        </form>

        </div>
</body>
</html>