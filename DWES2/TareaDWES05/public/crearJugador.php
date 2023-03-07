<?php
session_start();
require '../vendor/autoload.php';

use Clases\Jugador;

if(empty($_POST["nombre"])|| empty($_POST["apellidos"])){
    $_SESSION["error"] = "Nombre y apellidos no pueden estar vacíos";
    header('Location:fcrear.php');
}

//Guardo los datos recibidos del formulario
$nombre = $_POST['nombre'];
$apellidos = $_POST['apellidos'];
$dorsal = (int)$_POST['dorsal'];
$posicion = $_POST['posicion'];
$barcode = $_POST['barcode'];

$jugador = new Jugador();

if(($jugador)->existeDorsal($dorsal)){ //Comprobamos si existe algún dorsal igual
    $jugador = null;

    echo "El dorsal ya existe, elige otro";
}else{
    
    $jugador->setNombre($nombre);
    $jugador->setApellidos($apellidos);
    $jugador->setPosicion($posicion);
    if ($dorsal != 0) $jugador->setDorsal($dorsal);
    $jugador->setBarcode($barcode);
    $jugador->create();
    $_SESSION['creado'] = "Jugador Creado con exito"; //Si se creó con éxito, guardamos el mensaje en la variable de sesión para mostrarlo en el formulario
}
header('Location:fcrear.php');