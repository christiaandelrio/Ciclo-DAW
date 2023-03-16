
// Vamos a utilizar una variable global para contar el número de pasos
// aunque esto no se pide en el enunciado, puede ser ilustrativo.
// Por otro lado, también podríamos utilizar otro parámetro dentro de 
// la firma de la función para contar el número de pasos
function torresDeHanoi(discos, posteInicial, posteFinal, posteTemporal) {
    if (discos === 1) {
        textoSalida += `Paso ${++contador}: ${posteInicial} --> ${posteFinal}\n`;
    } else {
        torresDeHanoi(discos - 1, posteInicial, posteTemporal, posteFinal);
        textoSalida += `Paso ${++contador}: ${posteInicial} --> ${posteFinal}\n`;
        torresDeHanoi(discos - 1, posteTemporal, posteFinal, posteInicial);
    }
}

const botonEnviar   = document.getElementById("botonEnviar");
const botonReset    = document.getElementById("botonReset");
const textoNumero   = document.getElementById("numeroDiscos");
const parrafoSalida = document.getElementById("salida");

let textoSalida = '';
let contador = 0;


botonEnviar.addEventListener('click', (evento) => {
    parrafoSalida.innerText = '';
    textoSalida = '';
    contador = 0;
    torresDeHanoi(textoNumero.value, 1, 3, 2);
    parrafoSalida.innerText = textoSalida;
} );

botonReset.addEventListener('click', (evento) => {
    textoNumero.value = '';
    textoSalida = '';
    parrafoSalida.innerText = '';
    textoNumero.focus();
} );

