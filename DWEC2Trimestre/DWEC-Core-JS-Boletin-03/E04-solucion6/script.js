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
    parrafo.innerText = '';
  
    if ( (numero >= 1) && (numero < 20) && (numero % 2 != 0) ) {
        cadena = (".".repeat(medio - 1)) + "*";
        for (let fila = 2; fila <= numero + 1 ; fila++) {
           parrafo.innerText += cadena + "\n";
           cadena = fila <= medio ? (cadena.slice(1) + "**") : ("." + cadena.slice(0, -2));
        }
        entrada.focus();
    } else {
        alert("Por favor, introduce un número impar comprendido entre 1 y 20, ambos incluidos");
    }
}

function borrarRombo() {
    parrafo.innerText = '';
}
