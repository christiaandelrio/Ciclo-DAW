<?php
// Con este fichero consumimos el servicio web SIN DOCUMENTO WSDL. Para ello definimos las rutas al fichero del servidor y a su carpeta

$url = 'http://localhost/ex_3TRIM_dwes_angel/servidorSoap/servicio.php';
$uri = 'http://localhost/ex_3TRIM_dwes_angel/servidorSoap';

// Bloque try-catch nuevamente para capturar posibles errores
try {
    // Ponemos null porque no hay fichero .wsdl
    $cliente = new SoapClient(null, ['location' => $url, 'uri' => $uri]);
} catch (SoapFault $f) {
    die("Error en cliente SOAP:" . $f->getMessage());
}
// Definimos los parámetros que vamos a pasarle a los métodos
$codP = 2;
$codT = 1;
$codF = 'CONSOL';

//funcion getPvp ----------------------------------------------------------------------------

// Usamos método mágico 'soapCall' para llamar a los métodos, le pasamos como parámetros el nombre del método a invocar, 
// y un array con los parámetros que necesita dicho método
$pvp = $cliente->__soapCall('getPvp', ['id' => $codP]);
$precio = ($pvp == null) ? "No existe ese Producto" : $pvp;
echo "Precio del producto con Código (id) $codP: $precio €<br>";


//funcion getFamilias -----------------------------------------------------------------------

// Lo mismo, método mágico 'soapCall' con parámetro del nombre del método y un array
echo "<br>Código de Familias de la base de datos:";
$familias = $cliente->__soapCall('getFamilias', []);
echo "<ul>";
// Usamos un foreach porque el método 'getFamilias' devuelve un array
foreach ($familias as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";

//funcion getProductosFamila ----------------------------------------------------------------

// Igual, método 'soapCall' con nombre del método y parámetros en un array
$productos = $cliente->__soapCall('getProductosFamilia', ['codF' => $codF]);
echo "<br>Productos de la Familia $codF:";
echo "<ul>";
// foreach nuevamente porque el método 'getProductosFamilia' también devuelve un array
foreach ($productos as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";

// funcion getStock -------------------------------------------------------------------------

// Más de lo mismo, 'soapCall' con nombre del método y parámetros del mismo en un array
$unidades = $cliente->__soapCall('getStock', ['codP' => $codP, 'codT' => $codT]);
$uds = ($unidades == null) ? "En esa tienda no hay unidades" : $unidades;
echo "<br>Unidades del producto con Código (id) $codP, disponibles en la tienda con código $codT: $unidades";

// funcion getMontante ----------------------------------------------------------------------

$montante = $cliente->__soapCall('getMontante', ['codT' => $codT]);
echo "<br>Montante de la tienda de Código $codT: $montante €";

// funcion getNombreProducto ----------------------------------------------------------------
$nombre = $cliente->__soapCall('getNombreProducto',['codP' => $codP]);
echo "<br>Nombre del producto con código (id) $codP: $nombre";
