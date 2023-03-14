/*

*******
*     *
*     *
*******

*******
*******
*******
*******

*/

//4F, 7C 
function Fibonacci(datos,filas,columnas){
    var numTope= 25;
    var numa = 0;
    var numb = 1;
    var temp;
    datos[0]=numb; // Alamecenamos el primer elemento de la serie de Fibonacci en el array
    for(i = 1 ; i<=(filas*columnas); i++){
        temp = numa+numb;
        datos[i] = temp;
        //console.log(temp);
        numa = numb
        numb = temp;
    }
}

let filas = 3;//parseInt(prompt("Introduce num filas"));
let columnas = 3; // parseInt(prompt("Introduce num columnas"));
let datosFibonacci = new Array(filas*columnas);
Fibonacci(datosFibonacci,filas,columnas);
console.log(datosFibonacci);
let numerosSacados = 0;
for(i = 0; i<filas;i++){
    let fila = ""
    for(j = 0; j<columnas;j++){
        if(i==0 || i==filas-1 || j==0 || j==columnas-1){
            fila+=".."+datosFibonacci[numerosSacados];
            numerosSacados++;
        }else{
            fila+="   "
        }
      
    }
    console.log(fila);
}
