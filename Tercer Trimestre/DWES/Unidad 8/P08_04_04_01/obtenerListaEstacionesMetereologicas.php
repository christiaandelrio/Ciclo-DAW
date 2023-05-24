<?php

// ESTE EJEMPLO ES EL MISMO DE OBTENER TODAS LAS ESTACIONES CLIMATOLÓGICAS DE ESPAÑA, ***PERO TRAYÉNDONOS LOS DATOS FINALES***

// 1) Iniciamos sesión nuevamente
$curl = curl_init();

// 2) Aquí ya definimos la URL en vez de meterla en las Opciones de la solicitud
$urlBase = 'https://opendata.aemet.es/opendata/api/valores/climatologicos/inventarioestaciones/todasestaciones/';

// 3) Incluimos el fichero de claves
include("../claves.inc.php");

// 4) Definimos la URL completa con la que haremos la solicitud, añadiendo la KEY
$url = $urlBase . "?api_key=" . $keyAEMET;
 
// 5) Definimos las opciones de la solicitud
curl_setopt_array($curl, array(
  // Ahora aquí llamamos a la variable que contiene la URL, EL RESTO IGUAL
  CURLOPT_URL => $url,
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


// Opciones depuración:
// curl_setopt($curl,CURLOPT_VERBOSE, true );
// curl_setopt($curl,CURLOPT_CERTINFO, true );

// 6) Guardamos respuesta y error si lo hubieran
$respuesta = curl_exec($curl);
$err = curl_error($curl);

// Ver información depuración y transferencia:
// var_dump(curl_getinfo($curl));

// 7) Cerramos sesión
curl_close($curl);

// 8) OJOOOOO!! Ahora no mostramos la respuesta, si no que la convertimos en un Array con 'json_decode'
if ($err) {
  echo "cURL Error #:" . $err;
} else {
  $datos = json_decode($respuesta, true);
}

// A PARTIR DE AQUI ES NUEVO****

// 9) Volvemos a iniciar la sesión (MISMA VARIABLE)
$curl = curl_init();

// 10) Ya no necesitamos ni clave ni URL
curl_setopt_array($curl, array(
  // 11) ***OJOOO** Ahora le pasamos la variable donde guardamos el Array, LLAMANDO AL PARÁMETRO 'DATOS'
  // Esto no tiene que ser siempre asi, pillamos 'datos' porque en esta API nos devuelve ese parámetro, para otras puede ser distinto
  CURLOPT_URL => $datos['datos'],
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

// 12) Ejecutamos, guardamos y cerramos como antes
$respuesta = curl_exec($curl);
$err = curl_error($curl);
curl_close($curl);

// 13) ***OJOO*** Sanitizar la cadena para que solo tenga UTF-8 válidos, SI NO json_decode CASCA!!
$respuesta = mb_convert_encoding($respuesta, 'UTF-8', 'UTF-8');
// file_put_contents('estacionesmetereologicas.log', $respuesta);

// 14) ***OJO*** Volvemos a decodificar el array que previamente hemos sanitizado
$tiempoArray = json_decode($respuesta, true);

// 15) Mostramos los datos obtenidos
print_r($tiempoArray);