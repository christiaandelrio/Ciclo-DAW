<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DWES-JS-B02-08</title>
</head>
<body >

    <form action="<?php echo $_SERVER['PHP_SELF'] ?>"  method="get">
<?php
    $entero = empty($_GET['entero']) ? "": intval($_GET['entero']);
    echo <<<MARCA
    <p>
        <label for="numero">Introduzca un entero positivo de 1 a 20:</label>
        <input type="number"  min="1" max="20" step="1"  id="numero" name="entero" value="{$entero}" />
    </p>
MARCA;
    $segundaVez = false;
    if ( !isset($_GET['primeraVez']) ) {
        echo '<input type="hidden"  name="primeraVez" value="true" />';
    } else {
        $segundaVez = true;
    }

    if ( $entero === "" || $entero < 1 || $entero > 20 )  {  // aunque el html se encarga de comprobar rango, no hace daño probar aquí
        if ($segundaVez) {
            echo <<<MARCA
            <p style="color: red">Introduzca el entero solicitado (1-20), por favor</p>
            <p id="digitos">&nbsp;</p>
MARCA;
        }
    } else {
        echo "<pre style=\"font-family: monospace\">";
        for ($fila = 1; $fila <= $entero; $fila++) {
            for ($columna = 1; $columna <= $entero; $columna++) {
                echo '*';
            }
        echo '<br>';
        }
        echo "</pre>";
    }
?>
        <button id="botonEnviar" type="submit">Enviar</button>      
        <button id="botonReset">Reset</button> 
    </form>
</body>
</html>