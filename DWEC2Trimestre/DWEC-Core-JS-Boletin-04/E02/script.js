const parrafo     = document.getElementById('parrafo1');
parrafo.innerHTML = '';
 
let numeroFilas      = Math.floor(Math.random() * 100 + 1);
let primerNumeroFila = Math.floor(Math.random() *  50 + 1);
for (let fila = 1; fila <= numeroFilas; fila++) {
    parrafo.innerHTML += primerNumeroFila + ' ';
    for (let elementoFila = 1; elementoFila < primerNumeroFila; elementoFila++) {
        parrafo.innerHTML += Math.floor(Math.random() * 100 + 1) + ' ';
    }
    parrafo.innerHTML += '<br>';
    primerNumeroFila = Math.floor(Math.random() *  50 + 1);
}
