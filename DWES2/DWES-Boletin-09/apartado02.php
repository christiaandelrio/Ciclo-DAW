<?php
// boolean

$a1 = true + true + true;

$a2 = 3 * true;

$a3 = (bool) 0;
$a3 = (bool) -0;
$a3 = (bool) +0;
$a3 = (bool) 0.0;
$a3 = (bool) NAN;

// $a4 = true / false;

$a5;
$b5a = $a5 == false;
$b5b = $a5 == true;

$a6 = (bool) [];

$a7 = (bool) "";

$a8 = (bool) "0";

$a9 = (bool) "-0";

$a10 = (bool) "+0";

$a11 = (bool) "0000";

$a12 = (bool) "0E10";

$a13 = (bool) "0.0";


$a14 = (bool) "false";

$a15 = (bool) 0.00000000000000000001;

$a16 = (bool) ~0;

$a17 = (bool) new stdClass;

echo PHP_EOL . "FIN";
?>