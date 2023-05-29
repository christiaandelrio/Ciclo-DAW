<?php
// Con este fichero, vamos a generarnos el documento WSDL para nuestro servicio web, utilizando PHP2WSDL

// Hacemos el require nuevamente
require '../vendor/autoload.php';

// Usamos la dependencia para generar el documento
use PHP2WSDL\PHPClass2WSDL;

// Definimos la variable 'class', haciendo referencia al namespace 'Clases' y a la clase 'Operaciones'
$class = "Clases\\Operaciones";

// Definimos la ruta al servidor, pero en este caso a 'servicioW.php', que será el nuevo servidor que hará uso del documento WSDL generado
$uri = 'http://localhost/ex_3TRIM_dwes_angel/servidorSoap/servicioW.php';

// Creamos el objeto pasandole los parámetros que acabamos de definir
$wsdlGenerator = new PHPClass2WSDL($class, $uri);

// Llamamos al metodo 'generateWSDL' como true pa que convierta los métodos con parámetro @soap (que definimos en la clase Operaciones)
$wsdlGenerator->generateWSDL(true);

// Indicamos nombre del documento que generamos y ruta para guardarlo
$fichero = $wsdlGenerator->save('../servidorSoap/servicio.wsdl');
