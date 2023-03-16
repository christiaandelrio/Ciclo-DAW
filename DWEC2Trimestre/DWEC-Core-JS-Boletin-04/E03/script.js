/*
 *  Función que calcula la 
 *
 * 
*/
function resolverEcuacionSegundoGrado(a, b, c) {
    const discriminante = (b * b) - (4 * a * c);

    if (  discriminante < 0.0 ) {
        return [NaN, NaN];
    } else {
        return [ (-b + Math.sqrt(discriminante))/(2*a),(-b - Math.sqrt(discriminante))/(2*a) ];
    }
}

const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const a           = document.getElementById("coeficienteA");
const b           = document.getElementById("coeficienteB");
const c           = document.getElementById("coeficienteC");
const parrafo     = document.getElementById("parrafo");
const salida      = document.getElementById("salida");
const tripletas   = document.querySelector("pre");

salida.style.display = "none";
salida.style.fontFamily = "monospace";

botonEnviar.addEventListener('click', () => {
    let solucion = resolverEcuacionSegundoGrado(Number(a.value), Number(b.value), Number(c.value) )
    parrafo.innerText = " raíz 1 = " + solucion[0] + "; raíz 2 = " + solucion[1] + "\n\n";
    salida.style.display = "block";
    for (let i = 0; i < 10; i++) {
        const coefA = (Math.random() < 0.5 ? -1: 1) * ( Math.floor(Math.random() * 20) + 1);
        const coefB = (Math.random() < 0.5 ? -1: 1) * ( Math.floor(Math.random() * 20) + 1);
        const coefC = (Math.random() < 0.5 ? -1: 1) * ( Math.floor(Math.random() * 20) + 1);
        let solucion = resolverEcuacionSegundoGrado(coefA, coefB, coefC);
        parrafo.innerText += `Solución (${coefA},${coefB},${coefC}): raíz 1 = ${solucion[0]}  ; raíz 2 = ${solucion[1]} \n`;
    }
});

botonReset.addEventListener('click', () => {
    a.value = '';
    b.value = '';
    c.value = '';
    parrafo.innerText = '';
    salida.style.display = "none";
    a.focus();
});