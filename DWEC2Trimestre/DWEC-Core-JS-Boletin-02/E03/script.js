const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const cajaNumero  = document.getElementById("numero");
const textoNumero = document.getElementById("textoNumero");
const parrafo     = document.getElementById("salida");

botonEnviar.addEventListener('click', (evento) => {
    let numero = Number(cajaNumero.value);

    if ( cajaNumero.value == ''            || 
         numero < 2                        ||
         numero > Number.MAX_SAFE_INTEGER  ||
         numero !== Math.floor(numero)) {
        alert(`Introduzca un número entero positivo N (1< N <= ${Number.MAX_SAFE_INTEGER}), por favor`);
        cajaNumero.focus();
    } else {
        // Borramos la salida de una vuelta anterior, por si acaso:
        parrafo.innerText = '';

        // El siguiente bucle se puede optimizar, 
        // si utilizamos la propiedad matemática:
        //   N es primo si N no es divisible por
        //   cualquier entero (primo) <= raíz cuadrada de N
        //   (es decir, en vez de numero - 1, 
        //     podríamos poner Math.floor(Math.sqrt(numero))
        for (let i = 2; i < numero - 1; i++) {
            if ( numero % i === 0) {
                parrafo.innerText = 'El número no es primo';
                return;
            }
        }
        parrafo.innerText = 'El número es primo';
    }
} );

botonReset.addEventListener('click', () => {
    parrafo.innerHTML = '';
    cajaNumero.value = '';
    cajaNumero.focus();
});
