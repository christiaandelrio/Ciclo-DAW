const parrafo  = document.getElementById("salida");

parrafo.innerText = "A\u00a0\u00a0A+3\u00a0\u00a0A+6\u00a0\u00a0A+9\n\n";

for (let i = 7; i <=35; i +=7) {
    parrafo.innerText += `${i < 10 ? "\u00a0" + i : i}\u00a0\u00a0${i+3}\u00a0\u00a0${i+6}\u00a0\u00a0\u00a0${i+9}\n`; 
}