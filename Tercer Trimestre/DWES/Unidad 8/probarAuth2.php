<?php

/******************************* NAVEGADOR *********************
Los servicios utilizado hasta ahora recibían peticiones por parte de nuestra aplicación web y devolvían respuestas a las mismas en formatos JSON o AJAX. 
Google Tasks necesita una AUTORIZACIÓN para devolver información privada de un usuario. 
Para obtener esa autorización, nuestra aplicación deberá usar el protocolo OAuth2.
1) Antes de nada, tenemos que ir a GOOGLE CLOUD (console.cloud.google.com) y logearnos con el usuario que tenemos creado
2) Seleccionamos el proyecto que vamos a utilizar (en este caso, PhpGoogleAPIs)
3) Vamos a APIs y Servicios -> Pantalla Consentimiento -> Editar App
4) Establecer PÁGINA PRINCIPAL DE NUESTRA APLICACIÓN (EL FICHERO PHP DONDE USAMOS LA API DE TASKS O AL MENOS LA CARPETA)
5) Poner USUARIOS DE PRUEBA (LOS ÚNICOS QUE PODRÁN UTILIZAR EL SERVICIO) (EN ESTE CASO, MI CORREO PRINCIPAL)
6) Ahora vamos a CREDENCIALES** Vamos a nuestro ID de cliente 0Auth2.0, y pinchamos en él
7) ********* En URI DE REDIRECCIONAMIENTO autorizado, METEMOS LAS RUTAS A NUESTROS FICHEROS .PHP QUE UTILIZAN LA API ***********

8) HAY QUE UTILIZAR COMPOSER**** DEPENDENCIAS:
    "google/apiclient": "^2.14",
    "google/cloud-logging": "^1.5"

****************************************************************/

/**************** FICHERO PHP *******************************/

// 1) Primero iniciamos la sesión como siempre
session_start();

// 2) Cargamos el autoload con require como siempre que utilizamos Composer
require 'vendor/autoload.php';

// 3) Incluimos el fichero donde tenemos nuestras claves (OJO PORQUE SON DISTINTAS SEGUN EL PROYECTO, en este caso solo tenemos un proyecto)
include("../claves.inc.php");

// 4) OJO!!! Ponemos URL de redireccionamiento, esto lo configuramos en CREDENCIALES
$redirect_uri = 'http://localhost/dwes_UD_08_DAVID/P08_04_04_05/probarAuth2.php';


// 5) Creamos la solicitud de cliente 
$client = new Google_Client();

// 6) Establecemos las claves de cliente
$client->setClientId($googleClientId);
$client->setClientSecret($googleClientSecret);

// 7) Establecemos la URL de redireccion antes definida
$client->setRedirectUri($redirect_uri);

// 8) Con 'addScope', agregamos los ámbitos para acceder al perfil y correo del usuario (LO DEL LOGIN EN GOOGLE)
$client->addScope('profile');
$client->addScope('email');

// 9) Si recibimos el parámetro 'code' (es nuestro código de autentificación proporcionado por Google)
if (isset($_GET['code'])) {
    // 10) Utilizamos el método 'fetchAccessToken...' para intercambiar el código de autorización por un TOKEN DE ACCESO (CADUCAN!!!)
    $token=$client->fetchAccessTokenWithAuthCode($_GET['code']);
    // 11) Establecemos el token de entrada en la variable de la solicitud con 'setAccessToken'
    $client->setAccessToken($token);

    // 12) Instanciamos un nuevo objeto de la API 0Auth2 para obtener la información del usuario autenticado
    $gauth = new Google_Service_Oauth2($client);

    // 13) Obtenemos esta información mediante el método 'userinfo->get()'
    $google_info = $gauth->userinfo->get();
    
    // 14) Cogemos el mail y el nombre
    $email = $google_info->email;
    $name  = $google_info->name;
    // 15) Imprimimos los datos solicitados
    echo "Benvido " . $name . " estás rexistrado usando email: " . $email;

} else {
    // Si no se recibe el parámetro 'code', significa que el usuario aun no inició sesión en Google
    // Mostramos un enlace a la página de inicio de sesión mediante 'createAuthUrl()'
    echo "<a href='" . $client->createAuthUrl() . "'>Login with google</a>";

}