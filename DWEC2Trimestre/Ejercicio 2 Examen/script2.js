// Elementos HTML
const inputNumero = document.getElementById("numero");
const botonEnviar = document.getElementById("enviar");
const botonColorFondo = document.getElementById("colorFondo");
const divCuadrados = document.getElementById("cuadrados");

// Función para generar un número aleatorio entre dos valores
function generarNumeroAleatorio(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }