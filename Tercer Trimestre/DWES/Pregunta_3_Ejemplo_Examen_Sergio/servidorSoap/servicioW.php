<?php
require '../vendor/autoload.php';
$url = "http://127.0.0.1/dwes_tema_06/TAREA_06/servidorSoap/servicio.wsdl";

try {
    $server = new SoapServer($url);
    $server->setClass('Clases\Operaciones');
    $server->handle();
} catch (SoapFault $f) {
    die("error en server: " . $f->getMessage());
}
