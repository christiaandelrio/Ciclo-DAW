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

            if(isset($_GET["actualizar"])){

                $id = $_GET['id'];
                $nombre = $_GET['nombre'];
                $nombre_corto = $_GET['nombre_corto'];
                $precio = $_GET['precio'];
                $familia = $_GET['familia'];
                $descripcion = $_GET['descripcion'];

                $sql = "UPDATE PRODUCTOS SET nombre = '$nombre', nombre_corto = '$nombre_corto', descripcion = '$descripcion', pvp = $precio, familia = '$familia' WHERE id = $id";

                $actualizar = $conProyecto->query($sql);

                header("Location: listado.php");

            }
            
            if(isset($_GET["volver"])||!isset($_GET["id"])){
                header("Location: listado.php");
            }else{
                $id = $_GET["id"];
                $sql = "SELECT * FROM productos WHERE id=$id";

                $resultado = $conProyecto->query($sql); 
            
                $resultado = $resultado->fetch(PDO::FETCH_OBJ); 
            }
        ?>
        
        <form method="get">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" name="nombre" class="form-control" id="nombre" value="<?php echo $resultado->nombre;?>">
                </div>
                <div class="mb-3">
                    <label for="nombre_corto" class="form-label">Nombre Corto</label>
                    <input type="text" name="nombre_corto" class="form-control" id="nombre_corto" value="<?php echo $resultado->nombre_corto;?>">
                </div>

                <div class="mb-3">
                    <label for="precio" class="form-label">Precio(€)</label>
                    <input type="text" name="precio" class="form-control" id="precio" value="<?php echo $resultado->pvp;?>">
                </div>
                
                <div class="mb-3">
                    <label for="familia" name="familia" class="form-label">Familia</label>
                    <select id="familia" name="familia">
                    <?php 
                        $consulta = $conProyecto->query("SELECT * FROM familias");

                        $fila = $consulta -> fetchAll(PDO::FETCH_ASSOC);
                    
                        foreach($fila as $row){ ?>
                            <?php 
                                if ($row["cod"] == $resultado->familia) {
                                    echo "<option value=" .$row["cod"]." selected>".$row["nombre"]."</option>";
                                } else {
                                    echo "<option value=" .$row["cod"].">".$row["nombre"]."</option>";
                                }
                            }?>

                        </select>
                    </select>
                </div>
                <input type="hidden" name="id" class="form-control" id="id" value="<?php echo $resultado->id;?>"> <!-- Envío el id como campo oculto para que al darle a actualizar lo pueda recoger-->


                <div class="mb-3">
                    <label for="descripcion">Descripción</label>
                    <textarea class="form-control" name="descripcion" id="descripcion" cols="100" rows="10"><?php echo $resultado->descripcion; ?></textarea>
                </div>

                <input type="submit" class="btn btn-primary" name="actualizar" value="Actualizar"/>
                <input type="reset" class="btn btn-success" value="Limpiar"/>
                <input type="submit" class="btn btn-secondary" name="volver" value="Volver"/>
            </div>
            </div>
        </form>

   </body>

</html>