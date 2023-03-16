const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
// Entradas:
const cajaTotalMensualVentas      = document.getElementById("totalMensualVentas");
const cajaNombreMes               = document.getElementById("nombreMes");
// Salidas:
const parrafoTotalRecaudado       = document.getElementById("totalRecaudado");
const parrafoTotalLiquido         = document.getElementById("totalLiquido");
const parrafoImpuestosAutonomicos = document.getElementById("impuestosAutonomicos");
const parrafoImpuestosEstatales   = document.getElementById("impuestosEstatales");
const parrafoImpuestosTotales     = document.getElementById("impuestosTotales");
// Datos:
const IMPUESTO_ESTATAL    = 0.05;
const IMPUESTO_AUTONOMICO = 0.04;


botonEnviar.addEventListener('click', (evento) => {
    if ( cajaTotalMensualVentas.value == '' || cajaNombreMes.value == '') {
      alert("Complete ambos números, por favor");
    } else {
        limpiarSalidas();
        let totalMensualVentas = Number(cajaTotalMensualVentas.value);
        let liquido = totalMensualVentas / (1 + IMPUESTO_AUTONOMICO + IMPUESTO_ESTATAL);

        parrafoTotalRecaudado.innerHTML       += (totalMensualVentas).toFixed(2) + " €";
        parrafoTotalLiquido.innerHTML         += (+liquido).toFixed(2) + " €";
        parrafoImpuestosAutonomicos.innerHTML += (liquido * IMPUESTO_AUTONOMICO).toFixed(2) + " €";
        parrafoImpuestosEstatales.innerHTML   += (liquido * IMPUESTO_ESTATAL).toFixed(2) + " €";
        parrafoImpuestosTotales.innerHTML     += (liquido * (IMPUESTO_AUTONOMICO + IMPUESTO_ESTATAL)).toFixed(2) + " €";
    }} );

botonReset.addEventListener('click', limpiarSalidas);

function limpiarSalidas( ) {
    parrafoTotalRecaudado.innerHTML       = "Total recaudado&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;";
    parrafoTotalLiquido.innerHTML         = "Total líquido&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;";
    parrafoImpuestosAutonomicos.innerHTML = "Impuestos autonómicos&nbsp;&nbsp;:&nbsp;";
    parrafoImpuestosEstatales.innerHTML   = "Impuestos estatales&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;";
    parrafoImpuestosTotales.innerHTML     = "Impuestos totales&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;";
    cajaTotalMensualVentas.focus();
}
