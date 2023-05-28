// 1) Primero definimos variables y les asignamos a cada una las refencias a elementos HTML
const enviar = document.getElementById('enviar');
const borrar = document.getElementById('borrar');
const num = document.getElementById('numeroN');
const parrafo=document.getElementById("parrafo");
// 2) Almacenamos en variables caracteres especiales necesarios (espacio en blanco, un punto y un salto de línea)
const espacio='\u00a0';
const punto='.';
const saltoLinea='\n';

// 3) Función para calcular la serie de fibonacci. Recibe un parámetro 'termino' para calcular el siguiente número a ese en la serie de Fibonacci
function calcularFibonacci (termino) {
    // 4) Si el termino es menor que 2, lo devolvemos simplemente
    if ( termino < 2 ) {
        return termino;
    } else {
        // 5) Si es mayor, restamos 1 y 2 respectivamente al termino, y sumamos los resultados
        return  calcularFibonacci(termino - 1) + calcularFibonacci(termino - 2);
    }
}

// 6) Función para imprimir un nº de la serie Fibonacci y devolverlo como cadena. Recibe un parámetro número, que es el que queremos formatear
function imprimirFibonacci(numero){ 
    // 7) Obtenemos el numero de la serie que queremos formatear
    let fibonacci = calcularFibonacci(numero);
    
    if(fibonacci<10){
        // 8) Si el nº es menor de 10, devolvemos dos puntos seguidos del número y seguidos de dos espacios blancos
        return punto.repeat(2) + (fibonacci) + espacio.repeat(2);
    }else if(fibonacci<100){
        // 9) Si el nº es menor de 100, devolvemos un punto seguido del número y seguidos de dos espacios blancos
        return punto.repeat(1) + (fibonacci) + espacio.repeat(2);//siendo menor de 100 1
    }else{
        // 10) Si el número es mayor o igual a 100, devolvemos solo el nº seguido de dos espacios blancos
        return punto.repeat(0) + (fibonacci) + espacio.repeat(2);//si es mayor de 100 ninguno
    }

}

// 11) Este evento se dispara cuando hacemos clic en el boton enviar
enviar.addEventListener('click', () => {
    // 12) Limpiamos cualquier resultado anterior
    parrafo.innerText='';
    // 13) Obtenemos el valor del numero que introducimos en el input
    const numero = Number(num.value);
    if(num.value=''){
        // 14) Si no hemos introducido un número, mostramos alerta y ponemos el foco en el input de entrada
        alert("Por favor, introduzca un número");
        num.focus();
    } else if (numero<1 || numero>8){
        // 15) Si el nº es menor de 1 o mayor de 8, mostramos otra alerta y ponemos foco nuevamente
        alert("Por favor, introduzca un número comprendido entre 1 y 8");
        num.focus();
    } else {
        let contador = 0;
        // 16) Si el número ingresado es válido, iniciamos un bucle, que itera sobre las filas y columnas de una matriz de tamaño 'numero' x 'numero'
        for (let fila = 1; fila <= numero; fila++) {
            for (let columna = 1; columna <= numero; columna++) {
                // 17) Si la fila o columna actual es el borde de la matriz
                if ( fila == 1 || fila == numero || columna == 1 || columna == numero) {
                    // 18) Llamamos a 'imprimirFibonacci' para mostrar el nº correspondiente de Fibonacci, e incrementamos el contador
                    parrafo.innerText += imprimirFibonacci(contador);
                    contador++;
                } else {
                    // 19) Si no es el borde de la matriz, el interior lo pintamos vacío
                    parrafo.innerText += espacio.repeat(5);
                }
            }
            // 20) Por cada vez que se itere, saltamos una línea
            parrafo.innerText += saltoLinea.repeat(2);
        }
    }
});

// 21) Este evento se dispara cuando hacemos clic en el botón borrar
borrar.addEventListener("click", limpiarEntradas);

// 22) Función del evento anterior
function limpiarEntradas(){
    // 23) Restablecemos los valores del campo de entrada y del parrafo de salida, y ponemos foco en el campo de entrada
    num.value='';
    parrafo.innerText='';
    num.focus();
}
  
  
  
  
  
  
  
  
  
  