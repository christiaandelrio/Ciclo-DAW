<?php require_once("conexion.php");?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="	https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <title>CRUD</title>
    <style type="text/css">
        body{
            background-color:#cebfd7;
            margin: 2%;
        }
    </style>
  </head>

  <body>

    <div class="container">
        <h3 class="text-center">Detalle producto</h3>

        <?php

            if(!isset($_GET['id'])){               //Si no se ha enviado ningún id regreso al listado
                header("Location: listado.php");
            }else{

                $id=$_GET["id"];

                
                $delete = $conProyecto->query("DELETE FROM productos WHERE id=$id");

            }

        ?>

            <div class="mb-3">
                <p>El producto de código <?php echo $id;?> ha sido borrado correctamente</p>
                <form action="listado.php" method="get">
                    <input type="submit" class="btn btn-secondary" name="volver" value="Volver"/>
                </form>
            </div>
    </body>
</html>