<?php

// Fichero de apoyo a nuestra página principal, 'repartos.php'

// 1) Incluimos de nuevo los ficheros donde tenemos la ubicación de nuestro almacén, la solicitud de cliente a Google Tasks, y los métodos necesarios
include '../src/Tasks.php';
include '../src/config.inc.php';
include '../src/Tools.php';
require(__DIR__ . '/../vendor/autoload.php');
// 2) Instanciamos el objeto de Tasks
$service = new Google_Service_Tasks($client);

// 3)  Definimos las funciones para obtener las tareas y las notas, igual que en la clase principal
function getTareas($id) {
    global $service;

    $res1 = $service->tasks->listTasks($id);

    return $res1;
}

function getNotes($id, $titulo) {
    $tareas = getTareas($id);

    foreach ($tareas->getItems() as $tarea) {
        if ($tarea->getTitle() == $titulo) {
            return $tarea->getNotes();
        }
    }
}

// 4) Aqui tenemos ahora el 'case: oEnvios' que teniamos en el fichero principal

// 5) Almacenamos los valores de posicion y el id de la lista de las tareas
$apos = $_GET['pos'];
$id_lista = $_GET['idLt'];

// 6) Eliminamos la variable de sesion correspondiente al id de la lista de tareas
unset($_SESSION['idLt']);

// 7) Obtenemos todas las tareas de esta lista de tareas
$tareas = getTareas($id_lista);
// 8) Iteramos sobre el Array que contiene las posiciones de las tareas
foreach ($apos as $k => $v) {
    //los envios me los manda ordenados del 1 al n
    //en php los array empiezan por cero, por eso restamos 1 
    //asi el envio 1 pasa a ser el 0, el 2 el 1 ...
    $p = $v - 1;
    $arrayO[$k] = $tareas->getItems()[$p]->getTitle();
}

// 9) Aqui tendremos ahora el acceso a las rutas de cada lista de reparto

// 10) Definimos un formulario cuyo action nos llevará al fichero 'rutas.php'
$html = "<form name='1' action='rutas.php' method='POST'>";
// 11) Almacenamos en un campo oculto el primer waypoint, que será el almacen.
$html .= "<input type='hidden' name='wp[]' value=$corAlmacen>";
$html .= "<div class='container mt-2 mb-2' style='font-size:0.8rem'>";
// 12) Imprimimos una lista
$html .= "<ul class='list-group'>";
// 13) Iteramos sobre el array que definimos antes
foreach ($arrayO as $k => $v) {
    // 14) Por cada iteración, mostramos el elemento y su índice
    $html .= "<li class='list-group-item list-group-item-info'>" . ($k + 1) . ".- " . $v . "</li>\n";
    // 15) También agregamos un campo oculto adicional que almacena el contenido de las notas
    $html .= "<input type='hidden' name='wp[]' value='" . getNotes($id_lista, $v) . "'>\n";
}

// 16) El último waypoint volverá a ser el almacen.
$html .= "<input type='hidden' name='wp[]' value=$corAlmacen>";
$html .= "<p class='text-center mt-2'>\n";
// 17) Incluímos el botón para ver la ruta
$html .= "<button type='submit' class='btn btn-info btn-sm'><i class='fas fa-route mr-1'></i>Ver Ruta en Mapa</button>\n";
$html .= "</div>";
$html .= "</form>";
$html .= "</div>";

// 18) Imprimimos la variable con la información
echo $html;