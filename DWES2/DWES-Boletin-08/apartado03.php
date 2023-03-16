<?php
// Igualdad <=>

$r1 = 1 <=> 3;

$r2 = 1 <=> -1;

$r3 = 3 <=> 3;

$r4 = null <=> null;

$r5 = "" <=> "";

$r6 = "abc" <=> "Abc";

$r7 = "Abc" <=> "abc";

$r8 = "" <=> [];

$r9 = [] <=> "";


$r10 = (1 <=> 2) <=> "";

$r11 = false <=> true;

$r12 = false <=> null;

$r13 = "0" <=> null;
// pero ojo: "0" == null es false

$r14 = PHP_INT_MAX <=> new stdClass;

$r15 = [3, 4] <=> new stdClass;

$r16 = [3, 4] <=> [4, 3];

$r17 = [3, 3] <=> [3, 3];

$r18 = NAN <=> NAN;

echo PHP_EOL . "FIN";
?>