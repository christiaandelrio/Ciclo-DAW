<?php
// float 

echo PHP_FLOAT_EPSILON . PHP_EOL;

echo PHP_FLOAT_MAX;

echo PHP_FLOAT_MIN;


$a1 = 3.0000001;
$b1 = 3.0000021;
$miPrecision = 0.00001;
echo abs($a1 - $b1) < $miPrecision ? "Son iguales" : "Son distintos";
   


echo PHP_EOL . "FIN";
?>