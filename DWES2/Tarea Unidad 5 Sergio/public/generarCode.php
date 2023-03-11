<?php
//Importamos autoload
require '../vendor/autoload.php';

//Importamos la clase Jugador
use Clases\Jugador;
//Importamos Faker
use Faker\Factory;
//Importamos Blade
use Philo\Blade\Blade;

//Rutas de las vistas y la caché
$views = '../views';
$cache = '../cache';

//Instanciamos Blade
$blade = new Blade($views, $cache);

//Instanciamos Faker
$faker = Factory::create('es_Es');

//Creamos un objeto Jugador
$jugador=new Jugador();

//Creamos un código de barras, siempre y cuando no exista en la base de datos
do{
    $barcode = $faker->ean13;
}while($jugador->barcodeExiste($barcode));
//Hacemos null el objeto Jugador
$jugador = null;

//Creamos los datos del título y el encabezado
$titulo = 'Nuevo';
$encabezado = 'Crear Jugador';

//Mostramos la vista vcrear.blade.php con el $titulo, el $encabezado y el $barcode generado
echo $blade
    ->view()
    ->make('vcrear', compact('titulo', 'encabezado', 'barcode'))
    ->render();