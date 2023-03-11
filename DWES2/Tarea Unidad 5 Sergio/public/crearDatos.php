<!-- Este archivo crea los datos de la base de datos de ejemplo. Se ejecuta desde la vista de instalación "vinstaslacion.blade.php". -->

<?php
// Iniciamos una sesión
session_start();
// Cargamos el autoload de composer
require '../vendor/autoload.php';

//Importamos la clase Jugador que está en "src/Jugador.php"
use Clases\Jugador;
//Importamos la clase Faker para crear datos de ejemplo
use Faker\Factory;

//Creamos un objeto y llámamos al método borrarTodo() que lo que hace es borrar todos los datos de la tabla jugadores
$jugador = (new Jugador())->borrarTodo();
//Cerrramos la conexión a BD
$jugador = null;
//Creamos un objeto Faker para crear datos de ejemplo
$faker = Factory::create("es_ES");
//Creamos 15 jugadores con datos de ejemplo haciendo uso de Faker
for ($i = 0; $i < 15; $i++) {
    //Instanaciamos un objeto de la clase jugador
    $jugador=new Jugador();
    //Llamamos a los métodos set para asignar los datos de ejemplo
    $jugador->setNombre($faker->firstName);
    $jugador->setApellidos($faker->lastName." ".$faker->lastName);
    $jugador->setDorsal($faker->unique()->numberBetween(1,25));
    $jugador->setPosicion($faker->numberBetween(1, 6));
    $jugador->setBarcode($faker->unique->ean13);
    //Llamamos al método create() para crear y guardar el jugador en la base de datos
    $jugador->create();
    //CErramos la conexión a BD
    $jugador = null;
}
//Agregamos el mensaje de éxito a la sesión y redirigimos a la vista del listado de jugadores donde mostraremos este mensaje
$_SESSION['mensaje'] = 'Datos de Ejemplo Creados Correctamente';
header('Location:jugadores.php');