const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const parrafo     = document.getElementById('parrafo1');
const entrada     = document.getElementById('entrada1');   

botonEnviar.addEventListener('click', dibujarRombo);
botonReset.addEventListener('click', borrarRombo);


// Ejemplo solución 1: dibujo en 2 triángulos
function dibujarRombo() {
    const numero      = Number(entrada.value);
    const medio       = Math.floor(numero / 2 + 1);
    parrafo.innerText = '';

    if ( (numero >= 1) && (numero < 20) && (numero % 2 != 0) ) {
        // Dibujar parte superior rombo:
        for (let fila = 1; fila <= medio; fila++) {
            for (let columna = 1; columna <= medio + fila - 1; columna++) {
                if ( (columna > medio - fila) && (columna < medio + fila) ) {
                    parrafo.innerText += '*';
                } else {
                    // parrafo.innerText += '\u00a0';
                    parrafo.innerText += '.';
                }
            }
            parrafo.innerText += '\n';
        }

         // Dibujar parte inferior rombo:
         for (let fila = medio - 1 ; fila >= 1; fila--) {
            for (let columna = 1; columna <= medio + fila - 1; columna++) {
                if ( (columna > medio - fila) && (columna < medio + fila ) ) {
                    parrafo.innerText += '*';
                } else {
                    //  parrafo.innerText += '\u00a0';
                    parrafo.innerText += '.';
                }
            }
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