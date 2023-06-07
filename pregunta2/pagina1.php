<?php 
    session_start(); //Iniciamos una sesión en la que se guardarán los palíndromos inicialmente
    //Hacemos el autoload de las clases
    spl_autoload_register(function ($class) {
        require "./src/" . $class . ".php";
    });
    //Primero comprobamos si se ha enviado algo
    if(isset($_GET['frase'])){
        $frase = $_GET['frase'];
        $regex = '/^[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\s]+$/u';

        function esPalindromo($cadena){ //Cogemos la palabra y comprobamos si es palíndromo
            //Primero recibiremos la palabra, una vez la tengamos le quitaremos los espacios 
            //Después de quitarle los espacios, la guardaremos del revés en una variable
            //Si la variable al revés coincide con la normal, es palíndromo
            $cadenaSinEspacios = str_replace(' ','',$cadena); //La función str_replace recibe como primer parámetro lo que queremos sustituir, como segundo porqué y tercero la cadena

            $cadenaAlReves = strrev($cadenaSinEspacios); //La función strrev devuelve una cadena dada vuelta

            if($cadenaAlReves == $cadenaSinEspacios){
                return true;
            }else{
                return  false;
            }
            
        }
        //Ahora, si la cadena es palíndromo
        if(esPalindromo(($frase)) && preg_match($regex, $frase)){           
            $esPalindromo = 1;
        }else{
            $esPalindromo = 0;
        }
            $frase = $_GET['frase'];
            $usuario = $_SESSION['nombre'];

            $palindromo = new Palindromo;

            $palindromo->setUsuario($usuario);
            $palindromo->setFrase($frase);
            $palindromo->setPalindromo($esPalindromo);

            $palindromo->insertarPalindromo();
        }

?>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="" method="GET">
            <label for="frase">Introduce una frase con letras minúsculas y sin acentuar:</label>
            <input type="text" id="frase" name="frase" placeholder="Introduce una frase" required>   

            <label for="enviar"></label>
            <input type="submit" name="Enviar" id="enviar"/>
        </form>

        <!--A partir de aquí imprimiremos los palíndromos almacenados en la sesión -->
        <table border="1">
            <tr>
                <td>Palíndromo</td>
                <td>Frase</td>
            </tr>
            <?php 
              
             ?>
        <table>
    </body>
    
</html>
  