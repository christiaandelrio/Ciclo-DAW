<?php

/******************************* NAVEGADOR *********************
1) Vamos a bing rutas y abrimos sesion (LA CLAVE YA LA TENEMOS)
2) Para ver todos los servicios y PARÁMETROS NECESARIOS, vamos a https://learn.microsoft.com/en-us/bingmaps/rest-services/routes/
3) Para utilizar las rutas, este ejemplo nos es suficiente

****************************************************************/

/**************** FICHERO PHP *******************************/

// OJO!!!!! Aqui TAMPOCO usamos CURL

// 1) Incluímos fichero donde tenemos las claves
  include("../claves.inc.php");

// 2) Aqui ya no definimos los parámetros previamente, los metemos en la URL directamente
  $url = "http://dev.virtualearth.net/REST/V1/Routes/Driving?c=es&o=json&wp.0=vigo&wp.1=cangas&key=" . $keyBing;

// 3) OJOO!! Aqui utilizamos otra vez la función 'file_get_contents' para realizar la solicitud HTTP, pasando la URL completa
   $salida  = file_get_contents($url, true);

// 4) Decodificamos la respuesta nuevamente (Pues nos devuelve un .json)
   $salida1 = json_decode($salida, true);

// 5) Mostramos la respuesta
// print_r($salida1);

// 6) ****OJOOO*** NUEVA FUNCIONALIDAD
// Vemos lo que nos devuelve la variable $salida1, un conjunto de Arrays unos dentro de otros
// Vamos cogiendo los elementos que necesitamos para obtener la distancia de viaje total
   $distancia = $salida1['resourceSets'][0]['resources'][0]['travelDistance'];

// 7) Imprimimos la distancia
  echo "$distancia km";
?>