<?php

require 'Coordenadas.php';
require(__DIR__ . '/../vendor/autoload.php');

use Jaxon\Jaxon;
use function Jaxon\jaxon;
use Jaxon\Response\Response;

$jaxon = jaxon();

//getCoordenadas es una función que recibe un parámetro (la dirección) y devuelve las coordenadas de la misma
function getCoordenadas($dir) {
    // Creamos una respuesta para devolverla al cliente
    $resp = jaxon()->newResponse();
    // Eliminamos los espacios en blanco del principio y del final de la cadena
    $dir  = trim($dir);

    //Validamos que la dirección tenga más de 4 caracteres
    if (strlen($dir) < 4) {
        $resp->alert("Coordenadas no válidas!!!");
        
        return $resp;
    }

    // Creamos un objeto de la clase Coordenadas (ver Coordenadas.php)
    $c   = new Coordenadas($dir);
    $lat = $c->getCoordenadas()[0];
    $lon = $c->getCoordenadas()[1];
    $alt = $c->getCoordenadas()[2] . " mts.";

    $resp->assign('lat', 'value', $lat);
    $resp->assign('lon', 'value', $lon);
    $resp->assign('alt', 'value', $alt);
    
    return $resp;
}

//ordenarEnvios es una función con dos parámetros (puntos de reparto y el id de la lista de tareas/reparto) y devuelve datos e ids
function ordenarEnvios($puntos, $id) {
    //Creamos una respuesta para devolverla al cliente
    $resp = jaxon()->newResponse();

    //Eliminamos los espacios en blanco del principio y del final de la cadena y validamos que la cadena (puntos) no esté vacía
    if (strlen(trim($puntos)) == 0) {
        $resp->alert("Puntos no válidos");
        return $resp;
    }

    //Creamos un objeto de la clase Coordenadas (ver Coordenadas.php)
    $c     = new Coordenadas();
    //Creamos unos datos a partir del método ordenarEnvios de la clase Coordenadas
    $datos = $c->ordenarEnvios($puntos);

    // Usando AJAX (a través de Jaxon), invocamos el método javascript obtuvimosDatos
    $datosRespuesta = array( 'respuesta' => $datos, 'id' =>$id);
    $resp->call('obtuvimosDatos', $datosRespuesta);
    
    //Devolvemos la respuesta que es un array asociativo con los datos e ids
    return $resp;
}


$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'getCoordenadas');
$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'ordenarEnvios');

if($jaxon->canProcessRequest())  $jaxon->processRequest();