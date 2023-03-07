<?php require_once("conexion.php");?>
<!DOCTYPE html>
<html lang="en">
    
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="	https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <title>CRUD</title>

  </head>

  <body>

    <div class="container">
        <h3 class="text-center">Detalle producto</h3>

        <?php

            if(!isset($_GET['id'])){               //Si no se ha enviado ningún id regreso al listado
                header("Location: listado.php");
            }else{

                $id = $_GET['id'];

                $sql = "SELECT * FROM PRODUCTOS WHERE id = $id";

                $detalle = $conProyecto->query($sql);
                $detalle->execute();
                $producto = $detalle->fetch(PDO::FETCH_OBJ);

                if($producto == null){   //si no existe ningún producto con ese id
                    header("Location: listado.php");
                }

            }

        ?>

        <div class="card mt-3">
            <div class="text-center card-header">
                <?php echo $producto->nombre; ?>
            </div>
            <div class="card-body">
                <p class="card-text">
                    <p>Nombre: <?php echo $producto->nombre; ?></p>
                    <p>Nombre corto: <?php echo $producto->nombre_corto; ?></p>
                    <p>Código familia: <?php echo $producto->familia;?></p>
                    <p> PVP (€): <?php echo $producto->pvp; ?></p>
                    <p>Descripción: <?php echo $producto->descripcion; ?></p>
                </p>
            </div>
        </div>

        <div class="mt-2 text-center">
            <form action="listado.php">
                <input type="submit" class="btn btn-secondary" value="Volver">
            </form>
        </div>

    </div>


    <style type="text/css">
        body{
            background-color:#cebfd7;
            margin: 2%;
        }
    </style>
 </body>

</html>