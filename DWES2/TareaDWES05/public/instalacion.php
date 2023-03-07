<?php
session_start(); //Iniciamos la sesion
require '../vendor/autoload.php'; //Hacemos uso del autoload

use Philo\Blade\Blade;

$views = '../views';
$cache = '../cache';

$blade = new Blade($views,$cache);

$titulo ='Instalación';
$encabezado='Instalación de nuevos Datos';

echo $blade
    ->view()
    ->make('vinstalacion',compact('titulo','encabezado'));