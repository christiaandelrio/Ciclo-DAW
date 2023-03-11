<?php
//Iniciamos la sesión
session_start();
//Importamos autoload.php
require '../vendor/autoload.php';

//Importamos la clase Jugador de src/Jugador.php
use Clases\Jugador;
//Importamos Barcode
use Milon\Barcode\DNS1D;
//Importamos Blade
use Philo\Blade\Blade;

//Le indicamos a Blade dónde están las vistas y dónde está el caché
$views = '../views';
$cache = '../cache';
//Creamos un objeto Blade
$blade = new Blade($views, $cache);
//Creamos un objeto Barcode
$d = new DNS1D();
$d->setStorPath($cache);

//Creamos los datos del título y el encabezado que vamos a mostrar en la vista
$titulo = 'Jugadores';
$encabezado = 'Listado de Jugadores';
//Creamos un objeto Jugador y llamamos al método listadoJugadores() para obtener el listado de jugadores de la base de datos
$jugadores = (new Jugador())->listadoJugadores();

//Si hay algún mensaje en la sesión creamos la variable $mensaje que luego usaremos en la vista
if (isset($_SESSION['mensaje'])) {
    $mensaje = $_SESSION['mensaje'];
    unset($_SESSION['mensaje']); //Borramos el mensaje tras visualizarlo
    //Mostramos la vista vjugadores.blade.php con los datos de los jugadores y el mensaje
    echo $blade
        ->view()
        ->make('vjugadores', compact('titulo', 'encabezado', 'jugadores', 'd', 'mensaje'))
        ->render();
} else {
    echo $blade
        ->view()
        ->make('vjugadores', compact('titulo', 'encabezado', 'jugadores', 'd'))
        ->render();
}
