<!-- incluir el head del archivo head.php-->
<?php include 'head.php'; ?>

<body class="bg-info">
    <div class="container">
        <h3 class="text-center">Gestión de productos</h1>

        <!-- Botón para ir a la página de crear producto -->
        <form action="crear.php" class="mb-1">
            <button type="submit" class="btn btn-success">Crear producto</button>
        </form>

        <!-- Tabla para mostrar los productos -->
        <table class="table table-dark text-center">
            <thead>
                <tr>
                <th scope="col">Detalle</th>
                <th scope="col">Código</th>
                <th scope="col">Nombre</th>
                <th scope="col">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <?php
                    //Conexion a la base de datos
                    require_once('conexion.php');

                    try {             
                        //Creamos la consulta para traer los productos de la base de datos
                        $query = $conexion->query('SELECT * FROM productos');
                        //Utilizamos la consulta para obtener un array de objetos que contiene todos los productos
                        $productos = $query->fetchAll(PDO::FETCH_OBJ);
                        
                        //Recorremos el array de productos
                        foreach($productos as $producto){
                            ?>
                                <tr>
                                    <td>
                                        <!-- Botón para ir a la página de detalle producto. Tenemos que pasar el id en un campo oculto -->
                                        <form action="detalle.php" method="GET">
                                            <input type="hidden" name="id" value="<?php echo $producto->id; ?>">
                                            <button type="submit" class="btn btn-info">Detalle</button>
                                        </form>
                                    </td>
                                    <td>
                                        <!-- Mostrar id -->
                                        <?php echo $producto->id; ?>
                                    </td>
                                    <td>
                                        <!-- Mostrar nombre -->
                                        <?php echo $producto->nombre; ?>
                                    </td>
                                    <td>
                                        <!-- Botón para ir a la página de actualizar producto. Tenemos que pasar el id en un campo oculto -->
                                        <form class="d-inline-block" action="actualizar.php" class="mb-1" method="GET">
                                            <input type="hidden" name="id" value="<?php echo $producto->id; ?>">
                                            <button type="submit" class="btn btn-warning">Actualizar</button>
                                        </form>
                                        <!-- Botón para ir a la página de borrar producto. Tenemos que pasar el id en un campo oculto -->
                                        <form class="d-inline-block" action="borrar.php" class="mb-1" method="POST">
                                            <input type="hidden" name="id" value="<?php echo $producto->id; ?>">
                                            <button type="submit" class="btn btn-danger">Borrar</button>
                                        </form>
                                    </td>
                                </tr>
                            <?php
                        }
                    } catch(PDOException $e){
                        echo "<div class='alert alert-danger' role='alert'>Error: " . $e->getMessage() . "</div>";
                    }
                ?>
            </tbody>
        </table>
    </div>
</body>
</html>