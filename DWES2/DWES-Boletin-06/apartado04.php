<?php


$r = 65 | 33;

$r = 65 ^ 3;

$r = 0b01010101 ^ 0b10101010;

$r = 0b00000001<<1;

$r = (0b00000001<<1)<<1;

$r = ((0b00000001<<1)<<1)<<1;

$r = 0b00000001<<3;

$r = 0x80000000>>4;

echo PHP_INT_MAX . PHP_EOL;
echo PHP_INT_MIN . PHP_EOL;
echo PHP_INT_SIZE . PHP_EOL;

$r = 0xffffffff;
$r = 0xffffffffff;
$r = 0xffffffffffff;
$r = 0xffffffffffffff;
$r = 0xfffffffffffffff;
$r = 0x7fffffffffffffff;

$r = 0x7fffffffffffffff<<1;
$r = 0x7fffffffffffffff<<1 | 1;
$r = 0xffffffffffffffff;

$r = 0x8000000000000000;

$r = (int) 0xffffffffffffffff;

$r = (0x80000000 + 0x0fffffff);

$r = (0x80000000 + 0x0fffffff)|0;

$r = (int) (23 / 2);

$r = (int) (23 % 2);

$r = ~false;

$r = !false;

$r = !true;

$a = 10;
$r = ~$a === -$a - 1;

$r = (-0)*10;

$r = ~0;

$r = ~~(0);

$r = -0<<2>>2;

$r = -1>>1;

$r = -1>>6;

$r = -1<<63;




echo PHP_EOL . "FIN";
?>