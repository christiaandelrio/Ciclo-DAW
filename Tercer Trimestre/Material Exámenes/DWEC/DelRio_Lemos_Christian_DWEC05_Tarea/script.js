//Definición de constantes
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

//Comenzamos llamando a la función iniciar 
window.onload = iniciar;

//Definición de la función iniciar, que reinicia la cookie, llama a validar y pasa a mayúsculas
function iniciar(){
    document.cookie = 'intentos=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';

	const botonEnviar = document.getElementById('enviar');
	botonEnviar.addEventListener('click',validar);

    nombre.addEventListener('blur',convertirMayusculas,false);
    apellidos.addEventListener('blur',convertirMayusculas,false);
}

//Funciones de validación
function validar(event) {
    // Obtenemos el número actual de intentos de envío del formulario desde la cookie.
    let intentos = parseInt(leerCookie("intentos")) || 0;
    
    // Incrementamos el número de intentos y actualizamos la cookie.
    crearCookie("intentos", intentos + 1, 30);
  
    // Mostramos el número actual de intentos en la página.
    divIntentos.innerHTML = `Intentos de envío del formulario: ${intentos + 1}`;
  
    // Validamos cada campo del formulario utilizando sus funciones correspondientes.
    if (
      validarNombre() &&
	  validarApellidos()&&
      validarEdad() &&
      validarNif() &&
      validarEmail() &&
      validarProvincia() &&
      validarFecha() &&
      validarTelefono() &&
      validarHora() &&
      confirm("¿Deseas enviar el formulario?")
    ) {
      // Si la validación es exitosa y el usuario confirma el envío, permitimos el envío del formulario.
      return true;
    } else {
      // Si la validación falla o el usuario cancela el envío, cancelamos el evento de envío por defecto.
      event.preventDefault();
      return false;
    }
}
  
function validarNombre(){
	if(nombre.value == ''){ //Si el nombre está vacío, devuelve false y pone el foco sobre el campo nombre
		divErrores.innerHTML = 'El campo nombre no debe estar vacío';

		nombre.focus();
		return false;
	}

	return true;
}

function validarApellidos(){
	if(apellidos.value == ''){
		divErrores.innerHTML = 'El campo apellidos no debe estar vacío';
		apellidos.focus();

		return false;
	}
	return true;
}

function validarFecha(){

	let patron=/^((0?[1-9])|(1\d)|(2\d)|(3[0-1]))[-|\/]((0?[1-9])|(1[0-2]))[-|\/]([1-2]\d{3})$/
	// Si no se cumple el patrón definido
	if (!patron.test(document.getElementById("fecha").value)){
		divErrores.innerHTML="Fecha errónea. Introdúzcala de nuevo (dd/mm/aaaa)";
		document.getElementById("fecha").focus();
		return false;
	}
	// Si llega aquí es que todo ha sido correcto
	return true;
}

function validarEdad(){
	const age = parseInt(edad.value.trim());
	// Si no es número o es inferior a 0 ó superior a 105
	if (isNaN(age) || age <0 || age >105){
			divErrores.innerHTML="La edad debe estar entre 0 y 105";
			edad.focus();
			return false;
	}
	
	return true;
}

function validarNif() {
	const dni = nif.value.trim();
	//Expresión regular DNI (\d{8} = 8 dígitos, [a-zA-Z] = una letra mayúscula o minúscula)
	let patron = /^\d{8}[a-zA-Z]$/;
	//Si el campo no cumple la expresión regular
	if (!patron.test(dni)) {
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

function validarEmail(){
	const mail = email.value.trim();
	let patron = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;//Comienza con una o más letras mayúsculas/minúsculas/números o ._- una @ letras o números, un punto y de 2 a 4 letras
	// Si no se cumple el patrón
	if (!patron.test(mail)){
		divErrores.innerHTML="El email introducido no es válido";
		// Situamos el foco en el campo email y le asignamos la clase error.
		email.focus();
			
		return false;
	}
	// Si llega aquí es que el email es correcto
		
	return true;
}

function validarTelefono(){

	const telf = telefono.value.trim();
    let patron =/^[69]\d{8}$/; //Comienza por un 6 o 9 y va seguido de 8 dígitos del 0 al 9

    if(!patron.test(telf)){ //Si no encaja en el patrón dará error
         divErrores.innerHTML = "El teléfono introducido no es válido";
         telefono.focus();
         
         return false;
    }

    return true;
}

function validarHora(){
	const horaVisita = hora.value.trim();
	let patron = /^\d{2}:\d{2}$/;

	if(!patron.test(horaVisita)){
		divErrores.innerHTML = 'Introduce una hora válida (hh:mm)';

		hora.focus();
		
		return false;
	}
	return true; //si no hay errores
}

function validarProvincia(){
	// Comprueba que la opción seleccionada sea diferente a 0.
	// Si es la 0 es que no ha seleccionado ningún nombre de Provincia.
	if (provincia.selectedIndex==0){
		divErrores.innerHTML="No has seleccionado ninguna provincia";
		//Situamos el foco sobre el campo provincia
		provincia.focus();
			
		return false;
	}
	// Si llega aquí es que sí hemos seleccionado alguna provincia
	return true;
}

function convertirMayusculas(){
    document.getElementById("nombre").value=document.getElementById("nombre").value.toUpperCase();
	document.getElementById("apellidos").value=document.getElementById("apellidos").value.toUpperCase();
}

//Función para crear y leer  una cookie
function crearCookie(nombre, valor, dias) {
	let fecha = new Date();
	fecha.setTime(fecha.getTime() + (dias * 24 * 60 * 60 * 1000));
	let expires = "expires=" + fecha.toUTCString();
	document.cookie = nombre + "=" + valor + ";" + expires + ";path=/";
  }
  

function leerCookie(nombre) {
  let nombreCookie = nombre + "=";
  let cadena = document.cookie.split(';');
  for (let i = 0; i < cadena.length; i++) {
    let c = cadena[i];
    while (c.charAt(0) == ' ') c = c.substring(1, c.length);
    if (c.indexOf(nombreCookie) == 0) return c.substring(nombreCookie.length, c.length);
  }
  return 0;
}


