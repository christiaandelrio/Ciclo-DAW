<?php
session_start();
require '../vendor/autoload.php';


use Philo\Blade\Blade;

$views = '../views';
$cache = '../cache';
$blade = new Blade($views, $cache);

$titulo = 'Crear Nuevo Jugador';
$encabezado = 'Crear Jugador';

if(isset($_SESSION["creado"])){
    $mensaje = $_SESSION['creado'];  //Si se creó con éxito, guardamos el mensaje en una variable y lo pasamos a la vista para mostrarlo
}else{
    $mensaje = "";
}

echo $blade
    ->view()
    ->make('vcrear', compact('titulo', 'encabezado','mensaje'))  //En compact le paso las variables titulo, encabezado, y mensaje a la vista vcrear
    ->render();

    

