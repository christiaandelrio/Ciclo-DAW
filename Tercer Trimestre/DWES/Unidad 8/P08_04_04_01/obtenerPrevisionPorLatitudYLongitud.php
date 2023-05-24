<?php

// ********************* VER EJEMPLO DE OPENWEATHERMAP, ES LO MISMO ********************

// Obtener key gratuíta en:
// https://openweathermap.org/price
//   Professional collections --> Free --> Get API KEY

$curl = curl_init();
$urlBase1 = 'http://api.openweathermap.org/geo/1.0/direct?';
$urlBase2 = 'https://api.openweathermap.org/data/2.5/weather?';

$ciudad = 'Vigo';
$lat    = '42.264799151433';
$lon    = '-8.765128490705925';

// Meter aquí la API key que figura en la web:
// [OCULTAR]
include("../claves.inc.php");
$url1   = $urlBase1 . 'q=' . $ciudad . '&limit=5' . "&appid=" .  $keyOpenWeatherMap;
$url2   = $urlBase2 . '&lat=' . $lat . "&lon=" . $lon . "&appid=" . $keyOpenWeatherMap;

curl_setopt_array($curl, array(
  CURLOPT_URL => $url1,
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

// Configurar opciones depuración
// curl_setopt($curl,CURLOPT_VERBOSE, true );
// curl_setopt($curl,CURLOPT_CERTINFO, true );

$response = curl_exec($curl);
$err = curl_error($curl);

// ver información transferencia
// var_dump(curl_getinfo($curl));

curl_close($curl);

if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;
}

