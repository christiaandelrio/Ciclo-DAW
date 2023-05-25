<?php

// En este fichero implementamos el formulario para crearnos un nuevo envío

// 1) Verificacion para comprobar si existe el id de la lista de tareas pasado por GET
if (!isset($_GET['id'])) {
    header('Location: repartos.php');
    die();
}
// 2) Hacemos el require de los ficheros necesarios (para el jaxon, para listar los productos, Tools para obtener coordenadas)
require(__DIR__ . '/../src/Tools.php');
require(__DIR__ . '/../src/Producto.php');
require(__DIR__ . '/../vendor/autoload.php');
// 3) Preparamos Jaxon
use Jaxon\Jaxon;
use function Jaxon\jaxon;
use Jaxon\Response\Response;

// 4) cargamos los productos
$lista = new Producto();
$stmt  = $lista->listadoProductos();
$lista = null;
// 5) Guardamos en una variable el id de la lista de tareas
$id = $_GET['id'];

$jaxon = jaxon();

// Podemos incluir las opciones que queramos
$jaxon->setOption('js.app.minify', false);
$jaxon->setOption('core.decode_utf8', true);
$jaxon->setOption('core.debug.on', false);
$jaxon->setOption('core.debug.verbose', false);

if($jaxon->canProcessRequest())  $jaxon->processRequest();

?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <title>Envios</title>
    <script type="text/javascript" src="../js/funciones.js"></script>
</head>

<body style="background:#00bfa5;">
    <div class="container mt-3">
        <div class="d-flex justify-content-center h-100">
            <div class="card" style='width:34rem;'>
                <div class="card-header">
                    <h3><i class="fas fa-cart-plus mr-2"></i>Crear Envio</h3>
                </div>
                <div class="card-body">
                    <!-- 6) Creamos un formulario con action a la pagina de repartos, y onsubmit para invocar la funcion 'semaforo' de validaciones-->
                    <form name="f1" method='POST' action='repartos.php' onsubmit="return semaforo();">
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="width:2.3rem;"><i class="fas fa-city"></i></span>
                            </div>
                            <input type="text" class="form-control" placeholder="Dirección" id='dir' name='dir' required>

                        </div>
                        <div class="form-group mt-1">
                            <!-- 7) Llamamos con el 'onclick' al método para obtener las coordenadas-->
                            <button class="btn btn-info mr-2" id="vDireccion" onclick="getCoordenadas();">Ver Coordenadas</button>
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="width:2.3rem;"><i class="fas fa-map-marker-alt"></i></span>
                            </div>
                            <input type="text" class="form-control" placeholder="Latitud" id='lat' required name='lat' readonly>

                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="width:2.3rem;"><i class="fas fa-map-marker-alt"></i></span>
                            </div>
                            <input type="text" class="form-control" placeholder="longitud" id='lon' name='lon' required readonly>
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="width:2.3rem;"><i class="fa fa-info"></i></span>
                            </div>
                            <input type="text" class="form-control" placeholder="Altitud" id='alt' name='alt' readonly>
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="width:2.3rem;"><i class="fas fa-box-open"></i></span>
                            </div>
                            <select id='pro' name="pro" class='form-control'>
                                <?php
                                // 8) Hacemos un desplegable de los productos
                                echo "<option value='-1'>Elige un producto</option>";
                                while ($fila = $stmt->fetch(PDO::FETCH_OBJ)) {
                                    echo "<option>" . $fila->nombre . "</option>";
                                }
                                ?>
                            </select>
                        </div>
                        <div class="form-group">
                            <!-- 9) Almacenamos en un campo oculto el valor del 'id'-->
                            <input type="hidden" name="idLTarea" value="<?php echo $id; ?>">
                            <!-- 10) Boton para enviar, con atributo 'onclick' para realizar validaciones-->
                            <button type='submit' class="btn btn-info mr-2" id="enviar" onclick="return semaforo();">Crear Envio</button>
                            <a href="repartos.php" class="btn btn-success">Volver</a>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<?php
// 11) Inyectamos los scripts javascript antes de enviar la página:
$jaxon = jaxon();
echo $jaxon->getCss(), "\n", $jaxon->getJs(), "\n", $jaxon->getScript(), "\n";
echo "<!-- HTTP comment  -->\n"
?>
</html>