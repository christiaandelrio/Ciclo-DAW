/*
   CALCULA EL PRODUCTO MATRICIAL DE 2 MATRICES Y LO DEVUELVE COMO UN ARRAY
*/
function productoMatricial(m1, m2) {
    // Comprobar dimensiones
    const m1Filas    = m1.length;
    const m1Columnas = m1[0].length;
    const m2Filas    = m2.length;
    const m2Columnas = m2[0].length;

    if ( m1Columnas !== m2Filas ) {
        throw new Error("Dimensiones de matrices no compatibles");
    } else {
        // Poner a 0 el array resultado
        let resultado = Array(m1Filas).fill().map( () => Array(m2Columnas).fill(0));

        for(let fila = 0; fila < m1Filas; fila++) {
            for(let columna = 0; columna < m2Columnas; columna++) {
                for(let celda = 0; celda < m1Columnas; celda++) { 
                    resultado[fila][columna] = parseFloat(resultado[fila][columna] ) + 
                                               parseFloat(m1[fila][celda]) * 
                                               parseFloat(m2[celda][columna]) ;
                }
            }
        }

        return resultado;
    }
}


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



/*
   IMPRIME UNA MATRIZ
*/
function imprimirMatriz(matriz, manipulador) {
    // Recoger dimensiones matriz
    let filas    = matriz.length;
    let columnas = matriz[0].length;

    // Vaciar elemento contenedor matriz
    manipulador.innerHTML = '';

    // Procesar
    let tabla = document.createElement("table");
    tabla.setAttribute("class", "matrix");
    let elementoHTML = document.createElement("thead");
    tabla.appendChild(elementoHTML);
    let tablaBody = document.createElement("tbody");
    tabla.appendChild(tablaBody);

    for(let fila = 0; fila < filas; fila++) {
        let filaHTML = document.createElement("tr");
        tablaBody.appendChild(filaHTML);

        for(let columna = 0; columna < columnas; columna++) {
            let celda = document.createElement("td");
            celda.innerText = matriz[fila][columna];      
            filaHTML.appendChild(celda);
        }
    }

    manipulador.appendChild(tabla);
}



// --------------------------------------------------------------------------------------
let matriz1;
let matriz2;
let producto;

// --------------------------------------------------------------------------------------
const botonEnviar1         = document.getElementById("botonEnviar1");
const botonReset1          = document.getElementById("botonReset1");
const campoNumeroFilas1    = document.getElementById("numeroFilas1");
const campoNumeroColumnas1 = document.getElementById("numeroColumnas1");
const parrafoSalida1       = document.getElementById("salida1");


botonEnviar1.addEventListener('click', ()=> {
    let filas = Number(campoNumeroFilas1.value);
    let columnas = Number(campoNumeroColumnas1.value);
    matriz1 =  Array(filas).fill().map( () => Array(columnas).fill(0));
    solicitarMatriz(filas, columnas , matriz1, parrafoSalida1);
});

botonReset1.addEventListener('click', ()=>{ parrafoSalida1.innerHTML = ''});

// --------------------------------------------------------------------------------------
const botonEnviar2         = document.getElementById("botonEnviar2");
const botonReset2          = document.getElementById("botonReset2");
const campoNumeroFilas2    = document.getElementById("numeroFilas2");
const campoNumeroColumnas2 = document.getElementById("numeroColumnas2");
const parrafoSalida2       = document.getElementById("salida2");


botonEnviar2.addEventListener('click', ()=> {
    let filas = Number(campoNumeroFilas2.value);
    let columnas = Number(campoNumeroColumnas2.value);
    matriz2 =  Array(filas).fill().map( () => Array(columnas).fill(0));
    solicitarMatriz(filas, columnas , matriz2, parrafoSalida2);
});

botonReset1.addEventListener('click', ()=>{ parrafoSalida2.innerHTML = ''});


// --------------------------------------------------------------------------------------
const calcularProducto   = document.getElementById("calcularProducto");
const borrarProducto     = document.getElementById("borrarProducto");
const parrafoSalida3     = document.getElementById("salida3");


calcularProducto.addEventListener('click', ()=> {
    parrafoSalida3.innerHTML = '';
    producto = productoMatricial(matriz1, matriz2);
    imprimirMatriz(producto, parrafoSalida3);
});

borrarProducto.addEventListener('click', () => { parrafoSalida3.innerHTML = ''});




