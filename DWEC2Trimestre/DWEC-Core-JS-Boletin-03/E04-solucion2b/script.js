const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const parrafo     = document.getElementById('parrafo1');
const entrada     = document.getElementById('entrada1');   

botonEnviar.addEventListener('click', dibujarRombo);
botonReset.addEventListener('click', borrarRombo);


// NOTA: en todos estos ejercicios del rombo
//  en vez de "dibujar" espacio dibujo "punto" para
//  que se aprecie la diferencia entre varias soluciones
function dibujarRombo() {
    const numero      = Number(entrada.value);
    const medio       = Math.floor(numero / 2 + 1);
    let   siAsterisco = 0;
    parrafo.innerText = '';

    if ( (numero >= 1) && (numero < 20) && (numero % 2 != 0) ) {
        for (let fila = 1; fila <= numero; fila++) {
            for (let columna = 1; columna <= medio + siAsterisco  ; columna++) {
                if ( (columna >= (medio - siAsterisco) ) && 
                     (columna <= (medio + siAsterisco) ) ) {
                    parrafo.innerText += '*';
                } else {
                    // parrafo.innerText += '\u00a0';
                    parrafo.innerText += '.';
                }
            }
            fila < medio ? ++siAsterisco : --siAsterisco;
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