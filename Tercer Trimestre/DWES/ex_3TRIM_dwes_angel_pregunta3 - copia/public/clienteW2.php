<?php
// Vamos a crearnos este fichero para probar los métodos que nos ofrece el servicio con las clases generadas
require '../vendor/autoload.php';

// En este caso, como se generaron dos clases solamente, es fácil encontrar la clase de puerta de entrada al servicio (Gateway)
use Clases\Clases1\ClasesOperacionesService;

// Definimos nuevamente los parámetros que pasaremos a los métodos para probarlos
$codP = 2;
$codT = 1;
$codF = 'CONSOL';

// Nos creamos el objeto de la clase que generamos 
$objeto = new ClasesOperacionesService();

// Una vez creado el objeto, nos vamos a revisar la clase 'ClasesOperacionesService', y vemos que ya dispone de los métodos requeridos
// Por tanto, usamos la variable objeto para llamar a los métodos

//funcion getPvp ------------------------------------------------------------------------
$pvp = $objeto->getPvp($codP);
$precio = ($pvp == null) ? "No existe ese Producto" : $pvp;
echo "Precio del producto con Código (id) $codP: $precio €<br>";


//funcion getFamilias -------------------------------------------------------------------
echo "<br>Código de Familias de la base de datos:";
$prueba = $objeto->getFamilias();
echo "<ul>";
foreach ($prueba as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";


//funcion getProductosFamila ------------------------------------------------------------
$productos = $objeto->getProductosFamilia($codF);
echo "<br>Productos de la Familia $codF:";
echo "<ul>";
foreach ($productos as $k => $v) {
    echo "<code><li>$v</li></code>";
}
echo "</ul>";


// funcion getStock ---------------------------------------------------------------------
$unidades = $objeto->getStock($codP, $codT);
echo "<br>Unidades del producto con Código (id) $codP, disponibles en la tienda con código $codT: $unidades";

// funcion getMontante ----------------------------------------
$montante = $objeto->getMontante($codT);
echo "<br>Montante de la tienda de Código $codT: $montante €";

// funcion getNombreProducto --------------------------------
$nombre = $objeto->getNombreProducto($codP);
echo "<br>Nombre del producto con código (id) $codP: $nombre";

?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Document</title>
</head>

<body>
    <br><br>
    <button id="obtenerMontante">Obtener Montante</button>
    <script>
        $(document).ready(function() {
            $('#obtenerMontante').click(function() {
                $.ajax({
                    url: 'obtenerMontante.php', // Archivo PHP para obtener el montante
                    type: 'GET',
                    success: function(response) {
                        alert('Montante total del stock disponible en la tienda: ' + response + '€');
                    },
                    error: function(xhr, status, error) {
                        console.log(xhr.responseText);
                    }
                });
            });
        });
    </script>
</body>

</html>