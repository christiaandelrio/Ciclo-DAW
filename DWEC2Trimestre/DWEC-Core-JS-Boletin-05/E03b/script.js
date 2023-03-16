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
                // Convertimos una columna de m2 en un vector:
                let transpuesto = m2.map( filaMatriz => filaMatriz[columna] );
                // Multiplicamos 2 vectores:
                // (fíjate en que la función flecha no tiene return pues tiene una única sentencia)
                resultado[fila][columna] = m1[fila].reduce( (r, a, i) =>  r + a * transpuesto[i] , 0);
            }
        }

        return resultado;
    }
}

// Codigo para probar la función
let M1 = [ [1,2,3], [4, 5, 6], [7, 8, 9]];
let M2 = [ [1, 4], [2, 5], [3, 6]];

// M1 = M1.map( fila => fila.map(x => x * 2) );

let r = productoMatricial(M1, M2);

console.log(r);