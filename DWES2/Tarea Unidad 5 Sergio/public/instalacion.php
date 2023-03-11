<?php
//Este archivo es el que se va a encargar de mostrar la vista de instalación vinstalacion.blade.php

//Iniciamos la sesión
session_start();
//Importamos autoload.php
require '../vendor/autoload.php';

//Importamos Blade
use Philo\Blade\Blade;

//Le indicamos a Blade dónde están las vistas y dónde está el caché
$views = '../views';
$cache = '../cache';
//Creamos un objeto Blade
$blade = new Blade($views, $cache);

//Le pasamos los datos a la vista vinstalacion.blade.php
$titulo = 'Instalar';
$encabezado = 'Instalación de datos de ejemplo';

//Mostramos la vista vinstalacion.blade.php con los datos que le hemos pasado para el título y el encabezado
echo $blade
    ->view()
    ->make('vinstalacion', compact('titulo', 'encabezado'))
    ->render();
