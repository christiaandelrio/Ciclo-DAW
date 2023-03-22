const inputNumero = document.getElementById('numero');
const botonEnviar = document.getElementById('enviar');
const botonCambioColor = document.getElementById('cambioColor');
const botonReset = document.getElementById('resetear');
const salidas = document.getElementById('salidas');

//Creamos la función para obtener un término de la serie de fibonacci
function fib(n) {
	return n <= 1 ? n : fib(n - 1) + fib(n - 2);
}

//Lista de colores
const colores = [
	'coral',
	'red',
	'black',
	'pink',
	'green',
	'yellow',
	'pink',
	'orange',
	'deeppink',
	'lime',
];

//Función para obtener un número aleatorio
const numeroAleatorio = (max, min) => {
	return Math.floor(Math.random() * (max - 0 + min) + min);
};

//Función para agregar los números de fibonacci sin colores
botonEnviar.onclick = () => {
	let numero = inputNumero.value;

	if (confirm('¿Quieres enviar el número anterior?')) {
		if (numero < 1 || numero > 20) {
			salidas.innerHTML = `<p style="color: red;">Entrada no válida: debe estar comprendida entre 1 y 20</p>`;
			return;
		}

		salidas.innerHTML = '';
		for (let i = 1; i <= numero; i++) {
			let nuevoDiv;
			nuevoDiv = document.createElement('div');
			nuevoDiv.textContent = `${fib(i)}`;
			salidas.appendChild(nuevoDiv);
		}
	}
};

// Función para añadir los números de fibonacci con colores

botonCambioColor.onclick = () => {
	let numero = inputNumero.value;

	if (confirm('¿Quieres enviar el número anterior?')) {
		if (numero < 1 || numero > 20) {
			salidas.innerHTML = `<p style="color: red;">Entrada no válida: debe estar comprendida entre 1 y 20</p>`;
			return;
		}

		salidas.innerHTML = '';
		for (let i = 1; i <= numero; i++) {
			let nuevoDiv;
			nuevoDiv = document.createElement('div');
			nuevoDiv.textContent = `${fib(i)}`;
			let numColorAleatorio = numeroAleatorio(9, 0);
			nuevoDiv.style = `color: ${colores[numColorAleatorio]};`;
			salidas.appendChild(nuevoDiv);
		}
	}
};

// Función para borrar los elementos dibujados

botonReset.onclick = () => {
	salidas.innerHTML = '';
};
