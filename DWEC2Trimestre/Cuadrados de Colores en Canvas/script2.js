// Elementos
const numero = document.getElementById("numero");
const enviar = document.getElementById("enviar");
const reset = document.getElementById("reset");

//Definimos el canvas y el contexto
const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

// Función para generar un número aleatorio entre dos valores
function generarNumeroAleatorio() {
    return Math.floor(Math.random()*100);
}

// Función para generar un color aleatorio en formato hexadecimal
function generarColorAleatorio() {
  return "#" + Math.floor(Math.random() * 16777215).toString(16);
}

//Función para limpiar el canvas
function limpiarCanvas() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);
}

//Rellenamos el canvas
function dibujarCuadrado() {

  x = generarNumeroAleatorio();
  y = generarNumeroAleatorio();
  width = 10;
  height = 10;

  ctx.fillStyle = generarColorAleatorio();
  ctx.fillRect(x, y, width, height);  
}

//Eventos
enviar.addEventListener('click', ()=>{

  for(let i=0;i<numero.value;i++){
    dibujarCuadrado();
  }  
});

reset.addEventListener('click', ()=>{
  limpiarCanvas();
})