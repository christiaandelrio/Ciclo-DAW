const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const cajaNumeroBinario    = document.getElementById("numeroBinario");
const parrafoNumeroDecimal = document.getElementById("numeroDecimal");

botonEnviar.addEventListener('click', (evento) => {
    // Comprobar formato entrada:
    if ( cajaNumeroBinario.value == '') {
      alert("Introduzca un número binario, por favor");
      return;
    } else {
        let numeroDecimal = 0;
        let numeroBinario = Number(cajaNumeroBinario.value);

        if (numeroBinario < 0) {
            alert("El número debe ser positivo ( de 00000 a 11111)");
            return;
        } else {
            let digito0 = (numeroBinario % 10)|0;
            if ( digito0 > 1) {
                alert("El digito0 no es válido: '" + digito0 + "'");
                return;
            }
            numeroBinario = (numeroBinario / 10)|0;

            let digito1 = (numeroBinario % 10)|0;
            if ( digito1 > 1) {
                alert("El digito1 no es válido: '" + digito1 + "'");
                return;
            }
            numeroBinario = (numeroBinario / 10)|0;

            let digito2 = (numeroBinario % 10)|0;
            if ( digito2 > 1) {
                alert("El digito2 no es válido: '" + digito2 + "'");
                return;
            }
            numeroBinario = (numeroBinario / 10)|0;

            let digito3 = (numeroBinario % 10)|0;
            if ( digito3 > 1) {
                alert("El digito3 no es válido: '" + digito3 + "'");
                return;
            }
            numeroBinario = (numeroBinario / 10)|0;

            let digito4 = (numeroBinario % 10)|0;
            if ( digito4 > 1) {
                alert("El digito4 no es válido: '" + digito4 + "'");
                return;
            }
            numeroBinario = (numeroBinario / 10)|0;

            if ( numeroBinario > 0) {
                alert("El número introducido es demasiado grande.");
                return;
            }

            numeroDecimal = digito4 * (2**4) +  digito3 * (2**3) +  digito2 * (2**2) +  digito1 * (2**1)  +  digito0 * (2**0);
            parrafoNumeroDecimal.innerHTML = 'El número decimal correspondiente es:&nbsp;' + numeroDecimal;
        }
      
    }
} );

botonReset.addEventListener('click', limpiarEntradas);

function limpiarEntradas( ) {
    parrafoNumeroDecimal.innerHTML = 'El número decimal correspondiente es:&nbsp;';
    cajaNumeroBinario.focus();
}
