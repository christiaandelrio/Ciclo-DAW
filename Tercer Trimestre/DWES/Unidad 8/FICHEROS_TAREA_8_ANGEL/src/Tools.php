<?php

// En este fichero basicamente vamos a definir los metodos que serán llamados desde el fichero JavaScript utilizando Jaxon

// 1) Hacemos el require de la clase Coordenadas la cual vamos a instanciar y el autoload para el JAXON
require 'Coordenadas.php';
require(__DIR__ . '/../vendor/autoload.php');

// 2) Preparamos Jaxon
use Jaxon\Jaxon;
use function Jaxon\jaxon;
use Jaxon\Response\Response;

// 3) Función para obtener las coordenadas
function getCoordenadas($dir) {
    // 4) newResponse de jaxon para devolver una respuesta
    $resp = jaxon()->newResponse();
    // 5) Usamos 'trim' pa eliminar espacios en blanco de la dirección (La variable $dir la utilizabamos en el constructor de la clase Coordenadas!!!)
    $dir = trim($dir);
    // 6) Validamos la dirección, si la longitud es menor de 4 se consideran coordenadas no válidas
    if (strlen($dir) < 4) {
        $resp->alert("Coordenadas no válidas!!!");
        return $resp;
    }

    // 7) Creamos objeto de la clase Coordenadas (le pasamos parámetro) (En el constructor de clase recuerda el uso de 'func_num_args()')
    $c   = new Coordenadas($dir);
    // 8) El método 'getCoordenadas()' devolvía un Array con latitud, longitud y altitud, recordemoslo
    $lat = $c->getCoordenadas()[0];
    $lon = $c->getCoordenadas()[1];
    $alt = $c->getCoordenadas()[2] . " mts.";

    // 9) Con el método 'assign()', vamos asignando estos valores a los elementos HTML con los 'ID' especificados
    $resp->assign('lat', 'value', $lat);
    $resp->assign('lon', 'value', $lon);
    $resp->assign('alt', 'value', $alt);
    
    // 10) Retornamos la respuesta
    return $resp;
}

// 11) Función para ordenar los envíos
function ordenarEnvios($puntos, $id) {
    // 12) newResponse de jaxon nuevamente
    $resp = jaxon()->newResponse();
    // 13) Si la longitud de la cadena tras eliminar espacios en blanco es 0, consideramos los puntos como NO validos
    if (strlen(trim($puntos)) == 0) {
        $resp->alert("Puntos no válidos");
        return $resp;
    }
    
    // 14) Creamos nuevo objeto de la clase Coordenadas
    $c = new Coordenadas();
    // 15) Llamamos al método 'ordenarEnvios()'  y le pasamos la variable con los puntos de envío
    $datos = $c->ordenarEnvios($puntos);

    // 16) Creamos un nuevo Array que contiene el elemento 'respuesta' (datos ordenados obtenidos del método 'ordenarEnvios()'),
    // y el otro elemento contendrá el valor de la variable $id
    $datosRespuesta = array( 'respuesta' => $datos, 'id' =>$id);

    // 17) Utilizamos el método 'call()' para invocar la función JavaScript, y le pasamos el array como argumento
    $resp->call('obtuvimosDatos', $datosRespuesta);
    
    // 18) Finalmente devolvemos la respuesta construida, que contiene la invocación a la función JavaScript como acabamos de comentar
    return $resp;
}

// 19) Creamos objeto Jaxon
$jaxon = jaxon();
// 20) Registramos las funciones para ser llamadas desde javascript
$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'getCoordenadas');
$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'ordenarEnvios');
// 21) Procesamos las peticiones
if($jaxon->canProcessRequest())  $jaxon->processRequest();