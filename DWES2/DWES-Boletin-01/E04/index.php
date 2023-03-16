<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DWES-JS-B01-04</title>
</head>
<body style="font-family: monospace">

    <form action="<?php echo $_SERVER['PHP_SELF'] ?>"  method="get">
<?php
    $entero1 = empty($_GET['entero1']) ? "": $_GET['entero1'];
    $entero2 = empty($_GET['entero2']) ? "": $_GET['entero2'];
    $entero3 = empty($_GET['entero3']) ? "": $_GET['entero3'];
    echo <<<MARCA
    <p>
        <label for="numero1">Introduzca primer entero:</label>
        <input type="number"  id="numero1" name="entero1" value="{$entero1}" />
    </p>
    <p>
        <label for="numero2">Introduzca segundo entero:</label>
        <input type="number"  id="numero2" name="entero2" value="{$entero2}"  />
    </p>
    <p>
        <label for="numero3">Introduzca tercer entero (s):</label>
        <input type="number" id="numero3" name="entero3" value="{$entero3}" />
    </p>
MARCA;

    if ( $entero1 === "" || $entero2 === "" || $entero3 === "" )  {
        echo <<<MARCA
        <p style="color: red">Rellene todos los campos, por favor</p>
        <p id="suma">&nbsp;</p>
        <p id="media">&nbsp;</p>
        <p id="producto">&nbsp;</p>
        <p id="mayor">&nbsp;</p>
        <p id="menor">&nbsp;</p>   
MARCA;
    } else {
        $mayor = $entero1;
        if ( $entero1 < $entero2 ) {
            $mayor = $entero2;
            if ( $entero2 < $entero3 ) {
                $mayor = $entero3;
            }
        } else {
            if ( $entero1 < $entero3 )
                $mayor = $entero3;
        }

        $menor = $entero1;
        if ( $entero2 < $entero1 ) {
            $menor = $entero2;
            if ( $entero3 < $entero2 ) {
                $menor = $entero3;
            }
        } else {
            if ( $entero3 < $entero1 )
                $menor = $entero3;
        }

        echo "<p id='suma'>La suma es: " . ($entero1 + $entero2 + $entero3)  . "</p>";
        echo "<p id='media'>La media es: " . ( ($entero1 + $entero2 + $entero3)/3 ) . "</p>";
        echo "<p id='producto'>El producto es: " . ($entero1 * $entero2 * $entero3)  . "</p>";
        echo "<p id='mayor'>El mayor es: " . $mayor  . "</p>";
        echo "<p id='menor'>El menor es: " . $menor  . "</p>";
    }
?>
        <button id="botonEnviar" type="submit">Enviar</button>      
        <button id="botonReset">Reset</button> 
    </form>
</body>
</html>