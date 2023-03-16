<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="script.js" defer></script>
    <title>DWEC-JS-B03-01</title>
</head>
<body style="font-family: monospace">
    <div>
        <p>
            <label for="numeroEnteros" id="solicitarCantidad">Introduzca el número de enteros a procesar: 
                <input type="number" id="numeroEnteros" name="numeroEnteros" />
            </label>
        </p>
    </div>
    <div class="entradaEnteros">
        <p>
            <label for="introEntero" id="numero">MENSAJE</label>
            <input type="number" id="introEntero" name="entero" />
        </p>        
    </div>
    <div class="salida">
        <p id="media">&nbsp;</p>
    </div> 

    <button id="botonEnviar">Enviar</button>      
    <button id="botonReset">Reset</button> 

</body>
</html> 