<?php
$cadenas = ["domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado"];

$dia = 29;
$mes = 1;
$anno = 2023;

// 0 = domingo; .... 7 = sábado
$diaSemana = (int) (( (int) (23 * $mes / 9) + (int) ($dia + 4) +  (int) ($anno / 4) -  (int) ($anno / 100) +  (int) ($anno / 400) ) % 7 );

echo "El día de la semana es " . $cadenas[$diaSemana];


echo PHP_EOL . "FIN";
?>