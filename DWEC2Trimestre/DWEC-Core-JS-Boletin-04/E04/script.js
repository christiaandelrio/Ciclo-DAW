/*
 *  Iterativa
 *
*/
function calcularFibonacciIterativa (termino) {
    let fib1 = 0;
    let fib2 = 1;
    let i = 2;

    while (i <= termino) {
        const temp = fib2;
        fib2 += fib1;
        fib1 = temp;
        i++;
    }

    return termino > 1 ? fib2 : 1;
}

/*
 *  Recursiva
 *
*/
function calcularFibonacciRecursiva (termino) {
    if ( termino < 2 ) {
        return termino;
    } else {
        return  calcularFibonacciRecursiva(termino - 1) + calcularFibonacciRecursiva(termino - 2);
    }
}


const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const termino     = document.getElementById("termino");
const iterativa   = document.getElementById("interativa");
const recursiva   = document.getElementById("recursiva");



botonEnviar.addEventListener('click', () => {
    iterativa.innerText = "SOLUCIÓN iterativa: ";
    recursiva.innerText = "SOLUCIÓN recursiva: ";

    let numero = Number(termino.value);
    for (let i = 1; i <= numero; i++) {
        iterativa.innerText += calcularFibonacciIterativa(i) + ( i === numero ? "" : ", ");
        recursiva.innerText += calcularFibonacciRecursiva(i) + ( i === numero ? "" : ", ");
    }
});

botonReset.addEventListener('click', () => {
    iterativa.innerText = '';
    recursiva.innerText = '';
    a.focus();
});
