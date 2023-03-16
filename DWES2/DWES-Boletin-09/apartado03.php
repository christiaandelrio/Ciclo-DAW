<?php
// int

$a1 = is_integer(PHP_INT_MAX);

$a2 = is_integer(PHP_INT_MAX + 1);

$a3 = is_float(PHP_INT_MAX + 1);

$a4 = is_integer(PHP_INT_MIN - 1);

$a5 = is_float(PHP_INT_MIN - 1);

$a6 = is_integer((PHP_INT_MAX + 1) - 1);

$a7 = is_integer((PHP_INT_MAX - 1) + 1);

echo "En esta plataforma un entero en PHP tiene " . PHP_INT_SIZE * 8 . " bits";

echo PHP_EOL . "FIN";
?>