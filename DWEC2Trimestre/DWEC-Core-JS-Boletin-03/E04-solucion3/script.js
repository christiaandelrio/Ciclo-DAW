const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const parrafo     = document.getElementById('parrafo1');
const entrada     = document.getElementById('entrada1');   

botonEnviar.addEventListener('click', dibujarRombo);
botonReset.addEventListener('click', borrarRombo);


// Sol
function dibujarRombo() {
    const numero      = Number(entrada.value);
    const medio       = Math.floor(numero / 2 + 1);
    let   largoFila   = medio;
    let   incremento  = 1;
    parrafo.innerText = '';

    if ( (numero >= 1) && (numero < 20) && (numero % 2 != 0) ) {
        for (let fila = 1; fila <= numero; fila++) {
            for (let columna = 1; columna <= largoFila; columna++) {
                parrafo.innerText += (columna <= (medio - fila) * incremento) ? "." : "*";
            }
            
            if ( fila === medio) {
              incremento = -1;
            }

            largoFila += incremento;
            parrafo.innerText += '\n';
        }
        entrada.focus();
    } else {
        alert("Por favor, introduce un número impar comprendido entre 1 y 20, ambos incluidos");
    }
}

function borrarRombo() {
    parrafo.innerText = '';
}