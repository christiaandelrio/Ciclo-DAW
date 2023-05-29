<?php
// Con este fichero nos crearemos un servidor Soap que NO TIENE DOCUMENTO WSDL ASOCIADO (VER EJEMPLO CREAR_SERVICIO_WEB_SIN_WSDL)

// Hacemos el require del autoload como solicita la tarea
require '../vendor/autoload.php';

// Definimos la ruta a nuestro servidor Soap, donde se encuentra este propio documento
$uri = "http://localhost/ex_3TRIM_dwes_angel/servidorSoap";

// Definimos una variable parámetros, donde metemos la uri
$parametros = ['uri' => $uri];

// Bloque try-catch para capturar posibles errores
try {
    // Creamos el objeto, pasandole como parámetros un NULL y la variable anterior que contiene la uri
    $server = new SoapServer(NULL, $parametros);
    // Para añadir las funciones de la clase Operaciones, usamos el método 'setClass'
    $server->setClass('Clases\Operaciones');
    // El método handle es el que procesa las peticiones, recibidas por POST o HTTP
    $server->handle();
    // Si hay errores, los capturamos con 'SoapFault'
} catch (SoapFault $f) {
    die("Error en server: " . $f->getMessage());
}
