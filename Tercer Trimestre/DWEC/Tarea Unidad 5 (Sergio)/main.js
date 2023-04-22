/*1.- Programar el código de JavaScript en un fichero independiente. La única modificación que se permite realizar en el fichero .html es la de escribir la referencia al fichero de JavaScript.*/

const formulario = document.getElementById('formulario');
const divErrores = document.getElementById('errores');
const divIntentos = document.getElementById('intentos');
const nombre = document.getElementById('nombre');
const apellidos = document.getElementById('apellidos');
const edad = document.getElementById('edad');
const nif = document.getElementById('nif');
const email = document.getElementById('email');
const provincia = document.getElementById('provincia');
const fecha = document.getElementById('fecha');
const telefono = document.getElementById('telefono');
const hora = document.getElementById('hora');

//Al cargar la página se ejecuta la función ejecutar
window.addEventListener('load', ejecutar, false);

function ejecutar() {
	//Borrar la cookie cada vez que se recarga la página
	document.cookie =
		'intentos=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';

	//Función que se ejecuta al pulsar el botón enviar
	const btnEnviar = document.getElementById('enviar');
	btnEnviar.addEventListener('click', enviar);

	/*3.- Cada vez que los campos NOMBRE y APELLIDOS pierdan el foco, el contenido que se haya escrito en esos campos se convertirá a mayúsculas.*/
	nombre.addEventListener('blur', function () {
		//this.value = this.value.toUpperCase();
		nombre.value = nombre.value.toUpperCase();
	});
	apellidos.addEventListener('blur', function () {
		//this.value = this.value.toUpperCase();
		apellidos.value = apellidos.value.toUpperCase();
	});

	//Borrar los mensajes de error al hacer cambios en el formulario
	formulario.addEventListener('change', function () {
		//Se borran los mensajes de error
		divErrores.innerHTML = '';
	});
}

function enviar(event) {
	//Prevenimos el comportamiento por defecto. Esto evita que se vaya a la página de destino a pesar de que no se hayan cumplido las validaciones.
	event.preventDefault();

	/*2.- Almacenar en una cookie el número de intentos de envío del formulario que se van produciendo y mostrar un mensaje en el contenedor "intentos" similar a: "Intento de Envíos del formulario: X". Es decir cada vez que le demos al botón de enviar tendrá que incrementar el valor de la cookie en 1 y mostrar su contenido en el div antes mencionado. Nota: para poder actualizar el contenido de un contenedor o div la propiedad que tenemos que modificar para ese objeto es innerHTML.*/
	//Se obtiene el valor de la cookie almacenado
	let intentos = getCookie('intentos');
	//Si no existe la cookie, se crea con valor 1
	if (intentos == '') {
		intentos = 1;
	} else {
		//Si existe, se incrementa en 1
		parseInt(intentos);
		intentos++;
	}
	//Se almacena el valor de la cookie
	document.cookie = `intentos=${intentos}`;

	//Se muestra el número de intentos
	divIntentos.innerHTML = `Intento de Envíos del formulario: ${intentos}`;

	//Se validan los campos. Si no se cumple alguna validación retornamos false
	if (
		!validarNombre() ||
		!validarApellidos() ||
		!validarEdad() ||
		!validarNif() ||
		!validarEmail() ||
		!validarProvincia() ||
		!validarFecha() ||
		!validarTelefono() ||
		!validarHora()
	) {
		return false;
	}

	/*12.- Pedir confirmación de envío del formulario. Si se confirma el envío realizará el envío de los datos; en otro caso cancelará el envío.
	 */
	if (!confirm('¿Desea enviar el formulario?')) return false;

	//Se envía el formulario si todo es correcto
	formulario.submit();

	//Se muestra un mensaje de confirmación con todos los datos del formulario.
	alert(
		'Formulario enviado correctamente con los siguientes datos:' +
			'\nNombre: ' +
			nombre.value +
			'\nApellidos: ' +
			apellidos.value +
			'\nEdad: ' +
			edad.value +
			'\nNIF: ' +
			nif.value +
			'\nEmail: ' +
			email.value +
			'\nProvincia: ' +
			provincia.value +
			'\nFecha: ' +
			fecha.value +
			'\nTeléfono: ' +
			telefono.value +
			'\nHora: ' +
			hora.value
	);

	//Se borran los mensajes de error
	divErrores.innerHTML = '';
}

