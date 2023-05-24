<?php

// ***************** VER EJEMPLO ESTACIONES METEREOLOGICAS, ES LO MISMO *****************************

// Códigos de municipio en :
//   https://www.ine.es/daco/daco42/codmun/codmunmapa.htm
//
// Documentación APIs y obtener key gratis en:
//  /api/prediccion/especifica/municipio/diaria/{municipio}
// https://opendata.aemet.es/centrodedescargas/inicio

$curl = curl_init();
$urlBase = 'https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/36008/';

// Meter aquí la API key que recibimos por email:
include("../claves.inc.php");
$url     = $urlBase . "?api_key=" . $keyAEMET; 

curl_setopt_array($curl, array(
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

$respuesta = curl_exec($curl);

// Ver información depuración:
// var_dump(curl_getinfo($curl));

$err = curl_error($curl);
curl_close($curl);

if ($err) {
  echo "cURL Error #:" . $err;
} else {
  $datos = json_decode($respuesta, true);
}


$curl = curl_init();

curl_setopt_array($curl, array(
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

$respuesta = curl_exec($curl);
$err = curl_error($curl);
curl_close($curl);

// Sanitizar la cadena para que solo tenga UTF-8 válidos, si no json_decode casca
$respuesta = mb_convert_encoding($respuesta, 'UTF-8', 'UTF-8');
// file_put_contents('estacionesmetereologicas.log', $respuesta);

$tiempoArray    = json_decode($respuesta, true);
print_r($tiempoArray);