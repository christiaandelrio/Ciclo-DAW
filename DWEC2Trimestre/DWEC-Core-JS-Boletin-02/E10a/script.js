const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const cajaNumeroBinario    = document.getElementById("numeroBinario");
const parrafoNumeroDecimal = document.getElementById("numeroDecimal");

botonEnviar.addEventListener('click', (evento) => {
    // Comprobar formato entrada:
    if ( cajaNumeroBinario.value == '') {
      alert("Introduzca un número binario, por favor");
    } else {
        let numeroBinario = cajaNumeroBinario.value;
        let numeroDecimal = 0;

        if (numeroBinario.length > 5) {
            alert("El número introducido tiene más de 5 dígitos");
            return;
        } else {
            for(let i = (numeroBinario.length - 1); i >= 0; i--) {
                if ( numeroBinario[i] > 1) {
                    alert("El número introducido tiene en su posición " + ((numeroBinario.length - 1)- i) + " el caracter inválido " + numeroBinario[i]);
                    return;
                } else {
                    numeroDecimal += (numeroBinario[i] * (1<<((numeroBinario.length - 1) - i)));
                }
            }
             parrafoNumeroDecimal.innerHTML = 'El número decimal correspondiente es:&nbsp;' + numeroDecimal;
         }
    }
} );

botonReset.addEventListener('click', limpiarEntradas);

function limpiarEntradas( ) {
    parrafoNumeroDecimal.innerHTML = 'El número decimal correspondiente es:&nbsp;';
    cajaNumeroBinario.focus();
}
