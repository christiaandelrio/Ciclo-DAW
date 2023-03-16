<?php
$contador = 0;
// Vamos a utilizar una variable global para contar el número de pasos
// aunque esto no se pide en el enunciado, puede ser ilustrativo.
// Por otro lado, también podríamos utilizar otro parámetro dentro de 
// la firma de la función para contar el número de pasos
function torresDeHanoi (int $discos, int $posteInicial, int $posteFinal, int $posteTemporal) {
    global $contador;
    if ($discos === 1) {
        ++$contador;
        echo "Paso {$contador}: {$posteInicial} --> {$posteFinal}" . "<br>";
    } else {
        torresDeHanoi($discos - 1, $posteInicial, $posteTemporal, $posteFinal);
        ++$contador;
        echo "Paso {$contador}: {$posteInicial} --> {$posteFinal}". "<br>";
        torresDeHanoi($discos - 1, $posteTemporal, $posteFinal, $posteInicial);
    }
} ?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DWES-PHP-B10-E02</title>
</head>
<body >
    <h1>Torres de Hanoi</h1>
    <img src="torresHanoi.png" alt="Imagen juego milenario Torres de Hanoi"/>
    
    <form action="<?php echo $_SERVER['PHP_SELF'] ?>"  method="get">
<?php
    $numeroDiscos = empty($_GET['numeroDiscos']) ? "VACIO": $_GET['numeroDiscos'];
    echo <<<MARCA
    <div id="entrada">
        <p>
            <label for="numeroDiscos">Introduzca el número de discos a mover del poste 1 al poste 3:</label>
            <input type="number" id="numeroDiscos" name="numeroDiscos" />
        </p>
    </div>         
MARCA;

    if ( $numeroDiscos === "VACIO"  )  {
        echo <<<MARCA
        <p style="color: red">Rellene todos los campos, por favor</p>
MARCA;
    } else {
        torresDeHanoi($numeroDiscos, 1, 3, 2);
    }
?>
        <button id="botonEnviar" type="submit">Enviar</button>      
        <button id="botonReset">Reset</button> 
    </form>
</body>
</html>