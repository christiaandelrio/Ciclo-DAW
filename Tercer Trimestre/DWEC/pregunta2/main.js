//DECLARACIÓN DE VARIABLES
const frase = document.getElementById('frase');
const dificultad = document.getElementById('dificultad');
const jugar = document.getElementById('jugar');
const limpiar = document.getElementById('limpiar');
const tablero = document.getElementById('tablero');
const divErrores = document.getElementById('errores');
//Definimos el contador y los intentos fallidos
let cont = document.getElementById('contador');
let intentos = document.getElementById('intentos');

///FUNCIÓN PARA GENERAR UNA TABLA DE NxN CELDAS
function generarTabla(){
    let n = dificultad.value; //definimos la dificultad
    let tabla = document.createElement('table');
    tabla.setAttribute('border',1);
    tabla.setAttribute('id','tabla');
    tablero.appendChild(tabla);

    for(let i=0;i<n;i++){ //creamos NxN filas y columnas siendo n la dificultad
        tabla.appendChild(document.createElement('tr'));
        for(let j=0;j<n;j++){
            let celda = tabla.appendChild(document.createElement('td'));
           
        }
    }
    
   
}

//FUNCIÓN PARA METER LETRAS EN CELDAS ALEATORIAS
function letrasCeldas(){
    let celdas = document.getElementsByTagName('td');
    let cadena = frase.value;

    for(let i=0;i<cadena.length;i++){
        celdas[i].innerText = cadena[i];

      }
}


//FUNCIÓN PARA LIMPIAR EL TABLERO
function limpiarTabla(){
    tablero.innerHTML = '';
}

function validarEntradas(){
    

    if(frase.value == '' || frase.value>50){
        divErrores.innerHTML += 'La frase no puede estar vacía o ser inferior a 50 caracteres';
        return false;
        
    }
    if(dificultad.value == ''||dificultad.value<10||dificultad.value>50){
        divErrores.innerHTML += 'La dificultad no puede estar vacía y debe tener un valor entre 10 y 50';
        return false;
    }

    return true
}

jugar.addEventListener('click',()=>{
    if(validarEntradas()==true){
        generarTabla();
        letrasCeldas();
    }
});
limpiar.addEventListener('click',limpiarTabla);









//console.log(tabla.getElementsByTagName('td'));

//En las validaciones mejor primero la condición que no se cumple, así ya no sigue

// function pintarCeldas(evento){
//     let cel = evento.target;

//      if(evento.type == 'click'){
//          cel.style.backgroundColor = 'red';
//      }
// }

// //window.addEventListener('load',limpiarTabla);
// if(!condicion){
//     //avisa de que no se cumple
//     return false
//     }
    
//     //Sigue el resto