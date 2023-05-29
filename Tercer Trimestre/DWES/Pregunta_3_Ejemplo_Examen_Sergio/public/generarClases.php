<?php
/*generarClases.php se encarga de generar las clases PHP que se utilizarán para acceder al servicio web SOAP. Para ello, utiliza la biblioteca Wsdl2PhpGenerator, que se instala mediante Composer.*/

require '../vendor/autoload.php';

/*Importar la clase Generator y la clase Config del espacio de nombres Wsdl2PhpGenerator. Usamos el paquete wsdl2PhpGenerator que genera clases a partir de un wsdl*/
use Wsdl2PhpGenerator\Generator;
use Wsdl2PhpGenerator\Config;

/*Se crea un objeto de la clase Generator, que se utiliza para generar las clases.*/
$generator = new Generator();

/*Se llama al método generate de la instancia de Generator, al cual se le pasa un objeto Config que especifica los parámetros de generación de las clases.*/
$generator->generate(
    new Config([
        /*inputFile: Especifica la ubicación del archivo WSDL que describe el servicio web SOAP. En este caso, se utiliza una URL que apunta a un archivo local en el servidor (en la ruta ../servidorSoap/servicioW.php?wsdl).*/
        'inputFile' => 'http://127.0.0.1/dwes_tema_06/TAREA_06/servidorSoap/servicioW.php?wsdl',
        /*outputDir: Especifica el directorio de salida donde se generarán las clases PHP. En este caso, se genera en el directorio ../src/Clases1.*/
        'outputDir' => '../src/Clases1',
        /*namespaceName: Especifica el nombre del espacio de nombres que se utilizará para las clases generadas. En este caso, se utiliza el espacio de nombres Clases\Clases1.*/
        'namespaceName' => 'Clases\Clases1'
    ])
);
