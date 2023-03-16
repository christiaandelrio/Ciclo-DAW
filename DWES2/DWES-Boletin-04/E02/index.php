<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DWES-JS-B04-02</title>
</head>
<body>
    <div class="randoms">
        <pre>
<?php
    $numeroFilas = rand(1, 100);
    $primerNumeroFila = rand(1, 50);
    for ($fila = 1; $fila < $numeroFilas; $fila++) {
        echo $primerNumeroFila . ' ';
        for ($elementoFila = 1; $elementoFila < $primerNumeroFila; $elementoFila++) {
            echo rand(1, 50) . " ";
        }
        echo '<br>';
        $primerNumeroFila = rand(1, 50);
    }
?>
        </pre>
    </div> 
</body>
</html>