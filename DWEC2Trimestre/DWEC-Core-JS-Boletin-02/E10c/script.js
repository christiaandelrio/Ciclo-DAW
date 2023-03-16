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
        let numeroBinario = Number(cajaNumeroBinario.value);

        if (numeroBinario < 0) {
            alert("El número debe ser positivo ( de 00000 a 11111)");
            return;
        } else {
            let numeroDecimal = 0;
            let posicion = 0;
            while ( numeroBinario > 0 ) {
                const digito = numeroBinario % 10;
                if ( digito !== 1 && digito !== 0) {
                    alert("El número sólo puede tener 0s y 1s!!!");
                    return;
                }
                if (  posicion > 4 ) {
                    alert("El número sólo puede tener 5 dígitos!!!");
                    return;
                }
                numeroDecimal += digito * ( 2**posicion);
                posicion++;
                numeroBinario = (numeroBinario/10)|0;
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
