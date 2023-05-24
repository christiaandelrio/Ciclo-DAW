 <?php
  
/******************************* NAVEGADOR *********************
1) Vamos a bing maps y abrimos sesion (LA CLAVE YA LA TENEMOS)
2) Para ver todos los servicios y PARÁMETROS NECESARIOS, vamos a https://learn.microsoft.com/en-us/bingmaps/rest-services/locations/
3) Para utilizar la geolocalización, este ejemplo nos es suficiente

****************************************************************/

/**************** FICHERO PHP *******************************/

// OJO!!!!! Aqui NO usamos CURL

// 1) Definimos la URL a donde haremos la solicitud
  $urlLocalizacion = "http://dev.virtualearth.net/REST/v1/Locations";

// 2) Incluimos fichero donde tenemos la KEY
  include("../claves.inc.php");

// 3) DEFINIMOS LOS PARÁMETROS NECESARIOS COMO HICIMOS PARA WEATHER-MAP, en este caso latitud y longitud
  $la = '42.264799151433';
  $lo = '-8.765128490705925';

// 4) Construimos la URL de solicitud al completo. Le concatenamos los parámetros (EN OUTPUT PODRIAMOS PONERLE =xml)
  $revGeocodeUrl = $urlLocalizacion . "/$la,$lo?c=es&output=json&key={$keyBing}";

// 5) OJOO!! Aqui utilizamos la función 'file_get_contents' para realizar la solicitud HTTP, pasando la URL completa
  $salida  = file_get_contents($revGeocodeUrl);

// 6) Decodificamos la respuesta (Pues nos devuelve un .json)
  $salida1 = json_decode($salida, true);

// 7) Mostramos la respuesta
  print_r($salida1);


// LA RESPUESTA LA TENEMOS EN EL ARCHIVO .txt DE ESTA CARPETA

?>