//Función para obtener el valor de una cookie
function getCookie(cookieName) {
	//Nombre de la cookie que se está buscando
	const name = cookieName + '=';
	//Recuperamos la cookie almacenada
	const storedCookie = document.cookie;
	//Separamos las diferentes partes de la cookie (se almacenan en un array)
	const cookieParts = storedCookie.split(';');
	//Se recorre el array
	for (let i = 0; i < cookieParts.length; i++) {
		//Recorremos cada parte de la cookie
		let c = cookieParts[i];
		//Si hay espacios en blanco al principio, se eliminan
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		//Buscamos la cookie. Si se encuentra, se devuelve su valor
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	//Si no se encuentra la cookie, se devuelve una cadena vacía
	return '';
}

/*4.- Realizar una función que valide los campos de texto NOMBRE y APELLIDOS. Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en los campos correspondientes.*/
function validarNombre() {
	//Se comprueba que el campo no esté vacío
	if (nombre.value == '') {
		//Se muestra el mensaje de error
		divErrores.innerHTML = 'El campo "Nombre" no puede estar vacío';
		//Se pone el foco en el campo
		nombre.focus();
		return false;
	}
	//Si no hay errores, se devuelve true
	return true;
}

function validarApellidos() {
	//Se comprueba que el campo no esté vacío
	if (apellidos.value == '') {
		//Se muestra el mensaje de error
		divErrores.innerHTML = 'El campo "Apellidos" no puede estar vacío';
		//Se pone el foco en el campo
		apellidos.focus();
		return false;
	}
	//Si no hay errores, se devuelve true
	return true;
}

/*5.- Validar la EDAD que contenga solamente valores numéricos y que esté en el rango de 0 a 105. Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo EDAD.*/

function validarEdad() {
	//Obtenemos el valor del campo edad, le quitamos los espacios en blanco y lo convertimos a número
	age = parseInt(edad.value.trim());
	if (age === '' || isNaN(age) || age < 0 || age > 105) {
		divErrores.innerHTML = 'El campo edad debe ser un número entre 0 y 105';
		edad.focus();
		return false;
	}
	return true;
}

/*6.- Validar el NIF. Utilizar una expresión regular que permita solamente 8 números un guión y una letra. Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo NIF. No es necesario validar que la letra sea correcta. Explicar las partes de la expresión regular mediante comentarios.*/
function validarNif() {
	const dni = nif.value.trim();
	//Expresión regular DNI (\d{8} = 8 dígitos, [a-zA-Z] = una letra mayúscula o minúscula)
	const regexNif = /^\d{8}[a-zA-Z]$/;
	//Si el campo no cumple la expresión regular
	if (!regexNif.test(dni)) {
		//Se muestra el mensaje de error
		divErrores.innerHTML =
			'El campo NIF debe contener 8 números y una letra';
		//Se pone el foco en el campo
		nif.focus();
		return false;
	}
	//Si no hay errores, se devuelve true
	return true;
}

/*7.- Validar el E-MAIL. Utilizar una expresión regular que nos permita comprobar que el e-mail sigue un formato correcto. Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo E-MAIL. Explicar las partes de la expresión regular mediante comentarios.*/
function validarEmail() {
	const correo = email.value.trim();
	//Expresión regular EMAIL (^[a-zA-Z0-9._-]+ = una o más letras mayúsculas o minúsculas, números o los caracteres . _ -, @ = una arroba, [a-zA-Z0-9.-]+ = una o más letras mayúsculas o minúsculas, números o los caracteres . -, \. = un punto, [a-zA-Z]{2,4} = 2 o 4 letras mayúsculas o minúsculas)
	const regexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	//Si el campo no cumple la expresión regular
	if (!regexEmail.test(correo)) {
		//Se muestra el mensaje de error
		divErrores.innerHTML = 'Introduzca un email válido';
		//Se pone el foco en el campo
		email.focus();
		return false;
	}
	//Si no hay errores, se devuelve true
	return true;
}

/*8.- Validar que se haya seleccionado alguna de las PROVINCIAS. Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo PROVINCIA.*/
function validarProvincia() {
	//Se comprueba si se ha selccionado alguna provincia
	if (provincia.selectedIndex === 0 || provincia.value === '0') {
		//Se muestra el mensaje de error
		divErrores.innerHTML = 'Debe seleccionar una provincia';
		//Se pone el foco en el campo
		provincia.focus();
		return false;
	}
	//Si no hay errores, se devuelve true
	return true;
}

/*9.- Validar el campo FECHA utilizando una expresión regular. Debe cumplir alguno de los siguientes formatos: dd/mm/aaaa o dd-mm-aaaa. No se pide validar que sea una fecha de calendario correcta. Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo FECHA. Explicar las partes de la expresión regular mediante comentarios.*/
function validarFecha() {
	const date = fecha.value.trim();
	//Expresión regular FECHA (\d{1,2} = 1 o 2 dígitos, [-\/] = un guión o una barra, \d{1,2} = 1 o 2 dígitos, [-\/] = un guión o una barra, \d{4} = 4 dígitos)
	const regexDate = /^\d{1,2}[-\/]\d{1,2}[-\/]\d{4}$/;
	//Si no se cumple la expresión regular
	if (!regexDate.test(date)) {
		//Se muestra el mensaje de error
		divErrores.innerHTML =
			'Introduzca una fecha válida (dd/mm/aaaa) o (dd-mm-aaaa) o (d/m/aaaa) o (d-m-aaaa)';
		//Se pone el foco en el campo
		fecha.focus();
		return false;
	}
	//Si no hay errores, se devuelve true
	return true;
}

/*10.- Validar el campo TELEFONO utilizando una expresión regular. Debe permitir 9 dígitos obligatorios. Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo TELEFONO. Explicar las partes de la expresión regular mediante comentarios.*/
function validarTelefono() {
	const phone = telefono.value.trim();
	//Expresión regular TELEFONO ([\+]? = ninguno o un signo +, \d{9,11} = 9 u 11 dígitos)
	const regexPhone = /^[\+]?\d{9,11}$/;
	//Si no se cumple la expresión regular
	if (!regexPhone.test(phone)) {
		//Se muestra el mensaje de error
		divErrores.innerHTML =
			'Introduzca un teléfono válido (+ddddddddddd o ddddddddd)';
		//Se pone el foco en el campo
		telefono.focus();
		return false;
	}
	//Si no hay errores, se devuelve true
	return true;
}

/*11.- Validar el campo HORA utilizando una expresión regular. Debe seguir el patrón de hh:mm. No es necesario validar que sea una hora correcta. Si se produce algún error mostrar el mensaje en el contenedor "errores" y poner el foco en el campo HORA. Explicar las partes de la expresión regular mediante comentarios.*/
function validarHora() {
	const time = hora.value.trim();
	//Expresión regular HORA (\d{2} = 2 dígitos, : = dos puntos, \d{2} = 2 dígitos)
	const regexTime = /^\d{2}:\d{2}$/;
	//Si no se cumple la expresión regular
	if (!regexTime.test(time)) {
		//Se muestra el mensaje de error
		divErrores.innerHTML = 'Introduzca una hora válida (hh:mm)';
		//Se pone el foco en el campo
		hora.focus();
		return false;
	}
	//Si no hay errores, se devuelve true
	return true;
}
