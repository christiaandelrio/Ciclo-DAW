<?php

/*
servicio.php establece un servidor SOAP que escucha en una dirección específica y utiliza una clase para proporcionar operaciones que pueden ser invocadas por los clientes SOAP.

servicio.php es un servicio web SOAP. Aquí se está creando un servidor SOAP que escucha en la dirección $uri, que en este caso es http://127.0.0.1/dwes_tema_06/TAREA_06/servidorSoap. La variable $parametros se utiliza para establecer la opción uri del servidor, que se utiliza para identificar de forma única el servidor.
*/

require '../vendor/autoload.php';

//$uri=$_SERVER['PHP_SELF'];
$uri = "http://127.0.0.1/dwes_tema_06/TAREA_06/servidorSoap";
$parametros = ['uri' => $uri];

/*A continuación, se crea una instancia de la clase SoapServer, que representa el servidor SOAP. Se establece NULL como primer parámetro para indicar que se utilizará el WSDL (Web Services Description Language) generado automáticamente por el servidor, en lugar de proporcionar un WSDL personalizado.*/
try {
    $server = new SoapServer(NULL, $parametros);
    /*El método setClass para establecer la clase Clases\Operaciones como la clase que proporciona las operaciones que pueden ser invocadas por los clientes SOAP. Esta clase debe contener los métodos públicos que se expondrán como operaciones del servicio web.*/
    $server->setClass('Clases\Operaciones');
    /*El método handle se utiliza para procesar las solicitudes entrantes al servidor.*/
    $server->handle();
} catch (SoapFault $f) {
    /*Si se produce algún error durante el procesamiento, se captura la excepción SoapFault y se muestra un mensaje de error.*/
    die("error en server: " . $f->getMessage());
}
