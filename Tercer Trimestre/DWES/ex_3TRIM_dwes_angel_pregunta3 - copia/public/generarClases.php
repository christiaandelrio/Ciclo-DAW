<?php
// Fichero para generar las clases a partir del documento WSDL que nos generamos

// Hacemos require del autoload
require '../vendor/autoload.php';

// Usamos las dependencias necesarias (instaladas con Composer)
use Wsdl2PhpGenerator\Generator;
use Wsdl2PhpGenerator\Config;

// NOTA: En esta práctica, no configuramos SOAP para que use los certificados raíz adecuados como hicimos en el ejemplo de las 'titulosUni',
// pues es una práctica en local, y tenemos el documento WSDL en nuestro equipo

// Creamos objeto Generator e invocamos su método generate, y dentro de este un objeto Config pasandole la configuración necesaria
$generator = new Generator();
$generator->generate(
    new Config([
        'inputFile' => 'http://localhost/ex_3TRIM_dwes_angel/servidorSoap/servicioW.php?wsdl',  //wsdl (ojo, fichero 'servicioW.php')
        'outputDir' => '../src/Clases1',    // Directorio donde generamos las clases (Lo indica así el enunciado)
        'namespaceName' => 'Clases\Clases1' // Namespace que usaremos en las clases generadas
    ])
);
