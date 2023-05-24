<?php

// Una vez definida la clase 'TIEMPO.PHP', nos venimos aquí
// Este fichero será algo similar a los de 'votar.php' y 'validar.php' de la tarea 7
// Basicamente tenemos que definir los métodos de nuestra aplicacion y USAR JAXON para llamarlos de forma ASÍNCRONA desde el JavaScript

// 1) Hacemos el require de la clase TIEMPO la cual vamos a instanciar y el autoload del JAXON
require 'Tiempo.php';
require(__DIR__ . '/../vendor/autoload.php');
// 2) Declaramos los namespaces del Jaxon
use Jaxon\Jaxon;
use function Jaxon\jaxon;
use Jaxon\Response\Response;

// 3) Definimos la función para obtener el tiempo (AQUI SI QUE LE PASAMOS PARÁMETROS!!!!!)
function getTiempo($la, $lo) {
    // 4) Metodo 'newResponse' como siempre
    $resp = jaxon()->newResponse();
    // 5) Método de validación (EL CUAL DEFINIMOS DESPUÉS)
    if (!validar($la, $lo)) {
        $resp->alert("Coordenada erróneas, revíselas");
        return $resp;
    }
    // 6) Creamos objeto de la clase Tiempo (LE PASAMOS LOS PARÁMETROS PORQUE EL CONSTRUCTOR SI LOS TIENE!!!!!)
    $datos = new Tiempo($la, $lo);
    // 7) Llamamos al método 'getTiempo', EL CUAL DEVUELVE UN ARRAY ASOCIATIVO CON json_decode,true!!!
    $tiempo = $datos->getTiempo();
    // ******** VER EN EJEMPLO DE OPENWEATHERMAP LOS ELEMENTOS DEL JSON ********
    // 8) OJO!!!! IMPORTANTE. VAMOS COGIENDO LOS DATOS QUE QUEREMOS DEL ARRAY DEVUELTO POR EL MÉTODO
    $temp = $tiempo['main']['temp'];
    $humedad = $tiempo['main']['humidity'];
    // 9) EN ESTE CASO, NOS FIJAMOS QUE 'weather' CONTIENE OTRO ARRAY, POR TANTO ACCEDEMOS CON LA POSICION '0'
    $tiem = $tiempo['weather'][0]['description'];
    // 10) Usamos el método 'assign' como en el ejemplo de Jaxon del formulario
    // 11) Vamos cogiendo los ID del formulario de cada campo, pinchamos su 'value', y mostramos los datos obtenidos del .json
    $resp->assign('tie', 'value', $tiem);
    $resp->assign('tem', 'value', $temp . "º");
    $resp->assign('hum', 'value', $humedad . "%");
    
    // 12) Retornamos la respuesta
    return $resp;
}

function getLocalizacion($la, $lo)  {
    // 13) Volvemos a llamar al método de respuesta
    $resp = jaxon()->newResponse();
    // 14) Llamamos a la función de validación
    if (!validar($la, $lo)) {
        $resp->alert("Coordenada erróneas, revíselas");
        return $resp;
    }
    
    // 15) Creamos objeto pasandole parametros igual que antes
    $datos = new Tiempo($la, $lo);
    // 16) Llamamos al metodo el CUAL DEVUELVE TAMBIEN UN ARRAY ASOCIATIVO
    $ubicacion = $datos->getLocalizacion();
    // ******** VER EN EJEMPLO DE BING MAPS LOS ELEMENTOS DEL JSON ********
    // 17) Cogemos los datos del array
    $dir = $ubicacion['formattedAddress'];
    $ciudad = $ubicacion['locality'] . ", ". $ubicacion['adminDistrict2'];
    $pais = $ubicacion['countryRegion'];
    // 18) Volvemos a usar el método 'assign' para ir llevando los datos al formulario
    $resp->assign('dir', 'value', $dir);
    $resp->assign('ciu', 'value', $ciudad);
    $resp->assign('pai', 'value', $pais);
    
    // 19) Retornamos respuesta 
    return $resp;
}

// 20) Función de validación QUE USAMOS EN LAS FUNCIONES ANTERIORES
function validar($a, $b) {
    // Si latitud o longitud están vacíos o no son números, devuelve false
    if (strlen($a) == 0 || strlen($b) == 0 || !is_numeric($a) || !is_numeric($b)) {
         return false;
    } else {
        return true;
    }
}

// AHORA IGUAL QUE EN LA TAREA 7
// 21) Creamos objeto Jaxon
$jaxon = jaxon();

// 22) Registramos las funciones para ser llamadas desde javascript
$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'getTiempo');
$jaxon->register(Jaxon::CALLABLE_FUNCTION, 'getLocalizacion');

// 23) Procesamos las peticiones
if($jaxon->canProcessRequest())  $jaxon->processRequest();
