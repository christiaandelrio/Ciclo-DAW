<?php

// IMPORTANTE!!! Revisar configuración de la Console Cloud Google, revisar URIs redireccionamiento y todo lo necesario

// 1) Iniciamos sesión como en los ejemplos y hacemos require del autoload
session_start();
require '../vendor/autoload.php';
// 2) Definimos la URI de redireccionamiento. OJO!!! Porque aquí mandamos a otra página, NO a la misma como en los ejemplos de la unidad
$redirect_uri = 'http://localhost/TAREA_DWES_UD08/public/repartos.php';
// 3) Incluimos el fichero con nuestras claves
include("../claves.inc.php");


// 4) Creamos la solicitud de cliente como en el ejemplo de 'probarTasks'
$client = new Google_Client();
$client->setClientId($googleClientId);
$client->setClientSecret($googleClientSecret);
$client->setRedirectUri($redirect_uri);
$client->addScope('profile');
$client->addScope('email');
$client->setApplicationName('PruebaTasks');
$client->setScopes(Google_Service_Tasks::TASKS); 
$client->setPrompt('select_account consent');

// 5) Si se detecta que se ha solicitado la desconexión (?logout en la URL), se borra el token de acceso almacenado en la sesión.
if (isset($_REQUEST['logout'])) {
    unset($_SESSION['token']);
}

// 6) Si recibimos el código, solicitamos un token de acceso
if (isset($_GET['code'])) {
    // 7) Utilizamos el método 'fetchAccessToken...' para intercambiar el código de autorización por un TOKEN DE ACCESO
    $token = $client->fetchAccessTokenWithAuthCode($_GET['code']);
    // 8) Establecemos el token de entrada en la variable de la solicitud con 'setAccessToken'
    $client->setAccessToken($token);

    // 9) Guardamos el token en una variable de sesion
    $_SESSION['token'] = $token;

    // 10) Una vez con el token de acceso, REDIRIGIMOS A LA PÁGINA
    header('Location: ' . filter_var($redirect_uri, FILTER_SANITIZE_URL));
}

// 11) Verificamos si existe un token de acceso en la variable de sesión
if (!empty($_SESSION['token'])) {
    // 12) Si tenemos token, LO METEMOS EN LA VARIABLE DE LA SOLICITUD
    $client->setAccessToken($_SESSION['token']);
    // 13) Si el token ha expirado, eliminamos la sesión que lo almacenaba
    if ($client->isAccessTokenExpired()) {
        unset($_SESSION['token']);
    }
} else {
    // 14) Si no tenemos token o expiró, mandamos a la página de Google
    $authUrl = $client->createAuthUrl();
    header("Location: $authUrl");
}
