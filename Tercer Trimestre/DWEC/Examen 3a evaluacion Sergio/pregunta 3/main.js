const texto = document.getElementById('texto');
const salidas = document.getElementById('salidas');
const btnEnviar = document.getElementById('btnEnviar');
const divErrores = document.getElementById('errores');
const actualizarBtn = document.getElementById('actualizar');

function validarNumCaracteres() {
	//Se comprueba que el campo no esté vacío
	if (texto.value.length === 0 || texto.value.length > 50) {
		//Se muestra el mensaje de error
		divErrores.innerHTML =
			'El campo del texto no puede estar vacío o tener más de 50 caracteres';
		return false;
	}
	//Si no hay errores, se devuelve true
	return true;
}

function validarCaracteres() {
	let text = texto.value.trim();
	//Expresión regular que permite letras mayúsculas y minúsculas y espacios y un máximo de 50 caracteres
	const regex = /^[a-zA-ZÑñáéíóúÁÉÍÓÚ\s]{1,50}$/;

	//Si el campo no cumple la expresión regular
	if (!regex.test(text)) {
		divErrores.innerHTML =
			'El campo solo puede contener letras del alfabeto español y espacios';
		return false;
	}
	//Si no hay errores, se devuelve true
	return true;
}

function dibujaForma() {
	let txt = texto.value;

	//Dibujar las letras
	for (let i = 0; i < txt.length; i++) {
		console.log(txt[i]);
		salidas.innerHTML += txt[i];
		//Aquí habría que dibujar las letras con la forma haciendo uso de ciclos for
	}
}

function enviar() {
	salidas.innerHTML = '';

	if (!validarNumCaracteres() || !validarCaracteres()) {
		return false;
	}

	dibujaForma();

	//salidas.innerHTML += 'Todo ha ido bien';
}

btnEnviar.addEventListener('click', enviar);

btnReset.addEventListener('click', () => {
	salidas.innerHTML = '';
});

const actualizar = async () => {
	try {
		//Instanciamos el objeto XMLHttpRequest
		const xhttp = new XMLHttpRequest();
		//Creamos la petición
		xhttp.open(
			'GET',
			'https://api.openweathermap.org/data/2.5/weather?&lat=42.264799151433&lon=-8.765128490705925&units=metric&lang=es&appid=8a2423b28d69b2f14a25bca771904482',
			true
		);
		//Enviamos la petición
		xhttp.send();

		//Mostramos el loader mientras no se cargan los datos
		salidas.innerHTML = '<h3 class="loader">Cargando...</h3>';

		//Recibimos los datos
		xhttp.onreadystatechange = function () {
			//Si la petición se ha completado y la respuesta es correcta mostramos los datos
			if (this.readyState == 4 && this.status == 200) {
				let datos = JSON.parse(this.responseText);
				let description = datos.weather[0].description;

				//Una vez que tenemos los datos los dibujamos:
				salidas.innerHTML = description;

				//Aquí tendría que dibujar la forma con ciclos for
			} else {
				//Si la petición no se ha completado o la respuesta no es correcta mostramos un error
				salidas.innerHTML = `Se ha producido un error`;
			}
		};
	} catch (error) {
		//Si se produce un error mostramos un mensaje
		console.log('Se ha producido un error' + error);
		salidas.innerHTML = `Se ha producido un error`;
	}
};

actualizarBtn.addEventListener('click', actualizar);
