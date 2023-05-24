<?php
/**************************** NAVEGADOR ********************** 
1) Vamos a la web de OpenWeatherMap -> API -> SELECCIONAMOS LA API QUE QUERAMOS -> API doc
2) Ahí nos explica los parámetros que habría que pasar para hacer solicitudes
3) Estas solicitudes nos devuelven un JSON como antes
4) ***OJO*** ESTAS APIs YA TE DEVUELVEN UN .JSON CON LOS DATOS, NO UNA URL A LOS DATOS CONCRETOS COMO HACE LA AEMET!!!!
*************************************************************/

/**************** FICHERO PHP *******************************/
// 1) Iniciamos sesión
$curl = curl_init();

// 2) Ejemplo de API disponible en la web. La guardamos en una variable
$urlBase1 = 'http://api.openweathermap.org/geo/1.0/direct?';

// 3) Otro ejemplo de API disponible. CON ESTA HAREMOS EL EJEMPLO ACTUAL
$urlBase2 = 'https://api.openweathermap.org/data/2.5/weather?';

// 4) Definimos los parámetros que vamos a pasar en la solicitud 
// EN EL EJEMPLO DE LA AEMET, LE PASÁBAMOS EL ID DE LA PLAYA DIRECTAMENTE EN LA URL. También puede hacerse así
$ciudad = 'Vigo';
$lat    = '42.264799151433';
$lon    = '-8.765128490705925';

// 5) Llamamos al fichero con nuestras claves
include ("../claves.inc.php");

// 6) Construímos las URL de las APIs que vamos a solicitar
// ***OJO*** EN ESTE CASO, LE CONCATENAMOS LOS PARÁMETROS, EN VEZ DE ESCRIBIRLOS DIRECTAMENTE EN LA URL COMO EN LA AEMET
// Los parámetros utilizados podemos verlos en la web: q=ciudad, lang=idioma, limit=limite resultados, etc.

// $url1 = $urlBase1 . 'q=' . $ciudad . '&limit=5' . "&appid=" .  $keyOpenWeatherMap;
$url2 = $urlBase2 . '&lat=' . $lat . "&lon=" . $lon . "&units=metric". "&lang=es". "&appid=" . $keyOpenWeatherMap;

// 7) Volvemos a configurar las opciones de sesión
curl_setopt_array($curl, array(
  // OJO**** Aqui ya simplemente le pasamos la variable que contiene la URL completa (PODRIAMOS ESCRIBIRLA AQUÍ)
  CURLOPT_URL => $url2,
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
// El resto de opciones quedan igual

// 8) Configurar opciones depuración
// curl_setopt($curl,CURLOPT_VERBOSE, true );
// curl_setopt($curl,CURLOPT_CERTINFO, true );

// 9) Guardamos respuesta y error
$response = curl_exec($curl);
$err = curl_error($curl);

// 10) ver información transferencia si queremos
// var_dump(curl_getinfo($curl));

// 11) Cerramos sesión
curl_close($curl);

// 12) Mostramos el error si lo hubiera, o la respuesta
if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;
}

// 13) ***** OJOOO ****** AQUI ES DISTINTO!! ESTA API YA DEVUELVE LOS DATOS QUE NECESITAMOS
// Usamos la función 'json_decode' para convertir la respuesta en un Array, y lo guardamos en una variable
// Podríamos usar también 'json_decode' en los otros ejemplos, los mostraría de manera poco distinta
$salida = json_decode($response, true);

// 14) Mostramos el array obtenido
print_r($salida);
