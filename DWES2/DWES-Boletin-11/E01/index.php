<?php 

include 'clases.php';

$instrumentos = [ new Guitarra(), new Timbal(), new Clarinete(), new Bajo(), new Trompeta()];

// Ejemplo 1 código polimórfico
foreach ($instrumentos as $instrumento) {
    $instrumento->afinar();
    echo "<br>";
}

echo "<br>" . "<br>";

// Ejemplo 2 código polimórfico
foreach ($instrumentos as $instrumento) {
    $instrumento->tocarNota(['Do', 'Re', 'Mi', 'Fa', 'Sol', 'La', 'Si'][random_int(0,6)] . ['', '#', 'b'][random_int(0,2)] . random_int(2,6));
    echo "<br>";
}

echo "<br>" . "<br>";

// Ejemplo de qué pasa con métodos que no pertenecen a la clase base
// Si el código no es totalmente polimórfico, cada vez que
// añadamos un nuevo instrumento a nuestra jerarquía de clases
// tendremos que revisar toda nuestra base de código que tenga
// código como el siguiente, que tiene que revisar qué tipo 
// de objeto es para saber como procesarlo.
// NOTA: si en vez de haber creado 3 métodos distintos cambiarCuerdas,
//  limpiarTubo, ajustarTensores hubieramos creado un único método
//   mantenerInstrumento (por ejemplo) no tendríamos este problema
foreach ($instrumentos as $instrumento) {
    if ($instrumento instanceof Cuerda) {
        $instrumento->cambiarCuerdas();
        echo "<br>";        
    }

    if ($instrumento instanceof Viento) {
        $instrumento->limpiarTubo();
        echo "<br>";        
    }

    if ($instrumento instanceof Percusion) {
        $instrumento->ajustarTensores();
        echo "<br>";        
    }

}

echo "<br>" . "<br>";    

// Ejemplo de qué pasa con métodos que no pertenecen a la clase base
// NOTA: IDEM que la anterior nota
foreach ($instrumentos as $instrumento)  {
    switch($instrumento::class) {
        case 'Viento':    
        case 'Clarinete': 
        case 'Trompeta': $instrumento->limpiarTubo(); break;
        case 'Cuerda':    
        case 'Guitarra':  
        case 'Bajo'    : $instrumento->cambiarCuerdas(); break;
        case 'Percusion': 
        case 'Timbal': $instrumento->ajustarTensores();break;
        default: throw new Exception('Instrumentos desconocido');
    }
    echo "<br>";
}

echo "<br>";

echo "Total instrumentos: " . Instrumento::totalInstrumentos() . "<br>";
echo "Total instrumentos Viento: " . Viento::totalInstrumentosViento() . "<br>";
echo "Total instrumentos Cuerda: " . Cuerda::totalInstrumentosCuerda() . "<br>";
echo "Total instrumentos Percusion: " . Percusion::totalInstrumentosPercusion() . "<br>";

?>