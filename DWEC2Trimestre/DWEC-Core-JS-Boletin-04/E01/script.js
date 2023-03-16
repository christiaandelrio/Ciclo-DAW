/*
 *  Parámetro:
 *  numero: [ENTRADA] número a comprobar si es primo o no
 *  
 *  Devuelve: true (es primo) / false (no es primo)
*/
function esPrimo(numero) {
    const raiz = Math.floor(Math.sqrt(numero));
    let i = 2;

    // Por una propiedad matemática, basta con comprobar hasta la raiz
    while ( (i <= raiz)  &&  ((numero % i) !== 0) ) {
        i++;
    }

    if ( i > raiz ) {
        return numero === 1 ? false : true;
    } else {
        return false;
    }
}

const parrafo = document.querySelector('pre');

for (let i = 1; i <=100; i++) {
    parrafo.innerText += esPrimo(i) ? (" " + (i<10 ? " "+i:i) + " ") : " -- ";
    parrafo.innerText += ( i % 10 ) === 0 ? "\n" : "";
}

