<?php 
function factorial1($numero) {
    $factorial = 1;

    for ($i = $numero; $i > 1; $i--) {
        $factorial *= $i;
    }
    return $factorial;
}

function factorial2($numero) {
    if ($numero === 0 || $numero === 1) {
        return 1;
    } else {
        return $numero * factorial2($numero - 1);
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="styles.css" /> 
    <title>DWES-PHP-B10-E01</title>
</head>
<body >
    <h1>Factorial de un número: resultados de evaluación de tiempo</h1>
    <table style="font-family: monospace">
        <caption>Resultados evaluación rendimiento funciones factorial1 y factorial2</caption>
        <thead>
            <tr>
                <th>número</th>
                <th>factorial1</th>
                <th>tiempo factorial1</th>
                <th>factorial2</th>
                <th>tiempo factorial2</th>
            </tr>
        </thead>

        <tbody>
        <pre>
<?php
    $valores = [5, 7, 10, 20];

    foreach($valores as $valor) {
        $resultado[$valor][0] = microtime(true);
        $resultado[$valor][1] = factorial1($valor);
        $resultado[$valor][2] = microtime(true);
        $resultado[$valor][3] = microtime(true);
        $resultado[$valor][4] = factorial2($valor);
        $resultado[$valor][5] = microtime(true);        
        echo "<tr>";
        echo "<td>" . $valor ."</td>";
        echo "<td>" . ( $resultado[$valor][1] ) . "</td>";
        echo "<td>" . ( $resultado[$valor][2] - $resultado[$valor][0] ) . "</td>";
        echo "<td>" . ( $resultado[$valor][4] ) . "</td>";
        echo "<td>" . ( $resultado[$valor][5] - $resultado[$valor][3] ) ."</td>";
        echo "</tr>";
    }
?>
        </pre>
        </tbody>
    </table>
</body>
</html>