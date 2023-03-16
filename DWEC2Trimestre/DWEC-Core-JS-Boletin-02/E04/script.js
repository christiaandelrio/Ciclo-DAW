const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const cajaNumero  = document.getElementById("numero");
const textoNumero = document.getElementById("textoNumero");
const parrafo     = document.getElementById("salida");

let contador = 1;
let mayor = 0;
    

botonEnviar.addEventListener('click', (evento) => {
    let numero = Number(cajaNumero.value);

    if ( cajaNumero.value == ''            || 
         numero < 1                        ||
         numero > Number.MAX_SAFE_INTEGER  ||
         numero !== Math.floor(numero)) {
        alert(`Introduzca un número entero positivo (<${Number.MAX_SAFE_INTEGER}), por favor`);
        cajaNumero.focus();
    } else {
        // Borramos la salida de una vuelta anterior, por si acaso:
        parrafo.innerText = '';

        // Procesar nuevo número:
        if ( numero > mayor ) {
            mayor = numero;
        } 

        contador++;
        if ( contador <=10 ) {
            textoNumero.innerText = `Introduzca un número ${contador}/10:`;
            cajaNumero.value = '';
            cajaNumero.focus();
        } else {
            // Imprimir resultado final:
            parrafo.innerText = `El mayor es: ${mayor}`;

            // Reinicializar el estado para comenzar otra vuelta:
            mayor = 0;
            contador = 1;
            textoNumero.innerText = `Introduzca un número ${contador}/10:`;
            cajaNumero.value = '';
            cajaNumero.focus();
        }
    }
} );

botonReset.addEventListener('click', () => {
    parrafo.innerHTML = '';
    mayor = 0;
    contador = 1;
    textoNumero.innerText = `Introduzca un número ${contador}/10:`;
    cajaNumero.value = '';
    cajaNumero.focus();
});
