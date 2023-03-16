const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const tope        = document.getElementById("tope");
const salida      = document.getElementById("salida");

botonEnviar.addEventListener('click', () => {
    const numeroTope = Number(tope.value);
    
    if ( numeroTope <= 0 ) {
        alert("El número debe ser mayor o igual a 1");
        tope.value = '';
        tope.focus();
    } else {
        let suma = 0;

        for(let i = 7; i <= numeroTope; i += 7) {
            suma +=i;
        }

        salida.innerText = `La suma de todos los múltiplos de 7 de 1 hasta ${numeroTope} es: ${suma}`;
    }
} );

botonReset.addEventListener('click', () => {
    salida.innerText = '';
} );