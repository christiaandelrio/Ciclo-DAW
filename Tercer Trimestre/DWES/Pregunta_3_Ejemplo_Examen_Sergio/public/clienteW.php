<?php
/*clienteW.php emplea la extensión SOAP para consumir un servicio web que expone una serie de funciones a través de un archivo WSDL.

Primero se define la URL del archivo WSDL en la variable $url. A continuación, se crea un objeto SoapClient utilizando esta URL. Si hay algún error en la creación del cliente SOAP, se muestra un mensaje de error y se termina el script.

Este código utiliza SOAP para llamar a varias funciones en un servicio web, y muestra los resultados de estas funciones en la pantalla.
*/

$url = 'http://127.0.0.1/dwes_tema_06/TAREA_06/servidorSoap/servicio.wsdl';

try {
    $cliente = new SoapClient($url);
} catch (SoapFault $f) {
    die("Error en cliente SOAP:" . $f->getMessage());
}

$codP = 2;
$codT = 14;
$codF = 'CONSOL';

//funcion getPvp ----------------------------------------------------------------------------
/*getPvp: se llama a esta función con un parámetro de identificación de producto $codP, y devuelve el precio de venta al público del producto correspondiente. Si el resultado es nulo, se muestra un mensaje indicando que el producto no existe.*/

$pvp = $cliente->__soapCall('getPvp', ['id' => $codP]);
$precio = ($pvp == null) ? "No existe es Producto" : $pvp;
echo "Código de producto de Código $codP: $precio";

//funcion getFamilias -----------------------------------------------------------------------
/*getFamilias: esta función no tiene parámetros, y devuelve un arreglo con los códigos de todas las familias de productos disponibles en el servicio. Estos códigos se muestran en una lista ordenada.*/

echo "<br>Código de Familas";
$familias = $cliente->__soapCall('getFamilias', []);
echo "<ul>";
foreach ($familias as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";

//funcion getProductosFamila ----------------------------------------------------------------
/*getProductosFamilia: esta función se llama con un parámetro $codF que representa el código de una familia de productos, y devuelve un arreglo con los nombres de todos los productos en esa familia. Estos nombres se muestran en una lista ordenada.
*/

$productos = $cliente->__soapCall('getProductosFamilia', ['cofF' => $codF]);
echo "<br>Productos de la Famila $codF:";
echo "<ul>";
foreach ($productos as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";

// funcion getStock -------------------------------------------------------------------------
/*getStock: esta función se llama con dos parámetros $codP y $codT, que representan el código de un producto y el código de una tienda, respectivamente. La función devuelve el número de unidades disponibles del producto en esa tienda. Este número se muestra en pantalla.
*/

$unidades = $cliente->__soapCall('getStock', ['codP' => $codP, 'codT' => $codT]);
echo "<br>Unidades del producto de código; $codP en la tienda de código: $codT: $unidades";
