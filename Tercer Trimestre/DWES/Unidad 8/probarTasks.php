<?php

// REVISAR EJEMPLO 'probarAuth2.php', PARA CONFIGURACIÓN DE LA CONSOLA DE GOOGLE Y COMPOSER

// 1) Igual que en el otro ejemplo. iniciar sesión incluir ficheros necesarios y definir la URL de redireccionamiento
session_start();
require 'vendor/autoload.php';
$redirect_uri = 'http://localhost/dwes_UD_08_DAVID/P08_04_04_05/probarTasks.php';
include("../claves.inc.php");


// 2) Crear la solicitud de cliente, todo igual que el otro ejemplo
$client = new Google_Client();
$client->setClientId($googleClientId);
$client->setClientSecret($googleClientSecret);
$client->setRedirectUri($redirect_uri);
$client->addScope('profile');
$client->addScope('email');

// 3) Establecemos el nombre de la aplicación mediante 'setApplicationName'
$client->setApplicationName('PruebaTasks');

// 4) Establecemos el ámbito de Google Tasks:
$client->setScopes(Google_Service_Tasks::TASKS);

// 5) Con un PopUp, solicitamos al usuario que seleccione cuenta de usuario y dé su consentimiento
$client->setPrompt('select_account consent');

// 6) Si recibimos el código, solicitamos un token de acceso (igual)
if (isset($_GET['code'])) {
    $token=$client->fetchAccessTokenWithAuthCode($_GET['code']);
    $client->setAccessToken($token);
    
    // 7) OJO, NUEVO!!! Guardamos el token de acceso en la misma variable donde lo solicitamos
    $_SESSION['token'] = $token;

    // 8) Una vez con el token de acceso, REDIRIGIMOS A ESTA MISMA PÁGINA
    header('Location: ' . filter_var($redirect_uri, FILTER_SANITIZE_URL));

} else {
    // Si no se recibe el parámetro 'code', significa que el usuario aun no inició sesión en Google. Ponemos enlace para hacerlo
    echo "<a href='" . $client->createAuthUrl() . "'>Login with google</a>";
}


// 9) Verificamos si existe un token de acceso en la variable de sesión
if (!empty($_SESSION['token'])) {
        // 10) Si tenemos token, LO METEMOS EN LA VARIABLE DE LA SOLICITUD (IGUAL QUE OTRO EJEMPLO!!!)
        $client->setAccessToken($_SESSION['token']);
        // 11) Si el token ha expirado, eliminamos la sesión que lo almacenaba
        if ($client->isAccessTokenExpired()) {
            unset($_SESSION['token']);
        }
} else {
        // 12) Si no tenemos token o expiró, HACEMOS ALGO PARECIDO A CUANDO NO TENIAMOS EL CODE
        $authUrl = $client->createAuthUrl();
        header("Location:$authUrl"); //nos manda a la página de google
}


// 13) Una vez con el token establecido para el acceso, INSTANCIAMOS EL OBJETO DE ACCESO A LA API COMO HICIMOS EN EL OTRO EJEMPLO
$service = new Google_Service_Tasks($client);

// 14) Establecemos las opciones deseadas, en este caso, numero maximo de resultados
$optParams = ['maxResults' => 10];

// 15) Utilizamos la instancia de Tasks para obtener la lista de tareas mediante 'listTasklist', pasandole las opciones
$results = $service->tasklists->listTasklists($optParams);

// 16) Verificamos si tenemos algun resultado
if (count($results->getItems()) == 0) {
      echo "Ninguna Lista de tareas encontrada";
} else {
      // 17) Si hay LISTAS DE TAREAS, las recorremos con un foreach
      echo "<p><strong>Listas de Tareas:</strong></p>";
      foreach ($results->getItems() as $tasklist) {
            // 18) De cada LISTA DE TAREAS, mostramos el Titulo y el ID (IMPORTANTE MOSTRARLO*****)
            printf("%s (%s)<br>", $tasklist->getTitle(), $tasklist->getId());
      }
}


// 17) AQUI DEBERIAMOS PONER EL ID DE UNA LISTA DE TAREAS QUE NOS SALGA AL IMPRIMIRLAS, POR ESO ES IMPORTANTE MOSTRARLO
$res1 = $service->tasks->listTasks("MTcwNDc3MjUxMjIzMzA5MzIzNzc6MDow"); 

// 18) Ahora con un foreach recorremos LAS TAREAS de la lista seleccionada por su ID
echo   "<p><strong>Listando tareas de la lista seleccionada:</strong></p>";
foreach ($res1->getItems() as $tasklist) {
      printf("%s (%s)<br>", $tasklist->getTitle(), $tasklist->getId());
}


// 19) Creamos nueva lista de tareas
echo "<p><strong>Insertando lista tareas: .....</strong></p>";
// 20) Establecemos el titulo de la lista de tareas
$opciones =[ "title"=>"Lista de Tareas 3"];
// 21) Instanciamos un objeto de Google Tasks para crear listas y le pasamos las opciones
$taskList = new Google_Service_Tasks_TaskList($opciones);
// 22) Insertamos
$service->tasklists->insert($taskList);

// 23) Ahora vamos a insertar una tarea a esta lista recién creada
// Definimos las opciones de la lista con titulo, notas adicionales y fecha de vencimiento
$op = ["title"=>"Entregar Trabajo DWES", "notes"=>"Formato pdf", "due"=>"2023-05-15T10:57:00.000-08:00"];
// 24) Instanciamos el objeto para insertar tareas
$tarea = new Google_Service_Tasks_Task($op);

// 25) Utilizamos nuevamente el método insert y le pasamos nuestro ID de la lista seleccionada (LISTA DE TAREAS 3)
$service->tasks->insert('WHB2RktYOFF5ZHlyMjFDUA', $tarea); 

// 26) Para borrar una tarea especifica de una lista, necesitamos id de lista e id de tarea
//id_tasklist, idtask (Pon las tuyas)
//$service->tasks->delete("UlZzTm82YXM5dm5hYXE2Rg", "bU9hVFBDWlFPdFhnbVJGaQ"); 
