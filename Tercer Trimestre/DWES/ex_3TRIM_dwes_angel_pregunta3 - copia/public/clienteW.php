<?php
// Creamos este fichero para consumir el servicio utilizando el documento WSDL que generamos

// Definimos la url del documento WSDL que acabamos de generar
$url = 'http://localhost/ex_3TRIM_dwes_angel/servidorSoap/servicio.wsdl';

// Definimos bloque try-catch para capturar posibles errores
try {
    // Ahora al objeto 'SoapClient' ya no le pasamos 'null', le pasamos la url del documento WSDL
    $cliente = new SoapClient($url);
} catch (SoapFault $f) {
    die("Error en cliente SOAP:" . $f->getMessage());
}

// Definimos los parámetros nuevamente para pasarle a los métodos
$codP = 2;
$codT = 1;
$codF = 'CONSOL';

// Ahora probamos las funciones programadas igual que habíamos hecho con fichero 'cliente.php', utilizando el método mágico
// 'soapCall()' y pasándole como parámetros el nombre de la función a invocar, y los parámetros que necesita dicha función en un array

//funcion getPvp ----------------------------------------------------------------------------
$pvp = $cliente->__soapCall('getPvp', ['id' => $codP]);
$precio = ($pvp == null) ? "No existe ese Producto" : $pvp;
echo "Precio del producto con Código (id) $codP: $precio €<br>";

//funcion getFamilias -----------------------------------------------------------------------
echo "<br>Código de Familias de la base de datos:";
$familias = $cliente->__soapCall('getFamilias', []);
echo "<ul>";
foreach ($familias as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";

//funcion getProductosFamila ----------------------------------------------------------------
$productos = $cliente->__soapCall('getProductosFamilia', ['codF' => $codF]);
echo "<br>Productos de la Familia $codF:";
echo "<ul>";
foreach ($productos as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";

// funcion getStock -------------------------------------------------------------------------
$unidades = $cliente->__soapCall('getStock', ['codP' => $codP, 'codT' => $codT]);
echo "<br>Unidades del producto con Código (id) $codP, disponibles en la tienda con código $codT: $unidades";

// funcion getMontante ----------------------------------------------------------------------

$montante = $cliente->__soapCall('getMontante', ['codT' => $codT]);
echo "<br>Montante de la tienda de Código $codT: $montante €";

// funcion getNombreProducto ----------------------------------------------------------------
$nombre = $cliente->__soapCall('getNombreProducto',['codP' => $codP]);
echo "<br>Nombre del producto con código (id) $codP: $nombre";