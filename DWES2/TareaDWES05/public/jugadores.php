<?php
session_start();
require '../vendor/autoload.php';

use Clases\Jugador;
use Milon\Barcode\DNS1D;
use Philo\Blade\Blade;

$views = '../views';
$cache = '../cache';

$blade = new Blade($views, $cache);
$d = new DNS1D();
$d->setStorPath($cache); //establecemos la biblioteca de almacenamiento en cache para DNS1D
$titulo = 'Listado de Jugadores';
$encabezado = 'Listado de Jugadores';
$jugadores = (new Jugador())->mostrarJugadores();


if(isset($_SESSION["correcto"])){

    $mensaje = $_SESSION["correcto"];
    unset($_SESSION["correcto"]);

    echo $blade
    ->view()
    ->make('vjugadores', compact('titulo', 'encabezado', 'jugadores', 'd','mensaje'))  //En compact le paso las variables titulo, encabezado, jugadores y d a la vista vJugadores
    ->render();

    
}else{

    echo $blade
        ->view()
        ->make('vjugadores', compact('titulo', 'encabezado', 'jugadores', 'd'))  //En compact le paso las variables titulo, encabezado, jugadores y d a la vista vJugadores
        ->render();
}