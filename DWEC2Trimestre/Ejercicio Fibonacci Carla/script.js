//Botones
const enviar = document.getElementById('enviar');
const borrar = document.getElementById('borrar');

//Variables
const num = document.getElementById('numeroN');
const parrafo=document.getElementById("parrafo");
const espacio='\u00a0';
const punto='.';
const saltoLinea='\n';

//Función para calcular el siguiente término de la serie de Fibonacci a partir de un término dado
function calcularFibonacci (termino) {
    if ( termino < 2 ) {
        return termino;
    } else {
        return  calcularFibonacci(termino - 1) + calcularFibonacci(termino - 2);
    }
}

//Función para imprimir el siguiente término de la serie
function imprimirFibonacci(numero){ 
    let fibonacci = calcularFibonacci(numero);

    if(fibonacci<10){
        return punto.repeat(2) + (fibonacci) + espacio.repeat(2); //si el término a imprimir es menor que diez le añadimos dos puntos delante
    }else if(fibonacci<100){
        return punto.repeat(1) + (fibonacci) + espacio.repeat(2);//siendo menor de 100 1
    }else{
        return punto.repeat(0) + (fibonacci) + espacio.repeat(2);//si es mayor de 100 ninguno
    }

}

//Eventos

enviar.addEventListener('click', ()=>{
    parrafo.innerText='';
    const numero=Number(num.value);
    if(num.value=''){
        alert("Por favor, introduzca un número");
        num.focus();
    } else if (numero<1 || numero>8){
        alert("Por favor, introduzca un número comprendido entre 1 y 8");
        num.focus();
    } else {
        let contador=0;
        for (let fila = 1; fila <= numero; fila++) {
            for (let columna = 1; columna <= numero; columna++) {
                if ( fila    == 1 || fila == numero ||
                        columna == 1 || columna == numero) {
                    parrafo.innerText += imprimirFibonacci(contador);
                    contador++;
                } else {
                    //El interior lo pintamos vacío
                    parrafo.innerText += espacio.repeat(5);
                }
            }
            //Por cada vez que se itere, saltamos una línea
            parrafo.innerText +=saltoLinea.repeat(2);
        }
    }
});

borrar.addEventListener("click", limpiarEntradas);

function limpiarEntradas(){
    num.value='';
    parrafo.innerText='';
    num.focus();
}