const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const numeroHTML  = document.getElementById("numero");
const salida      = document.getElementById("salida");

botonEnviar.addEventListener('click', () => {
    const numero = Number(numeroHTML.value);
    
    if ( numero < 1 || numero > 100 ) {
        alert("El número debe estar comprendido entre 1 y 100, ambos incluídos");
        numeroHTML.value = '';
        numeroHTML.focus();
    } else {
        salida.innerText = '';
        for (let i = 1; i <=100; i++) {
            salida.innerText += i < 100 ? " " : "";
            salida.innerText +=  (i % numero === 0) ? (i < 100 ? " *" : "  *") : (i < 10 ? " " + i : i);
            salida.innerText += " ";
            salida.innerText += ( i % 10 ) === 0 ? "\n" : "";
        }
    }
} );

botonReset.addEventListener('click', () => {
    salida.innerText = '';
    numeroHTML.value = '';
    numeroHTML.focus();
} );




