/*
 *  Esta función recibe una referencia a un elemento HTML en donde
 *  dibujar una tabla de filas x columnas y al pulsar en un botón
 *  'Enviar matriz' la guarda en el parámetro matriz
 * 
 *   filas:       [ENTRADA] número de filas de la matriz
 *   columnas:    [ENTRADA] número de columnas de la matriz
 *   matriz:      [SALIDA] una referencia a un array en donde guardar los datos de la matriz
 *   manipulador: [SALIDA] una referencia un elemento HTML dentro del cual crear la tabla
 *                para introducir la matriz y los botones de control
 *   
 */
function solicitarMatriz(filas, columnas, matriz, manipulador) {
    // Array datos HHTML matriz, donde guardaremos referencias a cada HTMLInput de la matriz
    let matrizHTML = Array(filas).fill().map( () => Array(columnas).fill(0));

    // Vaciar elemento contenedor matriz
    manipulador.innerHTML = '';

    // Crear tabla básica que permite introducir los datos de la matriz
    let tabla = document.createElement("table");
    tabla.setAttribute("class", "matrix");
    let elementoHTML = document.createElement("thead");
    tabla.appendChild(elementoHTML);
    let tablaBody = document.createElement("tbody");
    tabla.appendChild(tablaBody);

    // Añadir filas a la tabla 
    for(let fila = 0; fila < filas; fila++) {
        let filaHTML = document.createElement("tr");
        tablaBody.appendChild(filaHTML);

        for(let columna = 0; columna < columnas; columna++) {
            let celda = document.createElement("td");
            let numero = document.createElement("input");
            // Guardamos la referencia al input para poder procesarlo después:
            matrizHTML[fila][columna] = numero;
            numero.setAttribute("type", "number");
            celda.appendChild(numero);
            filaHTML.appendChild(celda);
        }
    }

    // Ahora aparece la matriz (tabla)....
    manipulador.appendChild(tabla);

    // Añadimos botones control matriz y sus listeners:
    let botonEnviarMatriz = document.createElement("button");
    let botonBorrarMatriz = document.createElement("button");
    botonEnviarMatriz.innerText = "Enviar matriz";
    botonBorrarMatriz.innerText = "Borrar matriz";
    
    manipulador.appendChild(botonEnviarMatriz);
    manipulador.appendChild(botonBorrarMatriz);

    botonEnviarMatriz.addEventListener('click', () => {
        // Cogemos de cada input text el valor y lo copiamos al parametro matriz:
        matrizHTML.forEach( (fila, i) => {
            fila.forEach( (celda, j) => {
                matriz[i][j] = celda.value === '' ? 0: celda.value;
            })
        });
    });

    botonBorrarMatriz.addEventListener('click', () => { 
        // Ponemos, para cada fila, cada celda a 0:
        matrizHTML.forEach( fila => fila.map( celda => celda.value = 0))
    });
}


// -----------------------------------------------------------------
//  Código para probar la función de introducción de matriz:
//  Podremos comprobar la correcta salida a través de la consola del 
//  navegador:
const botonEnviar         = document.getElementById("botonEnviar");
const botonReset          = document.getElementById("botonReset");
const campoNumeroFilas    = document.getElementById("numeroFilas");
const campoNumeroColumnas = document.getElementById("numeroColumnas");
const parrafoSalida       = document.getElementById("salida");

let matriz;

botonEnviar.addEventListener('click', ()=> {
    let filas = Number(campoNumeroFilas.value);
    let columnas = Number(campoNumeroColumnas.value);
    matriz =  Array(filas).fill().map( () => Array(columnas).fill(0));
    solicitarMatriz(filas, columnas , matriz, parrafoSalida);
    console.log(matriz);
});


botonReset.addEventListener('click', ()=>{ parrafoSalida.innerHTML = ''})


