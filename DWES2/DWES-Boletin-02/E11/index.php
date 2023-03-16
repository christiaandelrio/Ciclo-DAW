<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="script.js" defer></script>
    <title>DWEC-JS-B02-11</title>
</head>
<body style="font-family: monospace">

    <form action="<?php echo $_SERVER["PHP_SELF"]; ?>" method="get">

        <div class="entrada">
            <p>
                <label for="numeroBinario">Introduzca un número binario de 5 dígitos máximo:</label>
<?php
    $numeroDecimal = '';
    if ( !isset($_GET['numeroBinario']) ) {
       echo "<input type='text' id='numeroBinario' name='numeroBinario' />";
       echo "</p></div>";
    } else if ( empty($_GET['numeroBinario'])) {
        echo "<input type='text' id='numeroBinario' name='numeroBinario' />";
        echo "<span style='color: red'> <-- introduzca el número binario, por favor</span>";
        echo "</p></div>";
    } else {
        echo "<input type='text' id='numeroBinario' name='numeroBinario' value=\"{$_GET['numeroBinario']}\" />";
        echo "</p></div>";        
        $numeroBinario = $_GET['numeroBinario'];
        $numeroDecimal = 0;
        $longitudNumeroBinario = strlen($numeroBinario);

        if ($longitudNumeroBinario > 5) {
            $numeroDecimal = "<span style='color: red'>El número es demasiado largo: introduzca 5 dígitos binarios máximo, por favor</span>";
        } else {
            $numeroDecimal = 0;
            for($i = ($longitudNumeroBinario - 1); $i >= 0; $i--) {
                if ( $numeroBinario[$i] != "1" && $numeroBinario[$i] != "0" ) {
                    $numeroDecimal =  "<span style='color: red'>El número introducido tiene en su posición " . 
                         (( $longitudNumeroBinario - 1) - $i) . 
                         " el caracter inválido " . $numeroBinario[$i] . "</span>";
                } else {
                    $numeroDecimal += ($numeroBinario[$i] * (1<<(($longitudNumeroBinario - 1) - $i)));
                }
            }
        }
    }
?>
        <div class="salida">
            <p id="numeroDecimal">El número decimal correspondiente es:&nbsp;<?php echo "{$numeroDecimal}";?></p>
        </div> 
            <button id="botonEnviar">Enviar</button>      
            <button id="botonReset">Reset</button> 
    </form>
</body>
</html>