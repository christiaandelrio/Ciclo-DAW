<?php
session_start(); //Iniciamos la sesion
require '../vendor/autoload.php'; //Hacemos uso del autoload

use Clases\Jugador;

$jugador = new Jugador();

if($jugador->existenJugadores()){ //Si devuelve true, nos llevará a jugadores.php porque existen
    $jugador = null;
    header('Location: jugadores.php');
}else{                           //En caso contrario nos llevará a instalacion.php para crear unos datos de ejemplo
    $jugador = null;
    header('Location:instalacion.php');
}