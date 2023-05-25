<?php
// DOCUMENTACIÓN DE LA API EN:
// Learn -> Bing Maps -> Bing Maps V8 Web Control
// https://learn.microsoft.com/en-us/bingmaps/v8-web-control/?toc=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2Fbingmaps%2Fv8-web-control%2Ftoc.json&bc=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2FBingMaps%2Fbreadcrumb%2Ftoc.json

// Con este fichero, mostramos las rutas idóneas para el reparto

// 1) Realizamos la comprobación de si hemos enviado el formulario con la variable $_POST['wp']. Si no, volvemos a repartos
if (!isset($_POST['wp'])) {
    header('Location: repartos.php');
    die();
}
// 2) Almacenamos el valor en una variable
$wp = $_POST['wp'];
// 3) Incluimos el fichero donde esta la clave de Bing
include("../claves.inc.php");
?>
<!DOCTYPE html>
<html>

<head>
    <title>Ruta de Reparto</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta charset="utf-8" />
    <script type='text/javascript'>
        // 4) Definimos nuevamente una función para coger los parámetros necesarios. Es muy similar a la utilizada en el fichero mapas.php
        function getParameterByName(name) {
            name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
            var results = regex.exec(location.search);
            return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }

        // 5) Definimos dos variables para crear el mapa y gestionar las direcciones de la ruta
        var map;
        var directionsManager;

        // 6) Declaramos una nueva función para obtener el mapa con las rutas
        function GetMap() {
            // 7) Creamos instancia del objeto Map de Bing Maps, pasando el identificador del elemento HTML donde se mostrará el mapa y la clave 
            map = new Microsoft.Maps.Map('#myMap', {
                credentials: "<?php echo $keyBing; ?>"
            });

            // 8) Cargamos el modulo de direcciones de Bing Maps. Esto permite utilizar la funcionalidad de cálculo de direcciones
            Microsoft.Maps.loadModule('Microsoft.Maps.Directions', function() {
                // 9) Creamos instancia del objeto DirectionsManager
                directionsManager = new Microsoft.Maps.Directions.DirectionsManager(map);
                <?php
                for ($i = 0; $i < count($wp); $i++) {
                    // 10) Añadimos los puntos a la ruta, incluidos los del almacen, mediante la función 'addWayPoint()'
                    echo  "directionsManager.addWaypoint(new Microsoft.Maps.Directions.Waypoint({ location: new Microsoft.Maps.Location($wp[$i]) }));\n";
                }
                ?>

                // 11) Se configuran opciones de renderizado de la ruta, como el color y grosor de la línea de ruta, y título de puntos de paso
                directionsManager.setRequestOptions({
                    distanceUnit: Microsoft.Maps.Directions.DistanceUnit.km,
                    routeAvoidance: [Microsoft.Maps.Directions.RouteAvoidance.avoidLimitedAccessHighway]
                });

                directionsManager.setRenderOptions({
                    drivingPolylineOptions: {
                        strokeColor: 'green',
                        strokeThickness: 6
                    },
                    waypointPushpinOptions: {
                        title: ''
                    }
                });

                // 12) Finalmente, se calculan las direcciones
                directionsManager.calculateDirections();
            });
        }
    </script>
    <!-- 13) Se carga el script de la API de Bing Maps para rutas-->
    <script type='text/javascript' src='https://www.bing.com/api/maps/mapcontrol?callback=GetMap' async defer></script>
</head>

<body style="background:#00bfa5;">
    <div class="container mt-3 ">
        <div class="d-flex justify-content-center">
            <!-- Contenedor donde mostramos el mapa-->
            <div id="myMap" style="width:650px;height:420px;"></div>
        </div>
        <div class="d-flex justify-content-center mt-3">
            <!-- Boton para volver a repartos (Pagina principal) -->
            <a href='repartos.php' class='btn btn-warning'>Volver</a>
        </div>
    </div>
</body>

</html>
