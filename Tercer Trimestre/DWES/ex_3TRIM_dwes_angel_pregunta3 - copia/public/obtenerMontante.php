<?php

require '../vendor/autoload.php';

use Clases\Clases1\ClasesOperacionesService;

$codT = 3;

$objeto = new ClasesOperacionesService();

// funcion getMontante ----------------------------------------
$montante = $objeto->getMontante($codT);
echo $montante;