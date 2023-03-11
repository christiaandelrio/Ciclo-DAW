<?php
//Este archivo es el punto de entrada de la aplicación. Comprueba si hay datos en la base de datos, si los hay redirige a la vista de jugadores, si no los hay se redirige a la vista de instalación.
//Importamos el autoload de composer
require '../vendor/autoload.php';

//Importamos la clase Jugaror de src/Jugador.php
use Clases\Jugador;

//Creamos un objeto de la clase Jugador
$jugador = new Jugador();
//Llamamos al método tieneDatos() que comprueba si hay datos en la base de datos
//Si hay datos redirigimos a la vista de jugadores
if ($jugador->tieneDatos()) {
    //Cerramos la conexxión a BD
    $jugador = null;
    //Redirigimos al listado de jugadores
    header('Location: jugadores.php');
//Si no hay datos redirigimos a la vista de instalación
} else {
    //Cerramos la conexión a BD
    $jugador = null;
    //Redirigimos a la vista de instalación de datos de prueba
    header('Location: instalacion.php');
}