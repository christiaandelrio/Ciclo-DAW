/*Desarrolla una aplicación en JavaScript disponga de los controles e especificaciones siguientes:
• R1) Un elemento que permita solicitar e introducir al usuario un número entero comprendido entre 1 y 50.
• R2) Un botón (B1) "Enviar" que, cada vez que se hace clic en él, permita indicar al usuario que quiere enviar la
entrada anterior.
• R3) Si la entrada no es válida se sacará un mensaje: "Entrada no válida: debe estar comprendida entre 1 y 50" • R4) Un botón (B2) "Color fondo" que al realizar clic en el cambie el color de fondo de la página.
• R5) Al hacer clic en el botón B1 se validará la entrada( número N ) y, si cumple con los requisitos indicados, se
dibujarán en la página N cuadrados de color, posición y tamaño aleatorio.
• R6) Cada vez que se hace clic en el botón B2 cambiará el color de fondo de la página, dejando todo lo demás
como está (incluyendo los cuadrados dibujados).*/

const inputNumero = document.getElementById('numero');
const botonEnviar = document.getElementById('enviar');
const botonCambioColor = document.getElementById('cambioColor');
const botonReset = document.getElementById('resetear');
const salidas = document.getElementById('salidas');

function generarLetra() {
	const valores = [
		'a',
		'b',
		'c',
		'd',
		'e',
		'f',
		'0',
		'1',
		'2',
		'3',
		'4',
		'5',
		'6',
		'7',
		'8',
		'9',
	];
	let numero = (Math.random() * 15).toFixed(0);
	return valores[numero];
}

const crearColorHex = () => {
	var coolor = '';
	for (var i = 0; i < 6; i++) {
		coolor = coolor + generarLetra();
	}
	return '#' + coolor;
};

const numeroAleatorio = (max, min) => {
	return Math.floor(Math.random() * (max - 0 + min) + min);
};

//Función para crear los cuadrados de colores

botonEnviar.onclick = () => {
	let numero = inputNumero.value;

	if (confirm('¿Quieres enviar el número anterior?')) {
		if (numero < 1 || numero > 50) {
			salidas.innerHTML = `<p style="color: red;">Entrada no válida: debe estar comprendida entre 1 y 50</p>`;
			return;
		}

		salidas.innerHTML = '';
		for (let i = 1; i <= numero; i++) {
			let nuevoDiv;
			nuevoDiv = document.createElement('div');
			nuevoDiv.textContent = `${i}`;
			nuevoDiv.style =
				'width: 100px; height: 100px; position: absolute; border-radius: 5px;';
			nuevoDiv.style.top = `${numeroAleatorio(100, 0)}%`;
			nuevoDiv.style.left = `${numeroAleatorio(100, 0)}%`;
			nuevoDiv.style.backgroundColor = crearColorHex();

			salidas.appendChild(nuevoDiv);
		}
	}
};

// Función para cambiar el color de fondo del documento

botonCambioColor.onclick = () => {
	let colorFondo;
	colorFondo = crearColorHex();
	document.body.style.backgroundColor = colorFondo;
};

// Función para borrar los cuadrados de colores

botonReset.onclick = () => {
	salidas.innerHTML = '';
};
