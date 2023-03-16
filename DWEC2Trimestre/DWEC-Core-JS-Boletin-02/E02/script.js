const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
// Entradas:
const textoCuenta   = document.getElementById("cuenta");
const textoCantidad = document.getElementById("cantidad");
const textoDuracion = document.getElementById("duracion");
const textoInteres  = document.getElementById("interes");
// Salidas:
const textoInteresTotal   = document.getElementById("interesTotal");
const textoInteresMensual = document.getElementById("interesMensual");


botonEnviar.addEventListener('click', (evento) => {
    const cuenta   = textoCuenta.value;
    const cantidad = Number(textoCantidad.value);
    const duracion = Number(textoDuracion.value);
    const interes  = Number(textoInteres.value); 

    // Validar datos:
    if ( cantidad < 10000 ) {
        alert("Lo sentimos, no concedemos hipotecas menores de 10.000€");
        textoCantidad.focus();
    } else if (textoCantidad.value === '' ) {
        alert("La cantidad debe ser 10.000€ o mayor");
        textoCantidad.focus();
    } else if ( duracion < 2 || duracion > 20 || textoDuracion.value === '' ) { 
        alert("Lo sentimos, la duración debe estar entre 2 años y 20 años, ambos incluídos");
        textoDuracion.focus();
    } else if (textoInteres.value === '' || interes < 0.05) { 
        alert("Lo sentimos, la tasa de interés mínima es del 5%");
        textoInteres.focus();
    } else {
 
        const interesTotal = cantidad * interes * duracion;
        const interesMensual = (cantidad + interesTotal) / (duracion * 12);

        textoInteresTotal.innerText   = `Los intereses totales son: ${interesTotal.toFixed(2).replace(".", ",")}€`;
        textoInteresMensual.innerText = `La cuota mensual asciende a: ${interesMensual.toFixed(2).replace(".", ",")}€`;
    }
} );


botonReset.addEventListener('click', limpiarFormulario);

function limpiarFormulario( ) {
    textoCuenta.value = '';
    textoCantidad.value = '';
    textoDuracion.value = '';
    textoInteres.value = '';

    textoInteresTotal.innerText = '';
    textoInteresMensual.innerText = '';

    textoCuenta.focus();
}
