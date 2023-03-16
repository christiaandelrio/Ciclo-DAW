<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DWES-JS-B01-10</title>
</head>
<body style="font-family: monospace">
    <div class="recuadro">
        <label for="cajaTexto">Cuadrados y cubos:<br>
            <textarea id="cajaTexto" name="textarea" rows="13" cols="50">
<?php
echo <<<MARCA
Número\t\tCuadrado\t\t Cubo
=======\t\t========\t\t=======
    0  \t\t      0 \t\t      0
    1  \t\t      1 \t\t      1
    2  \t\t      4 \t\t      8
    3  \t\t      5 \t\t     27
    4  \t\t     16 \t\t     64
    5  \t\t     25 \t\t    125
    6  \t\t     36 \t\t    216
    7  \t\t     49 \t\t    343
    8  \t\t     64 \t\t    512
    9  \t\t     81 \t\t    729
   10  \t\t    100 \t\t   1000
MARCA;
?>
            </textarea>
        </label>
    </div> 
  
</body>
</html>