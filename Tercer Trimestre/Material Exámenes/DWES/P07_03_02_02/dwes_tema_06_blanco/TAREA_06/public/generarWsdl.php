<?php
require '../vendor/autoload.php';

use PHP2WSDL\PHPClass2WSDL;

$class = "Clases\\Operaciones";
$uri = 'http://127.0.0.1/dwes_tema_06_blanco/TAREA_06/servidorSoap/servicioW.php';
$wsdlGenerator = new PHPClass2WSDL($class, $uri);
$wsdlGenerator->generateWSDL(true);
$fichero = $wsdlGenerator->save('../servidorSoap/servicio.wsdl');
