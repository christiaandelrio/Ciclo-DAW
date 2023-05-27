<?php
// DOCUMENTACIÓN DE LA API EN:
// Learn -> Bing Maps -> Bing Maps V8 Web Control
// https://learn.microsoft.com/en-us/bingmaps/v8-web-control/?toc=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2Fbingmaps%2Fv8-web-control%2Ftoc.json&bc=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2FBingMaps%2Fbreadcrumb%2Ftoc.json

//A mapas.php llegamos cuando pulsamos en reparto.php el botón de "mapa" de una tarea (punto de reparto). La url recibida tiene los parámetros get lat y lon, que son las coordenadas del punto de reparto.
//Comprobamos que nos haya llegado la latitud por get (si no es así redirigimos a repartos.php)
if (!isset($_GET['lat'])) {
    header('Location: repartos.php');
    die();
}

include("../../claves.inc.php");
$urlBingMaps = 'https://www.bing.com/api/maps/mapcontrol?key='. $keyBing;

?>

<!DOCTYPE html>
<html>

<head>
    <title>Mapa</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
    <!-- Importamos el script de la API de Bing Maps pasándole la url al src "https://www.bing.com/api/maps/mapcontrol?key=miAPIkey" -->
    <script type='text/javascript' src=<?php echo '"' . $urlBingMaps . '"'?>></script> <!-- OCULTAR -->
    <script type='text/javascript'>
        //Esta funcion coge de la url los parámetros del get, en este caso lat y lon y devuelve su valor
        function getParameterByName(name) {
            name        = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            let regex   = new RegExp("[\\?&]" + name + "=([^&#]*)");
            let results = regex.exec(location.search);

            return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }

        let map;
        let lat = getParameterByName('lat');
        let lon = getParameterByName('lon');

        //Función para obtener un mapa de Bing Maps centrado en las coordenadas recibidas por get
        function loadMapScenario() {
            map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
                center: new Microsoft.Maps.Location(lat, lon),
                mapTypeId: Microsoft.Maps.MapTypeId.canvasLight,
                zoom: 17
            });
        }
    </script>
</head>

<!-- Dibujamos el mapa. Al cargar el body se llama a la función loadMapScenario() definida con anterioridad -->
<body onload='loadMapScenario();' style="background:#00bfa5;">
    <div class="container mt-3 ">
        <div class="d-flex justify-content-center">
            <div id='myMap' style='width: 650px; height: 420px;'></div>
            <div class="mt-r">

            </div>
        </div>
        <div class="d-flex justify-content-center mt-3">
            <!-- Botón para volver a repartos.php -->
            <a href='repartos.php' class='btn btn-warning'>Volver</a>
        </div>
    </div>
</body>

</html>
