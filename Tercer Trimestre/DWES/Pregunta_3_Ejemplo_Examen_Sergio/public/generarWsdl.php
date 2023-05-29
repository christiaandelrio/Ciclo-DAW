<?php
/*generarWsdl.php se encarga de generar, a partir de una clase PHP, el archivo WSDL (Web Services Description Language) que describe el servicio web SOAP. Para ello, utiliza la biblioteca PHP2WSDL, que se instala mediante Composer.
*/

require '../vendor/autoload.php';

/*Importar la clase PHPClass2WSDL de PHP2WSDL. Usamos el paquete php2wsdl que crea un wsdl a partir de php. La clase PHPClass2WSDL es la que se encarga de generar el archivo WSDL a partir de una clase PHP.*/
use PHP2WSDL\PHPClass2WSDL;

/*Definimos el nombre de la clase PHP que se utilizará para generar el archivo WSDL. En este caso, se utiliza la clase Operaciones que se encuentra en el espacio de nombres Clases.*/
$class = "Clases\\Operaciones";

/*Aquí se define la dirección URL del servicio web que se utilizará en el archivo WSDL.*/
$uri = 'http://127.0.0.1/dwes_tema_06/TAREA_06/servidorSoap/servicioW.php';

/*Se crea un objeto de la clase PHPClass2WSDL, que se utiliza para generar el archivo WSDL. Se pasan como parámetros el nombre de la clase PHP y la dirección URL del servicio web.*/
$wsdlGenerator = new PHPClass2WSDL($class, $uri);

/* Se llama al método generateWSDL del objeto $wsdlGenerator para generar el archivo WSDL. Se pasa como parámetro el valor true, lo que indica que se deben incluir las anotaciones de PHPDoc en el archivo WSDL.*/
$wsdlGenerator->generateWSDL(true);

/*Se llama al método save del objeto $wsdlGenerator para guardar el archivo WSDL generado en el directorio ../servidorSoap con el nombre servicio.wsdl. La variable $fichero contendrá la ruta completa del archivo guardado.*/
$fichero = $wsdlGenerator->save('../servidorSoap/servicio.wsdl');
