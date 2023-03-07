<?php require_once("conexion.php");?>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="	https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <title>Crear</title>

    <style type="text/css">
        body{
            background-color:#cebfd7;
            margin: 2%;
        }
    </style>
  </head>

  <body>
        <?php 
            if(!empty($_GET["crear"])){

                $nombre = $_GET["nombre"];
                $nombre_corto = $_GET["nombre_corto"];
                $precio = $_GET["precio"];
                $familia = $_GET["familia"];
                $descripcion = $_GET["descripcion"];
                
                $sql = "INSERT INTO `productos`(`nombre`, `nombre_corto`, `descripcion`, `pvp`, `familia`) VALUES ('$nombre','$nombre_corto','$descripcion','$precio','$familia')";
                        
                $crear = $conProyecto->query($sql);

                header('Location:listado.php');

            }elseif(isset($_GET['volver'])){

                header('Location:listado.php');

            }
        ?>
        
        <form method="get">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" name="nombre" class="form-control" id="nombre">
                </div>
                <div class="mb-3">
                    <label for="nombre_corto" class="form-label">Nombre Corto</label>
                    <input type="text" name="nombre_corto" class="form-control" id="nombre_corto">
                </div>

                <div class="mb-3">
                    <label for="precio" class="form-label">Precio(€)</label>
                    <input type="text" name="precio" class="form-control" id="precio">
                </div>
                
                <div class="mb-3">
                    <label for="familia" name="familia" class="form-label">Familia</label>
                    <select id="familia" name="familia">
                    <?php 
                        $consulta = $conProyecto->query("SELECT * FROM familias");

                        $fila = $consulta -> fetchAll(PDO::FETCH_ASSOC);
                    
                    foreach($fila as $row){ ?>
                        <option value="<?php echo $row["cod"]?>"><?php echo $row['nombre']; }?></option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="descripcion">Descripción</label>
                    <textarea name="descripcion" class="form-control" placeholder="Descripción del producto" id="descripcion"></textarea>
                </div>

                <input type="submit"  class="btn btn-primary" name="crear" value="Crear"/>
                <input type="reset" class="btn btn-success" value="Limpiar"/>
                <input type="submit" class="btn btn-secondary" name="volver" value="Volver"/>
            </div>
            </div>
        </form>

   </body>

</html>