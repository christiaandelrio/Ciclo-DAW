<?php
// Igualdad no estricta

$a1 = (NULL == NULL['lo']['que']['sea']);

$a2 = null;
$b2 = null;
$c2 = $a2 ?? $b2 ?? "hola";
$r2 = $c2 == "hola" ;

$a3 = null;
$b3 = null;
$r3 = "hola" ?? $b2 == null ;

$a4 = null;
$b4 = null;
$r4 = $a4 ?? "hola" == "hola" ?? $b4;

$a5 = null;
$b5 = null;
$r5 = ($a5 ?? "hola") == "hola" ?? $b5;

$r6 = "" == [];

$r7 = "0" == 0;

$r8 = "" == " ";

$r9 = "" == "" . "";

$r10 = true == 1;

$r11 = true == "1";

$r12 = true == "php";

$r13 = 0 == "";

echo PHP_EOL . "FIN";
?>