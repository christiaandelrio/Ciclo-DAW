const parrafo  = document.getElementById("salida");
const nbs = "\u00a0";
const ancho = 7; 

function espaciar(numero) {
    return nbs.repeat(ancho - numero.toString().length);
}

for (let i = 1; i <=10; i++) {
    parrafo.innerText += `${i}${espaciar(i)}${i**2}${espaciar(i**2 )}${i**3}${espaciar(i**3)}${i**4}\n`; 
}