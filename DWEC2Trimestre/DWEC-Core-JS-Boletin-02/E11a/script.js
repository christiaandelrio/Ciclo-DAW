const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const cajaNumero  = document.getElementById("numero");
const parrafo     = document.getElementById("salida");

botonEnviar.addEventListener('click', (evento) => {
    parrafo.innerHTML = 'Imagen espejo del número:&nbsp;';
    // Comprobar formato entrada:
    let numero = Number(cajaNumero.value);
    if ( cajaNumero.value == '' || numero < 1) {
        alert("Introduzca un número entero positivo, por favor");
        cajaNumero.focus();
    } else {
        while ( numero > 0 ) {
            parrafo.innerText += (numero % 10);
            numero = (numero / 10)|0;
        }
    }
} );

botonReset.addEventListener('click', limpiarEntradas);

function limpiarEntradas( ) {
    parrafo.innerHTML = '';
    cajaNumero.value = '';
    cajaNumero.focus();
}
