<?php
// 1) Hacemos autoload para usar Jaxon y OJO REQUIRE DE DATOS.PHP, PORQUE ES DONDE ESTAN LAS FUNCIONES!!!! EN LOS FORMULARIOS ES IGUAL
require(__DIR__ . '/includes/Datos.php');
require(__DIR__ . '/vendor/autoload.php');

// 2) Namespaces
use Jaxon\Jaxon;
use function Jaxon\jaxon;
use Jaxon\Response\Response;

// ****** ESTO NO HACE FALTA, YA LO TENEMOS ESTABLECIDO EN EL FICHERO 'DATOS.PHP', QUE ES DONDE ESTÁN LAS FUNCIONES PARA OBTENER DATOS ******
/*$jaxon = jaxon();

$jaxon->setOption('js.app.minify', false);
$jaxon->setOption('core.decode_utf8', true);
$jaxon->setOption('core.debug.on', false);
$jaxon->setOption('core.debug.verbose', false);

if($jaxon->canProcessRequest())  $jaxon->processRequest(); */

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
    <script type="text/javascript" src="funciones.js"></script>

</head>

<body style="background:#00bfa5;">

    <div class="container mt-3">
        <div class="d-flex justify-content-center h-100">
            <div class="card" style='width:28rem;'>
                <div class="card-header">
                    <h3><i class="fa fa-cog mr-1"></i>Api REST</h3>
                </div>
                <div class="card-body">

                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-map-marker-alt"></i></span>
                        </div>
                        <input type="number" class="form-control" placeholder="Latitud" id='lat' step='0.000001' required>

                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-map-marker-alt"></i></span>
                        </div>
                        <input type="number" class="form-control" placeholder="longitud" id='lon' step="0.000001" required>
                    </div>
                    <div class="form-group">
                        <!-- Aqui no usamos el 'RETURN' como en las validaciones de usuario, porque no tenemos que esperar respuesta del servidor-->
                        <!-- Aqui invocamos las funciones del fichero JavaScript en solicitud *** ASINCRONA ***-->
                        <button class="btn btn-info mr-2" id="vDireccion" onclick="getLocalizacion()">Ver Direccion</button>
                        <button class="btn btn-success" id="vTiempo" onclick="getTiempo()">Ver Tiempo</button>
                    </div>

                </div>
            </div>
        </div>
        <div class="d-flex justify-content-center h-100 mt-2" id="datos">
            <div class="card" style='width:28rem;'>
                <div class="card-body">

                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" style="width:2.5rem;"><i class="fas fa-map-pin"></i></span>
                        </div>
                        <input type="text" class="form-control" placeholder="Dirección" id='dir' readonly>

                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" style="width:2.5rem;"><i class="fas fa-city"></i></span>
                        </div>
                        <input type="text" class="form-control" placeholder="Ciudad" id="ciu" readonly>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" style="width:2.5rem;"><i class="fas fa-globe"></i></span>
                        </div>
                        <input type="text" class="form-control" placeholder="País" id="pai" readonly>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" style="width:2.5rem;"><i class="fas fa-umbrella"></i></span>
                        </div>
                        <input type="text" class="form-control" placeholder="Tiempo" id="tie" readonly>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" style="width:2.5rem;"><i class="fas fa-thermometer-three-quarters"></i></span>
                        </div>
                        <input type="text" class="form-control" placeholder="Temperatura" id="tem" readonly>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" style="width:2.5rem;"><i class="fas fa-tint"></i></span>
                        </div>
                        <input type="text" class="form-control" placeholder="Humedad (%)" id="hum" readonly>
                    </div>

                </div>
            </div>
        </div>
    </div>

</body>
<?php
//  Inyectamos los scripts javascript como siempre antes de enviar la página:
$jaxon = jaxon();
echo $jaxon->getCss(), "\n", $jaxon->getJs(), "\n", $jaxon->getScript(), "\n";
echo "<!-- HTTP comment  -->\n"
?>

</html>