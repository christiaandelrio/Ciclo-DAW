<?php 
// null

$a1 = "hola";
unset ($a1);

$a2 = null;
$b2a = --$a2;
$a2 -= 1;

$a3 = null - 1;


$a4 = NULL;
$b4a = ( $a4 == $a4['lo']['que']['sea']);
$b4b = ( $a4 === $a4['lo']['que']['sea']);

$a5 = (null - null) * 10;

$a6 = null;
$lista['nombre'] = $a6['nombre'];
$lista['edad']   = $a6['años'];
var_dump($lista);

$a7 = null == "";

$a8 = null == [];

$a9 = null == array();




echo PHP_EOL . "FIN";
?>