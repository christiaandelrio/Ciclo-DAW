<?php
// DOCUMENTACIÓN DE LA API EN:
// Learn -> Bing Maps -> Bing Maps V8 Web Control
// https://learn.microsoft.com/en-us/bingmaps/v8-web-control/?toc=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2Fbingmaps%2Fv8-web-control%2Ftoc.json&bc=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2FBingMaps%2Fbreadcrumb%2Ftoc.json

//A rutas.php llegamos desde repartos.php, tras pulsar el botón de "Ver Ruta en Mapa", que a su vez se mostrará tras dar pulsar "ordenar" en una lista de tareas (reparto)
//Comprobamos que nos haya llegado por post 'wp" (waypoints), en caso contrario redirigimos a repartos.php
if (!isset($_POST['wp'])) {
    header('Location: repartos.php');
    die();
}
//Capturamos el valor de wp (waypoints) que nos llega por post
$wp = $_POST['wp'];
//Importamos las claves para poder utilizar Bing Maps
include("../../claves.inc.php");
?>
<!DOCTYPE html>
<html>

<head>
    <title>Ruta de Reparto</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <meta charset="utf-8" />
    <script type='text/javascript'>

        //Función para descomponer la url y obtener sus parámetros
        function getParameterByName(name) {
            name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            let regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
            return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }

        let map, directionsManager;

        //Función para obtener el mapa con la ruta de reparto
        function GetMap() {
            //Inicializamos el mapa con las credenciales (API key) de Bing Maps
            map = new Microsoft.Maps.Map('#myMap', {
                credentials: "<?php echo $keyBing; ?>"
            });

            //Cargamos el módulo de direcciones de Bing Maps
            Microsoft.Maps.loadModule('Microsoft.Maps.Directions', function() {
                //Creamos una instancia del manejador (manager) de direcciones
                directionsManager = new Microsoft.Maps.Directions.DirectionsManager(map);

                <?php
                for ($i = 0; $i < count($wp); $i++) {
                    //Añadimos los puntos a la ruta, incluidos los del almacen
                    echo  "directionsManager.addWaypoint(new Microsoft.Maps.Directions.Waypoint({ location: new Microsoft.Maps.Location($wp[$i]) }));\n";
                }
                ?>

                //Definimos las opciones de la respuesta (evitar autopistas y usar kilómetros)
                directionsManager.setRequestOptions({
                    distanceUnit: Microsoft.Maps.Directions.DistanceUnit.km,
                    routeAvoidance: [Microsoft.Maps.Directions.RouteAvoidance.avoidLimitedAccessHighway]
                });

                //Hacer que la línea de la ruta se vea de color verde y con un grosor de tamaño 6. Sustituimos el título de los puntos de ruta por una cadena vacía para ocultar el texto predeterminado que aparece.
                directionsManager.setRenderOptions({
                    drivingPolylineOptions: {
                        strokeColor: 'green',
                        strokeThickness: 6
                    },
                    waypointPushpinOptions: {
                        title: ''
                    }
                });

                //Calculamos las direcciones.
                directionsManager.calculateDirections();
            });
        }
    </script>
    <script type='text/javascript' src='https://www.bing.com/api/maps/mapcontrol?callback=GetMap' async defer></script>
</head>

<body style="background:#00bfa5;">
    <div class="container mt-3 ">
        <!-- div en el que vamos a dibujar el mapa con la ruta -->
        <div class="d-flex justify-content-center">
            <div id="myMap" style="width:650px;height:420px;"></div>
        </div>
        <!-- Botón para volver a repartos.php -->
        <div class="d-flex justify-content-center mt-3">
            <a href='repartos.php' class='btn btn-warning'>Volver</a>
        </div>
    </div>
</body>

</html>
