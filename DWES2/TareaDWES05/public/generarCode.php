<?php
require '../vendor/autoload.php';

use Clases\Jugador;
use Faker\Factory;
use Philo\Blade\Blade;
$views = '../views';
$cache = '../cache';
$blade = new Blade($views, $cache);

$faker = Factory::create('es_Es');
$jugador=new Jugador();


do{                                //Se ejecuta por lo menos una vez creando un código de barras con faker, si existeBarcode devuelve false, sale del bucle devolviendo ese código, sino genera uno nuevo
    $barcode = $faker->ean13;      //hasta que salga uno que no exista
}while($jugador->existeBarcode($barcode));
$jugador = null;


$titulo = 'Nuevo';
$encabezado = 'Crear Jugador';


if(isset($_SESSION["creado"])){
    $mensaje = $_SESSION['creado'];
}else{
    $mensaje = "";
}

echo $blade
    ->view()
    ->make('vcrear', compact('titulo', 'encabezado', 'barcode','mensaje')) //pasamos titulo, encabezado y el código de barras creado
    ->render();