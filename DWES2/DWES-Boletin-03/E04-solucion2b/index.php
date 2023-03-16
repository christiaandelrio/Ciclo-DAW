<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DWES-JS-B03-04-S2b</title>
</head>
<body >

    <form action="<?php echo $_SERVER['PHP_SELF'] ?>"  method="get">
<?php
    $entero = empty($_GET['entero']) ? "": intval($_GET['entero']);
    echo <<<MARCA
    <p>
        <label for="entero">Introduzca un entero impar positivo de 1 a 20:</label>
        <input type="number"  min="1" max="20" step="2"  id="entero" name="entero" value="{$entero}" />
    </p>
MARCA;
    $segundaVez = false;
    if ( !isset($_GET['primeraVez']) ) {
        echo '<input type="hidden"  name="primeraVez" value="true" />';
    } else {
        $segundaVez = true;
    }

    if ($entero === "" || $entero < 1 || $entero > 20 || ($entero % 2 == 0))  {  // aunque el html se encarga de comprobar rango, no hace daño probar aquí
         if ($segundaVez) {
            echo <<<MARCA
            <p style="color: red">Introduzca el entero IMPAR solicitado (1-20), por favor</p>
            <p id="digitos">&nbsp;</p>
MARCA;
        }
    } else {
        echo "<pre style=\"font-family: monospace\">";
        $medio = floor($entero / 2) + 1;
        $siAsterisco = 0;  
        // Dibujar rombo en una sola tirada:
        // NOTA: en vez de un espacio, imprimiré un "." (punto) para que puedas
        //  apreciar las diferencias entre distintas soluciones.
        for ($fila = 1; $fila <= $entero; $fila++) {
            for ($columna = 1; $columna <= $medio + $siAsterisco; $columna++) {
                if ( ($columna >= $medio - $siAsterisco) && ($columna <= $medio + $siAsterisco) ) {
                    echo '*';
                } else {
                    echo '.';
                }
            }
            $fila < $medio ? ++$siAsterisco : --$siAsterisco;
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