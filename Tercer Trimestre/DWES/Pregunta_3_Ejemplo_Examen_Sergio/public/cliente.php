<?php

/*cliente.php se encargar de interactuar con un servidor SOAP que se encuentra en la dirección $url. 
El código instancia un cliente SOAP usando la clase SoapClient y especifica la ubicación del servidor SOAP con la opción location y el URI del servidor SOAP con la opción uri.
A continuación, el código hace varias llamadas a funciones del servidor SOAP:*/

$url = 'http://127.0.0.1/dwes_tema_06/TAREA_06/servidorSoap/servicio.php';
$uri = 'http://127.0.0.1/dwes_tema_06/TAREA_06/servidorSoap';


try {
    $cliente = new SoapClient(null, ['location' => $url, 'uri' => $uri]);
} catch (SoapFault $f) {
    die("Error en cliente SOAP:" . $f->getMessage());
}
$codP = 2;
$codT = 14;
$codF = 'CONSOL';

//funcion getPvp ----------------------------------------------------------------------------
/*getPvp: Recupera el precio de venta al público de un producto identificado por su código $codP. El resultado se almacena en la variable $pvp y se muestra en la pantalla.*/

$pvp = $cliente->__soapCall('getPvp', ['id' => $codP]);
$precio = ($pvp == null) ? "No existe es Producto" : $pvp;
echo "Código de producto de Código $codP: $precio";


//funcion getFamilias -----------------------------------------------------------------------
/*getFamilias: Recupera una lista de códigos de familias de productos. Los códigos se almacenan en la variable $familias y se muestran en la pantalla como una lista HTML.*/

echo "<br>Código de Familas";
$familias = $cliente->__soapCall('getFamilias', []);
echo "<ul>";
foreach ($familias as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";

//funcion getProductosFamila ----------------------------------------------------------------
/* getProductosFamilia: Recupera una lista de códigos de productos que pertenecen a la familia identificada por el código $codF. Los códigos se almacenan en la variable $productos y se muestran en la pantalla como una lista HTML.*/

$productos = $cliente->__soapCall('getProductosFamilia', ['codF' => $codF]);
echo "<br>Productos de la Famila $codF:";
echo "<ul>";
foreach ($productos as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";

// funcion getStock -------------------------------------------------------------------------
/*getStock: Recupera el número de unidades de un producto identificado por su código $codP en la tienda identificada por el código $codT. El resultado se almacena en la variable $unidades y se muestra en la pantalla.*/

$unidades = $cliente->__soapCall('getStock', ['codP' => $codP, 'codT' => $codT]);
echo "<br>Unidades del producto de código; $codP en la tienda de código: $codT: $unidades";

/*
__soapCall es un método mágico de la clase SoapClient en PHP que se utiliza para hacer una llamada a una función o método del servidor SOAP.

Esta función acepta dos argumentos principales: el nombre de la función o método que se va a llamar en el servidor y un array asociativo que contiene los argumentos que se pasarán a la función o método.
*/