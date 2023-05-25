<?php
// DOCUMENTACIÓN DE LA API EN:
// Learn -> Bing Maps -> Bing Maps V8 Web Control
// https://learn.microsoft.com/en-us/bingmaps/v8-web-control/?toc=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2Fbingmaps%2Fv8-web-control%2Ftoc.json&bc=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2FBingMaps%2Fbreadcrumb%2Ftoc.json

// Con este fichero, mostramos en el mapa la dirección del envío

// 1) Realizamos la comprobación de si hemos recibido el parámetro 'lat' en la URL. Si no, volvemos a repartos
if (!isset($_GET['lat'])) {
    header('Location: repartos.php');
    die();
}
// 2) Incluimos el fichero donde tenemos la clave para bing
include("../claves.inc.php");
// 3) Construimos la URL de la API
$urlBingMaps = 'https://www.bing.com/api/maps/mapcontrol?key='. $keyBing;

?>

<!DOCTYPE html>
<html>

<head>
    <title>Mapa</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
    <!-- 4) Incluimos el Script de la API de BING MAPS (VER DOCUMENTACION DE LA API, ENLACE ARRIBA -->
    <script type='text/javascript' src=<?php echo '"' . $urlBingMaps . '"'?>></script> <!-- OCULTAR -->
    <script type='text/javascript'>
        // 5) Definimos una funcion para coger de la url los parámetros get, en este caso lat y lon y devolver su valor
        function getParameterByName(name) {
            // Utilizamos expresiones regulares para ello
            name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            let regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
            // Utilizamos el método 'exec()' de la expresión regular para buscar el valor del parámetro de la cadena de consulta de la URL
            let results = regex.exec(location.search);
            // Devolvemos el valor del parámetro si lo hemos encontrado, si no, una cadena vacia
            return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }
        // 6) Declaramos una variable 'map' y las variables para latitud y longitud usando la funcion anterior
        var map;
        var lat = getParameterByName('lat');
        var lon = getParameterByName('lon');
        // 7) Declaramos una nueva función para crear un objeto de Mapa de Bing Maps
        function loadMapScenario() {
            // El mapa se crea en el elemento HTML con id 'myMap'
            map = new Microsoft.Maps.Map(document.getElementById('myMap'), {
                // El centro del mapa se establece según las coordenadas que le pasamos
                center: new Microsoft.Maps.Location(lat, lon),
                mapTypeId: Microsoft.Maps.MapTypeId.canvasLight,
                // Zoom a 17
                zoom: 17
            });
        }
    </script>
</head>
<!-- Con el atributo onlaod invocamos la función de cargar el mapa-->
<body onload='loadMapScenario();' style="background:#00bfa5;">
    <div class="container mt-3 ">
        <div class="d-flex justify-content-center">
            <!-- Contenedor para mostrar el mapa-->
            <div id='myMap' style='width: 650px; height: 420px;'></div>
            <div class="mt-r">

            </div>
        </div>
        <div class="d-flex justify-content-center mt-3">
            <!-- Mostramos un boton para volver a repartos (Pagina principal) -->
            <a href='repartos.php' class='btn btn-warning'>Volver</a>
        </div>
    </div>
</body>

</html>
