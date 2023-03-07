// Elementos HTML
const inputNumero = document.getElementById("numero");
const botonEnviar = document.getElementById("enviar");
const botonColorFondo = document.getElementById("colorFondo");
const divCuadrados = document.getElementById("cuadrados");

// Función para generar un número aleatorio entre dos valores
function generarNumeroAleatorio(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

// Función para generar un color aleatorio en formato hexadecimal
function generarColorAleatorio() {
  return "#" + Math.floor(Math.random() * 16777215).toString(16);
}

// Función para dibujar n cuadrados de color, posición y tamaño aleatorio
function dibujarCuadrados(n) {
  divCuadrados.innerHTML = "";
  for (let i = 0; i < n; i++) {
    const tamano = generarNumeroAleatorio(10, 100);
    const posX = generarNumeroAleatorio(0, window.innerWidth - tamano);
    const posY = generarNumeroAleatorio(0, window.innerHeight - tamano);
    const color = generarColorAleatorio();
    const divCuadrado = document.createElement("div");
    divCuadrado.style.position = "absolute";
    divCuadrado.style.top = posY + "px";
    divCuadrado.style.left = posX + "px";
    divCuadrado.style.width = tamano + "px";
    divCuadrado.style.height = tamano + "px";
    divCuadrado.style.backgroundColor = color;
    divCuadrados.appendChild(divCuadrado);
  }
}

// Función para validar la entrada del usuario
function validarEntrada() {
  const numero = parseInt(inputNumero.value);
  if (isNaN(numero) || numero < 1 || numero > 50) {
    alert("Entrada no válida: debe estar comprendida entre 1 y 50");
  } else {
    dibujarCuadrados(numero);
  }
}

// Evento click del botón enviar
botonEnviar.addEventListener("click", validarEntrada);

// Evento click del botón color de fondo
botonColorFondo.addEventListener("click", function() {
  document.body.style.backgroundColor = generarColorAleatorio();
});
