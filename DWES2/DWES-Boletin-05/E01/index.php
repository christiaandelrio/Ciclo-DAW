<?php
    // importante recordar que en codificación de caracteres UTF-8, hay caracteres que ocupan 1 byte, otros
    //  caracteres ocupan 2 bytes, etc: NO podemos recorrer la cadena de texto con indice de string 
    //  $cadena[$i] : que utilizar funciones Multibyte (mb) string
    function codificarMensaje (string $mensaje, string $codigo, string $cadenaReferencia): string {
        $cifrado  = $mensaje;
        $posicion = 0; 

        for($i = 0; $i < mb_strlen($mensaje); $i++ ) {            
            $posicion = mb_strpos($cadenaReferencia, mb_substr($mensaje, $i, 1));   // buscamos la posición del carácter de codificación 

            if ( $posicion === false ) {  // false = caracter no está en cadenaReferencia
                return "Caracter en mensaje no soportado: '" . mb_substr($mensaje, $i, 1) . "' : PROCESO ABORTADO";
            } else if ( mb_substr($codigo, $posicion, 1) === "-" ) {
                ;
            } else if ( mb_substr($codigo, $posicion, 1) === '.' ) {
                // cadena antes del carácter a borrar =  mb_substr($cifrado, 0, $i)
                // cadena después del carácter a borrar = mb_substr($cifrado, $i+1, strlen($cifrado))
                // el caracter "^" queda "reservado" para indicar borrado posterior
                $cifrado = mb_substr($cifrado, 0, $i) .  "^" . mb_substr($cifrado, $i+1, mb_strlen($cifrado));
            } else {      
                // ver comentarios encima...mismo razonamiento
                $cifrado = mb_substr($cifrado, 0, $i) .  mb_substr($codigo, $posicion, 1) .  mb_substr($cifrado, $i+1, strlen($cifrado));
            }
        }

        $cifrado = str_replace('^', '', $cifrado);  // Eliminamos los caracteres codificados con "."
      
        return $cifrado;   
    }

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="script.js" defer></script>
    <title>DWES-JS-B05-01</title>
    <style>
        .monoespacio { font-family: monospace; }
    </style>
</head>
<body>
    <form action="<?php echo $_SERVER['PHP_SELF'] ?>"  method="get">
<?php
    $cadenaReferencia = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789';
                    
    $segundaVez = false;
 
    if ( !isset($_GET['primeraVez']) ) {
        echo '<input type="hidden"  name="primeraVez" value="true" />';
    } else {
        $segundaVez = true;
    }

    if ( !isset($_GET['codigo']) ) {
        $codigo  = 'fkdjfklhgosidfowheoihfosfdlkfjlkdfjoiwejf2343sd987238479lkdjflk4';
    } else {
        $codigo = $_GET['codigo'];
    }

    if ( empty($_GET['mensaje']) )  {  
        if ($segundaVez) {
            echo <<<MARCA
            <p style="color: red">Rellene el campo mensaje, por favor</p>
            <input type="hidden"  name="primeraVez" value="true" />
MARCA;
        }   
    } 
    
    $mensaje = empty($_GET['mensaje']) ? '' : $_GET['mensaje'];


    echo <<<MARCA
        <p>
        <label for="mensaje">Mensaje:
            <input type="text" id="mensaje" size="100" name="mensaje" value='{$mensaje}' class="monoespacio"/>
        </label>
        </p>
        <p class="monoespacio">
        <label for="codigo">Código: 
            <input type="text" id="codigo" size="100" name="codigo" value='{$codigo}' class="monoespacio"/>
        </label><br>
        <label for="cadenaReferencia">Refer.: 
            <input type="text" id="cadenaReferencia" size="100" name="cadenaReferencia" value='{$cadenaReferencia}' readonly  class="monoespacio"/>
        </label>
        </p>
    </div>
MARCA;
 
 
   if ( !empty($_GET['mensaje']) && (mb_strlen($codigo) !== mb_strlen($cadenaReferencia)) )  {  
        echo <<<MARCA
        <p style="color: red">La cadena código debe tener la misma longitud que la de Refer., por favor</p>
MARCA;
    } else {
        
        echo "<p>El mensaje codificado es: " . codificarMensaje($mensaje, $codigo, $cadenaReferencia) . "</p>";
    }
?>
        <button id="botonEnviar" type="submit">Enviar</button>      
        <button id="botonReset">Reset</button> 
    </form>
</body>
</html>