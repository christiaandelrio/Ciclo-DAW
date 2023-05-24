<?php
/**************************** NAVEGADOR ********************** 
1) Antes de nada, nos vamos a la pagina de OpenDataAEMET y vamos a 'Acceso Desarrolladores', 'HATEOAS', 'Authorize' (boton verde), y pegamos clave
2) Ya podemos utilizar todas las APIs que hay disponibles, debemos seleccionar la API que queramos y ver qué parametros necesita
3) Debemos ir a 'Try It Out', y nos proporcionan un 'REQUEST URL' que es donde están los códigos para hacer la consulta
4) Una vez con el fichero, seleccionamos, en este caso, el ID_PLAYA que queramos y lo pegamos en la API
5) Esto nos devuelve un .JSON, y ahí cogemos el enlace del parámetro 'DATOS', lo metemos en el navegador, y ahí tenemos los datos consultados
*************************************************************/

/**************** FICHERO PHP *******************************/

// 1) Iniciamos sesión cURL con curl_init() y se guarda en la variable $curl. Esta sesión se utilizará para realizar la solicitud HTTP.
$curl = curl_init();

// 2) Con este include, pinchamos el fichero donde están las claves (solo cogemos la que necesitamos)
include("../claves.inc.php");

// 3) Configuramos las opciones para la sesión CURL. Le pasamos la variable de inicio de sesión, y un array con las opciones
curl_setopt_array($curl, array(
  // 4) Pegamos la URL de la API que usamos. OJO!!! Pasamos el ID de la playa (3600802), lo cogemos en la web de la API. AÑADIR CLAVE
  CURLOPT_URL => "https://opendata.aemet.es/opendata/api/prediccion/especifica/playa/3600802/?api_key=" . $keyAEMET,
  // 5) Para que CURL devuelva la respuesta como un String y no la imprima directamente
  CURLOPT_RETURNTRANSFER => true,
  // 6) Permite la compresión de la respuesta si el servidor la admite
  CURLOPT_ENCODING => "",
  // 7) Nº máximo de redirecciones permitidas
  CURLOPT_MAXREDIRS => 10,
  // 8) Tiempo máximo de espera para la solicitud en segundos
  CURLOPT_TIMEOUT => 30,
  // 9) Versión HTTP que utilizará
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  // 10) Método de solicitud en GET
  CURLOPT_CUSTOMREQUEST => "GET",
  // 11) Encabezados HTTP adicionales metidos en un array (SIEMPRE PONDREMOS 'no-cache')
  CURLOPT_HTTPHEADER => array(
    "cache-control: no-cache"
  ),
));

// 12) Ejecutamos la solicitud y guardamos la respuesta en una variable
$response = curl_exec($curl);

// 13) Verificamos si ocurrió algún error en la solicitud, y guardamos ese error en otra variable
$err = curl_error($curl);

// 14) Llamamos con 'var_dump' a la función 'CURL_GETINFO' que muestra información detallada sobre la solicitud
var_dump(curl_getinfo($curl));

// 15) Cerramos la sesión
curl_close($curl);

// 16) Si ocurrió un error en la solicitud, lo mostramos
if ($err) {
  echo "cURL Error #:" . $err;
} else {  // 17) Si no hubo errores, mostramos la respuesta
  echo $response;
}

// ESTA PRUEBA NOS DEVUELVE UN MONTÓN DE INFO DETALLADA DE LA SOLICITUD, Y AL FINAL, UNOS DATOS EN .JSON
// EN EL PARÁMETRO 'DATOS' HAY UNA URL QUE ES DONDE REALMENTE ESTÁN LOS DATOS QUE QUEREMOS