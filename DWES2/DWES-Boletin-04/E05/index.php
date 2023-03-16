<?php
    function separarEnDigitos(int $numero) {
        $salida = '';
        $cerosIzquierda = '';
        $cerosIzquierda .= $numero < 1000000 ? '0    ': '';
        $cerosIzquierda .= $numero <  100000 ? '0    ': '';
        $cerosIzquierda .= $numero <   10000 ? '0    ': '';

        while ($numero > 0) {
            $salida = (int)($numero % 10) . $salida;
            $numero = (int)($numero / 10);
            if ( $numero != 0 ) {
                $salida = '    ' . $salida;
            }
        }
        return $cerosIzquierda . $salida;
    }
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DWES-JS-B04-05</title>
</head>
<body>
    <pre>
<?php
    $numerosAGenerar = 10;
    $numero = random_int(9999, 1000000);

    for ($fila = 1; $fila <= $numerosAGenerar; $fila++) {
        echo separarEnDigitos($numero) . '<br>';
        $numero = random_int(9999, 1000000);
    }
?>
    </pre>      
</body>
</html>