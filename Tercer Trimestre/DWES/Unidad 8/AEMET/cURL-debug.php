<?php

// En este ejemplo, usamos otra API, en este caso para obtener datos de todas las ESTACIONES CLIMATOLÓGICAS DE ESPAÑA

$curl = curl_init();

include("../claves.inc.php");

// Igual hasta aquí
curl_setopt_array($curl, array(
  // Aqui la URL cambia, usamos otra API distinta (NO REQUIERE DE PARÁMETROS!!!!!)
  CURLOPT_URL => "https://opendata.aemet.es/opendata/api/valores/climatologicos/inventarioestaciones/todasestaciones/?api_key=" . $keyAEMET, 
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "GET",
  CURLOPT_HTTPHEADER => array(
    "cache-control: no-cache"
  ),
));
// Hasta aqui todo igual

// ****** AQUI ES NUEVO ***** Configurar opciones depuración
// Usamos la función 'curl_setopt'

curl_setopt($curl,CURLOPT_VERBOSE, true );
curl_setopt($curl,CURLOPT_CERTINFO, true );

// Guardamos respuesta y error si lo hubiera
$response = curl_exec($curl);
$err = curl_error($curl);

// ver información transferencia
var_dump(curl_getinfo($curl));

// Cerramos la sesión
curl_close($curl);

// Imprimimos error si lo hay, o la respuesta
if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;
}

// ESTA PRUEBA TAMBIEN NOS DEVUELVE UN MONTÓN DE INFO DETALLADA DE LA SOLICITUD, Y AL FINAL, UNOS DATOS EN .JSON
// EN EL PARÁMETRO 'DATOS' HAY UNA URL QUE ES DONDE REALMENTE ESTÁN LOS DATOS QUE QUEREMOS
