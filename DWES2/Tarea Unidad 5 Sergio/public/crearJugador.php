<?php
//Iniciamos la sesión
session_start();
//Cargamos el autoload de composer
require '../vendor/autoload.php';

//Importamos la clase Jugador
use Clases\Jugador;

//Creamos la función para mostrar errores
function error($text){
        //Asignamos el error a la sesión y redirigimos a fcrear.php (a la misma página)
        $_SESSION['error'] = $text;
        header('Location:fcrear.php');
        die();
}

//Función para validar que el nombre y los apellidos no están vacíos
function validarNombreApellidos(&$nom, &$apell)
{
        //comprobamos que no esten vacios ni nombre ni apellidos
        if (strlen($nom) == 0 || strlen($apell) == 0) {
                error("Debe introducir nombre y apellidos");
        }
}

//Función para validar que el barcode no está vacío
function validarBarcode(&$barcode)
{
        //comprobamos que no esté vacío el barcode
        if (strlen($barcode) == 0) {
                error("Debe introducir un código de barras");
        }
}

//Creamos las variables con la información del formulario
$nombre = trim($_POST['nombre']);
$apellidos = trim($_POST['apellidos']);
$dorsal = (int)$_POST['dorsal'];
$posicion = $_POST['posicion'];
$barcode = $_POST['barcode'];

//Ejecutamos la función de validación de nombre y apellidos:
validarNombreApellidos($nombre, $apellidos);
//Ejectutamos la función de validación del barcode
validarBarcode($barcode);

//Creamos un objeto Jugador
$jugador = new Jugador();

//Comprobamos si el dorsal existe en la base de datos
if ($jugador->dorsalExiste($dorsal)) { 
        $jugador = null;
        error("Ya existe un dorsal con ese número");
}

//Si se han pasado todas las validaciones creamos el jugador en la base de datos
$jugador->setNombre($nombre);
$jugador->setApellidos($apellidos);
$jugador->setPosicion($posicion);
if ($dorsal != 0) $jugador->setDorsal($dorsal);
$jugador->setBarcode($barcode);
$jugador->create();

//Almacenamos el mensaje de éxito en la sesión
$_SESSION['mensaje'] = "Se ha creado el jugador $nombre satisfactoriamente";

//Redirigimos a la misma página fcrear.php
header('Location:jugadores.php');
