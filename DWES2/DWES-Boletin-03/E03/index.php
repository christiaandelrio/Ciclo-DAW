<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="script.js" defer></script>
    <link rel="stylesheet" href="estilos.css" /> 
    <title>DWES-JS-B03-03</title>
</head>
<body >

    <div class="salida" style="font-family: monospace">
        <ul>
            <li>
                <label for="cajaTextoA">(A)<br>
                    <textarea id="cajaTextoA" name="textarea" rows="12" cols="15"><?php
                    $ancho = 10;
                    for ($fila = 1; $fila <= $ancho; $fila++) {
                        for ($columna = 1; $columna <= $fila; $columna++) {
                            echo '*';
                        }
                        echo "\n";
                    }    
                    ?></textarea>        
                </label>
            </li>
            <li>
                 <label for="cajaTextoB">(B)<br>
                    <textarea id="cajaTextoB" name="textarea" rows="12" cols="15"><?php
                    for ($fila = 1; $fila <= $ancho; $fila++) {
                        for ($columna = 1; $columna <= ($ancho - $fila); $columna++) {
                            echo '*';
                        }
                        echo "\n";
                    }    
                    ?></textarea>
                 </label>
            </li>
            <li>
                <label for="cajaTextoC">(C)<br>
                    <textarea id="cajaTextoC" name="textarea" rows="12" cols="15"><?php
                    for ($fila = 1; $fila <= $ancho; $fila++) {
                        for ($columna = 1; $columna <= $ancho; $columna++) {
                            if ($columna < $fila) {
                                echo ' ';
                            } else {
                                echo '*';
                            }
                        }
                        echo "\n";
                    }    
                    ?></textarea>
                </label>
            </li>
            <li>
                <label for="cajaTextoD">(D)<br>
                    <textarea id="cajaTextoD" name="textarea" rows="12" cols="15"><?php
                    for ($fila = 1; $fila <= $ancho; $fila++) {
                        for ($columna = 1; $columna <= $ancho; $columna++) {
                            if ( $columna > ($ancho - $fila) ) {
                                echo '*';
                            } else {
                                echo ' ';
                            }
                        }
                        echo "\n";
                    }    
                    ?></textarea>
                </label>
            </li>
        </ul>
    </div> 
</body>
</html>