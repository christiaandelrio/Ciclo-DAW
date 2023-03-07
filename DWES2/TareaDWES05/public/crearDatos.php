<?php
session_start(); //Iniciamos la sesion
require '../vendor/autoload.php'; //Hacemos uso del autoload

use Clases\Jugador; //Necesitamos la clase para crear los jugadores
use Faker\Factory;  //Y utilizaremos Faker para generar los datos aleatorios de prueba

$faker = Factory::create("es_ES");
for($i=0;$i<15;$i++){
    $jugador = new Jugador();

    $jugador->setNombre($faker->firstName);
    $jugador->setApellidos($faker->lastName);
    $jugador->setDorsal($faker->unique()->numberBetween(1,99));
    $jugador->setPosicion($faker->numberBetween(1,6));
    $jugador->setBarcode($faker->unique->ean13);
    $jugador->create();
    $jugador=null; //liberar espacio que ocupa el objeto en cada vuelta del bucle
}

$_SESSION["correcto"]="Los datos de ejemplo se han generado correctamente";
header('Location:jugadores.php');