function espejo(numero) {
    if ( numero === 0) {
        return;
    } else {
        parrafo.innerText += (numero % 10);
        espejo((numero / 10)|0);
    }
}

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
        espejo(numero);
    }
} );

botonReset.addEventListener('click', limpiarEntradas);

function limpiarEntradas( ) {
    parrafo.innerHTML = '';
    cajaNumero.value = '';
    cajaNumero.focus();
}
