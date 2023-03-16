<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DWES-JS-B01-02</title>
</head>
<body style="font-family: monospace">

    <form action="<?php echo $_SERVER['PHP_SELF'] ?>"  method="get">
<?php
    $valorU = empty($_GET['u']) ? "VACIO": $_GET['u'];
    $valorA = empty($_GET['a']) ? "VACIO": $_GET['a'];
    $valorT = empty($_GET['t']) ? "VACIO": $_GET['t'];
    echo <<<MARCA
    <p>
        <label for="velocidadInicial">Introduzca velocidad inicial (m/s):</label>
        <input type="number" step="any" id="velocidadInicial" name="u" value="{$valorU}" />
    </p>
    <p>
        <label for="aceleracion">Introduzca aceleración del objeto (m/s^2):</label>
        <input type="number"  step="any" id="aceleracion" name="a" value="{$valorA}"  />
    </p>
    <p>
        <label for="tiempoTranscurrido">Introduzca tiempo transcurrido (s):</label>
        <input type="number"  step="any" id="tiempoTranscurrido" name="t" value="{$valorT}" />
    </p>
MARCA;

    if ( $valorU === "VACIO" || $valorA === "VACIO" || $valorT === "VACIO" )  {
        echo <<<MARCA
        <p style="color: red">Rellene todos los campos, por favor</p>
        <p id="velocidad">&nbsp;</p>
        <p id="espacio">&nbsp;</p>
MARCA;
    } else {
       
        echo "<p id='velocidad'>La velocidad (v) es: " .  ((float) $valorU + $valorA * $valorT) . "</p>";
        echo "<p id='espacio'>El espacio recorrido (s) es: " . ( (float) $valorU * $valorT + 0.5 * $valorA * ($valorT**2)) . "</p>";
    }
?>
        <button id="botonEnviar" type="submit">Enviar</button>      
        <button id="botonReset">Reset</button> 
    </form>
</body>
</html>