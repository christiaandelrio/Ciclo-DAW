const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const parrafo     = document.getElementById('parrafo1');
const entrada     = document.getElementById('entrada1');   

botonEnviar.addEventListener('click', dibujarRombo);
botonReset.addEventListener('click', borrarRombo);


// 
function dibujarRombo() {
    const numero      = Number(entrada.value);
    const medio       = Math.floor(numero / 2 + 1);
    let   asteriscos  = 0 ;
    parrafo.innerText = '';

    if ( (numero >= 1) && (numero < 20) && (numero % 2 != 0) ) {
        for (let fila = 1; fila <= numero; fila++) {
            parrafo.innerText += ".".repeat( medio - (asteriscos + 1));
            parrafo.innerText += "*".repeat( 2 * asteriscos + 1);
            parrafo.innerText += '\n';
            asteriscos += fila < medio ? 1 : -1;
        }
        entrada.focus();
    } else {
        alert("Por favor, introduce un número impar comprendido entre 1 y 20, ambos incluidos");
    }
}

function borrarRombo() {
    parrafo.innerText = '';
}
