<?php
// Arrays

$a1 = (array) 4;

$a2 = (array) [4];

$a3 = (array) "4";

$a4 = (array) ("4" + 3);

// $a5 = (array) ([4] + 3);

$a6 = [4] . "hola";

$a7 = array ("H", "o", 6 => "l", "a");
echo implode("", $a7)  . PHP_EOL;

$a8 = ["H", "o", "l", "a"];
$a8[] = "s";
$a8[] = " a todas";
echo implode("", $a8)  . PHP_EOL;

$a9 = "Hola";
$b9 = $a9;
unset($a9);
echo $b9 . PHP_EOL;

$a10 = ("Hola")[2];

$a11 = (2345 . "")[2];

$a12 = [ [1, 2, 3, 4], ["a", "b"], ["x", "HOLA", "z"], [[1, 2 ,3], ['implode', 5, 6]]];
echo $a12[3][0][2] . PHP_EOL;
echo $a12[2][1][2] . PHP_EOL;
echo $a12[3][1][0]($a12[2]) . PHP_EOL;
// $funcion = $a12[3][1][0];
// $r12 = $funcion($a12[2]);

$a13 = array();
echo "hola" . $a13['noexiste'];


echo PHP_EOL . "FIN";
?>