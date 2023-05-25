<?php //Este fichero maneja lo necesario para autenticarse con Google y acceder a la API de Tasks
session_start();
require '../vendor/autoload.php'; //Hacemos el require del autoload


// $redirect_uri = 'http://' . $_SERVER['HTTP_HOST'] . $_SERVER['PHP_SELF'];
$redirect_uri = 'http://localhost/dwes_tema_08/TAREA_08/public/repartos.php'; //Definimos la uri de redirección
include("../../claves.inc.php"); //Hacemos un include de las claves necesarias


// Crear la solicitud de cliente 
$client = new Google_Client(); //Instanciamos un cliente de Google
$client->setClientId($googleClientId); //Configuramos estableciendo el id y el secret almacenados en claves.inc.php
$client->setClientSecret($googleClientSecret);
$client->setRedirectUri($redirect_uri);
$client->addScope('profile'); //Agregamos lo necesario para acceder al perfil y al correo
$client->addScope('email');
$client->setApplicationName('PruebaTasks'); //Establecemos el nombre de la aplicación, en este caso "PruebaTasks"
$client->setScopes(Google_Service_Tasks::TASKS); 
$client->setPrompt('select_account consent');

// Por si queremos cerrar sesion solo hay que poner en la url ?logout
if (isset($_REQUEST['logout'])) { //si se hace logout se destruye la variable session token
    unset($_SESSION['token']);
}

if (isset($_GET['code'])) {
    $token = $client->fetchAccessTokenWithAuthCode($_GET['code']);
    $client->setAccessToken($token);

    // Guardamos el token en una variable de sesion
    $_SESSION['token'] = $token;

    // redirigimas a esta misma página
    header('Location: ' . filter_var($redirect_uri, FILTER_SANITIZE_URL));
}

// set the access token as part of the client
if (!empty($_SESSION['token'])) {
    $client->setAccessToken($_SESSION['token']);
    if ($client->isAccessTokenExpired()) {
        unset($_SESSION['token']);
    }
} else {
    $authUrl = $client->createAuthUrl();
    header("Location: $authUrl");
}
