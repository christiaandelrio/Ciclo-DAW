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
                    resultado[fila][columna] = parseFloat(resultado[fila][columna] ) + parseFloat(m1[fila][celda]) * parseFloat(m2[celda][columna]) ;
                }
            }
        }

        return resultado;
    }
}

// Codigo para probar la función
let M1 = [ [1,2,3], [4, 5, 6], [7, 8, 9]];
let M2 = [ [1, 4], [2, 5], [3, 6]];

M1 = M1.map( fila => fila.map(x => x * 2) );

let r = productoMatricial(M1, M2);

console.log(r);