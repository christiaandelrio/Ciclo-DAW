const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const parrafo     = document.getElementById('parrafo1');
const entrada     = document.getElementById('entrada1');   

botonEnviar.addEventListener('click', dibujarCuadrado);
botonReset.addEventListener('click', borrarCuadrado);


function dibujarCuadrado() {
  
    const numero = Number(entrada.value);
    parrafo.innerText = '';
    if ( numero >= 1 && numero <= 20) {
        let fila = 1; 
        do {
            let columna = 1;
            do {
                parrafo.innerText += '*';
                columna++;
            } while (columna <= numero);
            parrafo.innerText += '\n';
            fila++
        } while (fila <= numero);
        entrada.focus();
    } else {
        alert("Por favor, introduce un número comprendido entre 1 y 20, ambos incluidos");
    }
}

function borrarCuadrado() {
    parrafo.innerText = '';
}