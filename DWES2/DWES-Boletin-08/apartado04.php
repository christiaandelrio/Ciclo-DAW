<?php
// falsys

$r1 = -0 ? "verdadero" : "falso";

$r2 = [] ? "verdadero" : "falso";

$r3 = "" ? "verdadero" : "falso";

$r4 = "0" ? "verdadero" : "falso";

$r5 = "00" ? "verdadero" : "falso";

$r6 = null ? "verdadero" : "falso";

$a7="Verdadero";
$r7 = $a7 ? "verdadero" : "falso";

$a8="Falso";
$r8 = $a8 == null ? "verdadero" : "falso";

$r9 = null == "0" ? "verdadero" : "falso";


$r11 = array() == null ? "verdadero" : "falso";


$r12 = array() == "0" ? "verdadero" : "falso";










echo PHP_EOL . "FIN";
?>