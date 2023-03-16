// Las funciones están pensadas para operar con BigInt
// debido a que la función factorial en seguida revasa
//  Number.MAX_SAFE_INTEGER

function factorial1(numero) {
    let factorial = 1n;

    for (let i = numero; i > 1n; i--) {
        factorial *= i;
    }
    return factorial;
}

function factorial2(numero) {
    if (numero === 0n || numero === 1n) {
        return 1n;
    } else {
        return numero * factorial2(numero - 1n);
    }
}


const filas = [5, 7, 10, 20, 30, 100];

const cuerpoTabla = document.getElementById('cuerpoTabla');
const REPETICIONES = 10000;

filas.forEach(fila => {
    // Calcular datos:
    //  Como cada ejecución de función tarda muy poco, vamos a 
    //  ejecutar cada función REPETICIONES veces y dividir por REPETICIONES el tiempo
    //  para así calcular un promedio
    console.time('fac1_' + fila);
    const tinicio1   = performance.now();
    for (let i = 0; i < REPETICIONES - 1; i++) {
        factorial1(BigInt(fila));
    }
    const resultado1 = factorial1(BigInt(fila));
    const tfinal1    = performance.now();
    console.timeEnd('fac1_' + fila);

    console.time('fac2_' + fila);
    const tinicio2   = performance.now();
    for (let i = 0; i < REPETICIONES - 1; i++) {
        factorial2(BigInt(fila));
    }   
    const resultado2 = factorial2(BigInt(fila));
    const tfinal2    = performance.now();
    console.timeEnd('fac2_' + fila);   

    // Añadir datos a la fila:
    const htmlFila = document.createElement("tr");
    cuerpoTabla.appendChild(htmlFila);

    const celdaNumero = document.createElement("td");
    htmlFila.appendChild(celdaNumero)
    celdaNumero.textContent = fila;

    let celda = document.createElement("td");
    htmlFila.appendChild(celda);
    celda.textContent = (resultado1 + "").length > 20 ? Number(resultado1): resultado1;

    celda = document.createElement("td");
    htmlFila.appendChild(celda);
    celda.textContent = `tF = ${tfinal1} tI= ${tinicio1} -> ${(tfinal1 - tinicio1)/REPETICIONES}`;

    celda = document.createElement("td");
    htmlFila.appendChild(celda);
    celda.textContent = (resultado2 + "").length > 20 ? Number(resultado2): resultado2;;

    celda = document.createElement("td");
    htmlFila.appendChild(celda);
    celda.textContent = `tF = ${tfinal2} tI= ${tinicio2} -> ${(tfinal2 - tinicio2)/REPETICIONES}`;

    celda = document.createElement("td");
    htmlFila.appendChild(celda);
    const diferencia = ((tfinal1 - tinicio1)/REPETICIONES) - ((tfinal2 - tinicio2)/REPETICIONES);
    celda.textContent = diferencia;
});

