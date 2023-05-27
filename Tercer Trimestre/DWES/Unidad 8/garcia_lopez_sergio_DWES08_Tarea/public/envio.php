<?php
// envio.php se corresponde con la página que se encarga de crear un envío
//Primero comprobamos que se nos haya facilitado en la url de la petición get el id de la lista de tareas en la que vamos a insertar la nueva tarea
if (!isset($_GET['id'])) {
    header('Location: repartos.php');
    die();
}

require(__DIR__ . '/../src/Tools.php');
require(__DIR__ . '/../src/Producto.php');
require(__DIR__ . '/../vendor/autoload.php');

use Jaxon\Jaxon;
use function Jaxon\jaxon;
use Jaxon\Response\Response;

//cargamos los productos de la base de datos haciendo uso de la Clase Producto
$lista = new Producto();
$stmt  = $lista->listadoProductos();
$lista = null;
//Capturamos el id de la lista de tareas
$id    = $_GET['id'];

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
    <title>Apartado 4-3</title>
    <!-- Importamos las funciones javascript del archivo funciones.js -->
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
                    <!-- Formulario para crear un nuevo envío. Cuando se hace submit nos lleva a la página principal repartos.php, pero antes hace uso de la función semaforo() de funciones.js que es una función que valida que la latitud no esté vacía y que haya un producto elegido -->
                    <form name="f1" method='POST' action='repartos.php' onsubmit="return semaforo();">
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="width:2.3rem;"><i class="fas fa-city"></i></span>
                            </div>
                            <!-- Input en el que vamos a escribir una dirección de nuestro envío -->
                            <input type="text" class="form-control" placeholder="Dirección" id='dir' name='dir' required>

                        </div>
                        <div class="form-group mt-1">
                            <!-- Botón que al hacer click llama la función getCoordenadas() de funciones.js que nos va a devolver las coordenadas de la dirección que hemos escrito en el input anterior -->
                            <button class="btn btn-info mr-2" id="vDireccion" onclick="getCoordenadas();">Ver Coordenadas</button>
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="width:2.3rem;"><i class="fas fa-map-marker-alt"></i></span>
                            </div>
                            <!-- Input para mostrar la latitud que nos devuelve la función getCoordenadas() de funciones.js -->
                            <input type="text" class="form-control" placeholder="Latitud" id='lat' required name='lat' readonly>

                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="width:2.3rem;"><i class="fas fa-map-marker-alt"></i></span>
                            </div>
                            <!-- Input para mostrar la longitud que nos devuelve la función getCoordenadas() de funciones.js -->
                            <input type="text" class="form-control" placeholder="longitud" id='lon' name='lon' required readonly>
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="width:2.3rem;"><i class="fa fa-info"></i></span>
                            </div>
                            <!-- Input para mostrar la altitud que nos devuelve la función getCoordenadas() de funciones.js -->
                            <input type="text" class="form-control" placeholder="Altitud" id='alt' name='alt' readonly>
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text" style="width:2.3rem;"><i class="fas fa-box-open"></i></span>
                            </div>
                            <!-- Select para elegir el producto que vamos a enviar -->
                            <select id='pro' name="pro" class='form-control'>
                                <?php
                                echo "<option value='-1'>Elige un producto</option>";
                                while ($fila = $stmt->fetch(PDO::FETCH_OBJ)) {
                                    echo "<option>" . $fila->nombre . "</option>";
                                }
                                ?>
                            </select>
                        </div>
                        <div class="form-group">
                            <!-- Input oculto para enviar el id de la lista de tareas -->
                            <input type="hidden" name="idLTarea" value="<?php echo $id; ?>">
                            <!-- Botón de submit para enviar el formulario que llama a la función semaforo() de funciones.js que es una función que valida que la latitud no esté vacía y que haya un producto elegido. Cuando damos a enviar mandamos toda la info de los inputs ocultos por post a reparto.php -->
                            <button type='submit' class="btn btn-info mr-2" id="enviar" onclick="return semaforo();">Crear Envio</button>
                            <!-- Botón para volver a la página principal repartos.php -->
                            <a href="repartos.php" class="btn btn-success">Volver</a>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<?php
//  Injectamos los scripts javascript antes de enviar la página:
$jaxon = jaxon();
echo $jaxon->getCss(), "\n", $jaxon->getJs(), "\n", $jaxon->getScript(), "\n";
echo "<!-- HTTP comment  -->\n"
?>
</html>