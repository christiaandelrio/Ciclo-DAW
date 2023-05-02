<?php
require '../vendor/autoload.php';
use Clases\Pub_gestdocente;
use Clases\wstitulosuni;

$puerta=new Pub_gestdocente();
$service=new wstitulosuni('es','2023'); 
$respuesta=$puerta->wstitulosuni($service);
$listaTitulos=$respuesta->getWstitulosuniResult();
echo "Número de títulos: ".$listaTitulos->count()."<br>";

//Recorremos los titulos e imprimimos atributos con getters
for($i=0; $i<$listaTitulos->count();$i++){
    $titulo=$listaTitulos->current();
    echo "Nº de título :".($i+1)."<br>".PHP_EOL;
    echo "Código de título: ".$titulo->getCodigo()."<br>".PHP_EOL;
    echo "Nombre título: ".$titulo->getNombre()."<br>".PHP_EOL;
    $listaTitulos->next();
}

