<?php
$url = 'http://127.0.0.1/dwes_tema_06_blanco/TAREA_06/servidorSoap/servicio.wsdl';

try {
    $cliente = new SoapClient($url);
} catch (SoapFault $f) {
    die("Error en cliente SOAP:" . $f->getMessage());
}

$codP = 1;
$codT = 1;
$codF = 'CONSOL';

//funcion getPvp ----------------------------------------------------------------------------
$pvp = $cliente->__soapCall('getPvp', ['id' => $codP]);
$precio = ($pvp == null) ? "No existe es Producto" : $pvp;
echo "Código de producto de Código $codP: $precio";

//Función getPrecio -------------------------------------------------------------------------
$pvp = $cliente->__soapCall('getPrecio', ['id' => $codP]);
$precio = ($pvp == null) ? "No existe es Producto" : $pvp;
echo "Código de producto de Código $codP: $precio";

//funcion getFamilias -----------------------------------------------------------------------
echo "<br>Código de Familas";
$familias = $cliente->__soapCall('getFamilias', []);
echo "<ul>";
foreach ($familias as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";

//funcion getProductosFamila ----------------------------------------------------------------
$productos = $cliente->__soapCall('getProductosFamilia', ['cofF' => $codF]);
echo "<br>Productos de la Famila $codF:";
echo "<ul>";
foreach ($productos as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";

// funcion getStock -------------------------------------------------------------------------
$unidades = $cliente->__soapCall('getStock', ['codP' => $codP, 'codT' => $codT]);
echo "<br>Unidades del producto de código; $codP en la tienda de código: $codT: $unidades";


//Función getNombre -------------------------------------------------------------------------
$nombre = $cliente->__soapCall('getNombre', ['id' => $codP]);
$nombreProducto = ($nombre == null) ? "No existe ese Producto" : $nombre;
echo "Nombre de producto de Código $codP: $nombreProducto";