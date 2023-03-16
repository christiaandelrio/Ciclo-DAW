// debugger
const parrafo     = document.getElementById('parrafo1');
const numerosAGenerar = 10;

parrafo.innerHTML = '';

// Nos piden de 9999 hasta 10000000:
//    9999 + x = 1000000 --->   x = 1000000 - 9999 = 990001
//    para generar ese x  con Math.random() hay que poner
//      x = Math.random() * (990001 + 1) = Math.random() * 990002
//   puesto que Math.random() genera floats de 0 a 0,999.. sin llegar a 1
let numero = Math.floor(Math.random() * (1000000 - 9999 + 1)  + 9999 );

for (let fila = 1; fila <= numerosAGenerar; fila++) {
    parrafo.innerHTML += separarEnDigitos(numero);
    parrafo.innerHTML += '<br>';
    numero = Math.floor(Math.random() * (1000000 - 9999 + 1)  + 9999 );
}

function separarEnDigitos(numero) {
    let salida = '';
    let cerosIzquierda = '';
    cerosIzquierda += numero < 1000000 ? '0    ': '';
    cerosIzquierda += numero <  100000 ? '0    ': '';
    cerosIzquierda += numero <   10000 ? '0    ': '';

    while (numero > 0) {
        salida = ((numero % 10)|0) + salida;
        numero = (numero / 10)|0;
        if ( numero != 0 ) {
            salida = '    ' + salida;
        }
    }
    return cerosIzquierda + salida;
}