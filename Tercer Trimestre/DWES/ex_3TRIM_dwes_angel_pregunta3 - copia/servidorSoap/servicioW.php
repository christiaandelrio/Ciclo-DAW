<?php
// Con este fichero nos crearemos un servidor Soap que SI TIENE DOCUMENTO WSDL ASOCIADO

require '../vendor/autoload.php';

// Definimos la ruta al documento WSDL que generamos
$url = "http://localhost/ex_3TRIM_dwes_angel/servidorSoap/servicio.wsdl";

// Bloque try-catch para capturar posibles errores
try {
    // Creamos el objeto, pasándole esta vez como parámetro la ruta al documento WSDL
    $server = new SoapServer($url);
    // Usamos método setClass() nuevamente
    $server->setClass('Clases\Operaciones');
    // Método handle() para procesar las peticiones
    $server->handle();
    // Si hay errores capturamos con SoapFault nuevamente
} catch (SoapFault $f) {
    die("Error en server: " . $f->getMessage());
}
