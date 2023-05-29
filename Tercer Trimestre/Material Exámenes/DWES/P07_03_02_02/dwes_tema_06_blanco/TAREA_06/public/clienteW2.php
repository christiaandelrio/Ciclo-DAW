<?php
require '../vendor/autoload.php';

use Clases\Clases1\ClasesOperacionesService;

$url = 'http://127.0.0.1/dwes_tema_06_blanco/TAREA_06/servidorSoap/servicio.wsdl';
try {
    $cliente = new SoapClient($url);
} catch (SoapFault $f) {
    die("Error en cliente SOAP:" . $f->getMessage());
}

$codP = 2;
$codT = 1;
$codF = 'MP3';

//---------------------------------------------------------------------------------------
$objeto = new ClasesOperacionesService();


//funcion getPvp ------------------------------------------------------------------------
$pvp = $objeto->getPvp($codP);
$precio = ($pvp == null) ? "No existe es Producto" : $pvp;
echo "Código de producto de Código $codP: $precio";


//funcion getFamilias -------------------------------------------------------------------
echo "<br>Códigos de Familas:";
$prueba = $objeto->getFamilias();
echo "<ul>";
foreach ($prueba as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";


//funcion getProductosFamila ------------------------------------------------------------
$productos = $objeto->getProductosFamilia($codF);
echo "<br>Productos de la Famila $codF:";
$prueba = $objeto->getProductosFamilia($codF);
echo "<ul>";
foreach ($prueba as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";


// funcion getStock ---------------------------------------------------------------------
$unidades = $objeto->getStock($codP, $codT);
echo "<br>Unidades del producto de código $codP en la tienda de código $codT: $unidades";
