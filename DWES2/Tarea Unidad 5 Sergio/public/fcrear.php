<?php
//Iniciamos la sesión
session_start();
//Cargamos el autoload de composer
require '../vendor/autoload.php';

//Importamos Blade
use Philo\Blade\Blade;

//Rutas de las vistas y la caché
$views = '../views';
$cache = '../cache';

//Instanciamos Blade
$blade = new Blade($views, $cache);

//Creamos los datos del título y el encabezado
$titulo = 'Nuevo';
$encabezado = 'Crear Jugador';

//Si existe un error en la sesión lo asignamos a la variable $error que se mostrará en la vista vcrear.blade.php
if (isset($_SESSION['error'])) {
    $error = $_SESSION['error'];
    //Mostramos la vista vcrear.blade.php
    echo $blade
        ->view()
        ->make('vcrear', compact('titulo', 'encabezado', 'error'))
        ->render();
    unset($_SESSION['error']);
} else {
    echo $blade
        ->view()
        ->make('vcrear', compact('titulo', 'encabezado'))
        ->render();
}