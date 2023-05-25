<?php
include '../src/Tasks.php';
include '../src/config.inc.php';


$service    = new Google_Service_Tasks($client);

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

include '../src/Tools.php';


$apos     = $_GET['pos'];
$id_lista = $_GET['idLt'];

unset($_SESSION['idLt']);

//Obtenemos todas las tareas de esta lista de tareas
$tareas = getTareas($id_lista);
foreach ($apos as $k => $v) {
    //los envios me los manda ordenados del 1 al n
    //en php los array empiezan por cero, por eso restamos 1 
    //asi el envio 1 pasa a ser el 0, el 2 el 1 ...
    $p = $v - 1;
    $arrayO[$k] = $tareas->getItems()[$p]->getTitle();
}

$html = "<form name='1' action='rutas.php' method='POST'>";
// el primer waypoint de la ruta será el almacen.
$html .= "<input type='hidden' name='wp[]' value=$corAlmacen>";
$html .= "<div class='container mt-2 mb-2' style='font-size:0.8rem'>";
$html .= "<ul class='list-group'>";
foreach ($arrayO as $k => $v) {
    $html .= "<li class='list-group-item list-group-item-info'>" . ($k + 1) . ".- " . $v . "</li>\n";
    $html .= "<input type='hidden' name='wp[]' value='" . getNotes($id_lista, $v) . "'>\n";
}

//El último waypoint volverá a ser el almacen.
$html .= "<input type='hidden' name='wp[]' value=$corAlmacen>";
$html .= "<p class='text-center mt-2'>\n";
$html .= "<button type='submit' class='btn btn-info btn-sm' formtarget='_blank'><i class='fas fa-route mr-1'></i>Ver Ruta en Mapa</button>\n";
$html .= "</div>";
$html .= "</form>";
$html .= "</div>";

echo $html;