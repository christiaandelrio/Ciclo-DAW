//Función para validar la fecha
function validarFecha() {
	let fecha = document.getElementById('title').value;

	//Si la fecha está vacía mostramos una alerta
	if (fecha.length == 0) {
		alert('Elija una fecha.');
		return false;
	}

	//Obtenemos la fecha actual
	let miFecha = new Date(fecha);
	let hoy = new Date();

	hoy.setHours(0, 0, 0, 0);
	miFecha.setHours(0, 0, 0, 0);

	//Comprobamos que la fecha introducida no sea anterior a la actual
	if (miFecha < hoy) {
		alert('La fecha no puede se inferior a la actual');
		return false;
	}

	return true;
}

//Función para obtener las coordenadas. Es una función que se define en Tools.php
function getCoordenadas() {
	//Capturamos la dirección del formulario
	let dir = document.getElementById('dir').value;

	//Llamamos a la función Jaxon definida en Tools.php
	jaxon_getCoordenadas(dir);
	return true;
}

//Función para ordenar envíos de cara a crear la ruta de reparto. Se le pasa como parámetro el id de la lista de tareas (reparto)
function ordenarEnvios(id) {
	//Capturamos los puntos
	let puntos = $('#t_' + id + ' input:hidden')
		.map(function () {
			return this.value;
		})
		.get()
		.join('|');

	//Llamamos a la función Jaxon definida en Tools.php
	jaxon_ordenarEnvios(puntos, id);
}

//Función que se llama cuando se llama a la función de ordenarEnvios de Tools.php
function obtuvimosDatos(datosRespuesta) {
	// Si no obtuvimos una respuesta, mostramos un mensaje de error
	if (datosRespuesta['respuesta'] == '404') {
		alert(
			'Servicio para ordenar Rutas de Bing Maps no disponible temporalmente'
		);
		return datosRespuesta['respuesta'];
	}

	// Si obtuvimos una respuesta, reordenamos los envíos del reparto
	// Cogemos la URL base del documento, quitando los parámetros GET si los hay
	let url = 'http://localhost/dwes_tema_08/TAREA_08/public/repartos.php';

	// Añadimos el código de la lista de reparto
	url += '?action=oEnvios&idLt=' + datosRespuesta['id'];
	const respuesta = datosRespuesta['respuesta'];

	// Y un array con las nuevas posiciones que deben ocupar los envíos
	//for (var r of datosRespuesta['respuesta']) url += '&pos[]=' + respuesta[r];
	for (let i = 0; i < respuesta.length; i++) url += '&pos[]=' + respuesta[i];

	//Nos redirigimos a la URL que será la de repartos.php con todos los parámetros que hemos definido para que salgan los envíos ordenados
	window.location = url;
}

//semaforo es una función de validacion que captura la latitud y el producto del formulario y comprueba que no estén vacíos
function semaforo() {
	var latitud = document.getElementById('lat').value;
	var pro = document.getElementById('pro').value;

	if (latitud.length == 0 || pro == -1) {
		//alert("Elija un producto.");
		return false;
	}

	return true;
}
