const inputTexto = document.getElementById('texto');
const botonEnviar = document.getElementById('enviar');
const botonReset = document.getElementById('resetear');
const salidas = document.getElementById('salidas');

//Función para invertir el texto
function invertirCadena(cad) {
	return cad.split('').reverse().join('');
}

//Función para validar si son letras minúsculas y espacios
function is_letters(str) {
	var regex = /^[a-z\s]+$/;
	if (regex.test(str)) {
		return true;
	} else {
		return false;
	}
}

botonEnviar.onclick = () => {
	salidas.innerHTML = '';
	let texto = inputTexto.value.trim();

	if (!is_letters(texto)) {
		salidas.innerHTML =
			'<div style="color: red;">El texto sólo debe contener letras minúsculas y/o espacios</div>';
	} else {
		let textoInvertido;
		textoInvertido = invertirCadena(texto);
		salidas.innerHTML += textoInvertido + '</br>';

		if (texto === textoInvertido) {
			salidas.innerHTML +=
				'<div style="color: green;">¡¡Sí es un palíndromo!!</div>';
		} else {
			salidas.innerHTML +=
				'<div style="color: orange;">No es un palíndromo</div>';
		}
	}
};

// Función para borrar los elementos dibujados

botonReset.onclick = () => {
	salidas.innerHTML = '';
};
