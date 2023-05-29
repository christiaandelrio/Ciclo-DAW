<?php
//Fichero para generar las clases
require '../vendor/autoload.php';

use Wsdl2PhpGenerator\Generator;
use Wsdl2PhpGenerator\Config;

$generator = new Generator();
$generator->generate(
    new Config([
        'inputFile' => 'http://127.0.0.1/dwes_tema_06_blanco/TAREA_06/servidorSoap/servicio.wsdl',   
        'outputDir' => '../src/Clases1',
        'namespaceName' => 'Clases\Clases1'
    ])
);
