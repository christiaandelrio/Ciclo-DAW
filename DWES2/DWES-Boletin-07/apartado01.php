<?php 
// Strings

$a1 = null;
$b1 = null . "hola"  . $b1;

$a2 = "Caña";
echo strlen($a2) . PHP_EOL;
echo $a2[0] . " ; " . $a2[1] .  " ; " .  $a2[2] . " ; " . $a2[3] . " ; " . $a2[4] . " ; " . $a2[5];

$a3 = "Hola";
echo $a3["0"] . " ;  " . $a3[1.0]  .  " ; " .  $a3["2"] . " ; " . $a3["003"] ; 


$a4 = "Hola"[2];

$a5 = "Hola ";
$a5[] = "10"; 

$a6 = "10.37 hola";
$r6 = 10 + $a6;

$a7 = "2E1" + "030" + "50 leches" + "10E3";

$a8 = "aquella mañana era uNa mañana cualquiera; no obstante no lo fue.";
echo $a8["2E1" + 1];

$a9 = "aquella mañana era uNa mañana cualquiera; no obstante no lo fue.";
echo $a9["2E1"];

$b10 = "HOLA";
$c10 = strlen($b10);
$nombre = "c10";
$a10 = <<<MARCA
La cadena "$b10" tiene una longitud de {${$nombre}}.\n
Caracteres Unicode \u{0f3a} \u{0065}.
MARCA;
echo $a10 . PHP_EOL;

echo ("2E2" == "200" ? "Son iguales": "Son distintos");


echo PHP_EOL . "FIN";
?>