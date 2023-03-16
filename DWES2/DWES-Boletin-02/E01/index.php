<?php
define('IMPUESTO_ESTATAL', 0.05);
define('IMPUESTO_AUTONOMICO', 0.04);
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DWEC-JS-B02-01</title>
</head>
<body">
    <form action="<?php echo $_SERVER['PHP_SELF'];?>" method="get">

    <div class="entrada">
<?php 
    $primeraVez = false;
    echo '<input type="hidden"  name="primeraVez" value="false" />';
    if ( !isset($_GET['primeraVez']) ) {
        $totalMensualVentas = 0.00;
        $primeraVez = true;
    } else {
        $totalMensualVentas = floatval($_GET['totalMensualVentas']);
    }
    
    echo <<<MARCA
        <p>
            <label for="totalMensualVentas">Introduzca el importe mensual total de ventas: </label>
            <input type="number" min="0" step="any" id="totalMensualVentas" name="totalMensualVentas" value="{$totalMensualVentas}" />
        </p>
MARCA;
        if ( !$primeraVez && $totalMensualVentas == 0 ) {
        echo "<span style='color: red'> Introduzca el importe, por favor</span>";
            }

?>
        <p>
            <label for="nombreMes">Introduzca el nombre del mes: </label>
            <select id="nombreMes" name="nombreMes">
                <option value="Enero" selected>Enero</option>
                <option value="Febrero">Febrero</option>
                <option value="Marzo">Marzo</option>
                <option value="Abril">Abril</option>
                <option value="Mayo">Mayo</option>
                <option value="Junio">Junio</option>
                <option value="Julio">Julio</option>
                <option value="Agosto">Agosto</option>
                <option value="Septiembre">Septiembre</option>
                <option value="Octubre">Octubre</option>
                <option value="Noviembre">Noviembre</option>
                <option value="Diciembre">Diciembre</option>
            </select>
        </p>
        </div> 
<?php
    if (!$primeraVez) {
        $totalMensualVentas   = floatval($_GET['totalMensualVentas']);
        $liquido              = $totalMensualVentas / (1 + IMPUESTO_AUTONOMICO + IMPUESTO_ESTATAL);
        $impuestosAutonomicos =  $liquido * IMPUESTO_AUTONOMICO;
        $impuestosEstatales   =  $liquido * IMPUESTO_ESTATAL;
        $impuestosTotales     =  $liquido * (IMPUESTO_AUTONOMICO + IMPUESTO_ESTATAL);
    } else {    
        $totalMensualVentas   = 0.0;
        $liquido              = 0.0;
        $impuestosAutonomicos = 0.0;
        $impuestosEstatales   = 0.0;
        $impuestosTotales     = 0.0;
    } 

        $totalMensualVentas   = number_format($totalMensualVentas, 2, ",", "");
        $liquido              = number_format($liquido, 2, ",", "");
        $impuestosAutonomicos = number_format($impuestosAutonomicos, 2, ",", "");
        $impuestosEstatales   = number_format($impuestosEstatales, 2, ",", "");
        $impuestosTotales     = number_format($impuestosTotales, 2, ",", "");        
        echo <<<MARCA
        <div class="salida" style="font-family: monospace;">
            <p id="totalRecaudado">      Total recaudado&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;{$totalMensualVentas} €</p>
            <p id="totalLiquido">        Total líquido&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;{$liquido} €</p>
            <p id="impuestosAutonomicos">Impuestos autonómicos&nbsp;&nbsp;:&nbsp;{$impuestosAutonomicos} €</p>  
            <p id="impuestosEstatales">  Impuestos estatales&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;{$impuestosEstatales} €</p>
            <p id="impuestosTotales">    Impuestos totales&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;{$impuestosTotales} €</p>     
        </div> 
MARCA;
?>
        <button id="botonEnviar" type="submit">Enviar</button>      
        <button id="botonReset">Reset</button>
    </form>
</body>
</html>