<?php
include '../src/config.inc.php';
require 'Coordenadas.php';
require(__DIR__ . '/../vendor/autoload.php');

use Jaxon\Jaxon;
use function Jaxon\jaxon;
use Jaxon\Response\Response;

$jaxon = jaxon();

function getCoordenadas($dir) {
    $resp = jaxon()->newResponse();
    $dir  = trim($dir);

    if (strlen($dir) < 4) {
        $resp->alert("Coordenadas no válidas!!!");
        
        return $resp;
    }

    $c   = new Coordenadas($dir);
    $lat = $c->getCoordenadas()[0];
    $lon = $c->getCoordenadas()[1];
    $alt = $c->getCoordenadas()[2] . " mts.";

    $resp->assign('lat', 'value', $lat);
    $resp->assign('lon', 'value', $lon);
    $resp->assign('alt', 'value', $alt);
    
    return $resp;
}

function ordenarEnvios($puntos, $id) {
    $resp = jaxon()->newResponse();

    if (strlen(trim($puntos)) == 0) {
        $resp->alert("Puntos no válidos");
        return $resp;
    }

    $c     = new Coordenadas();
    $datos = $c->ordenarEnvios($puntos);

    // Usando AJAX (a través de Jaxon), invocamos el método javascript obtivimosDatos
    $datosRespuesta = array( 'respuesta' => $datos, 'id' =>$id);
    $resp->call('obtuvimosDatos', $datosRespuesta);
    
    return $resp;
}


$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'getCoordenadas');
$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'ordenarEnvios');

if($jaxon->canProcessRequest())  $jaxon->processRequest();