const texto = document.getElementById('texto');
const salidas = document.getElementById('salidas');
const btnEnviar = document.getElementById('btnEnviar');
const divErrores = document.getElementById('errores');
const dificultad = document.getElementById('dificultad');
const intentos = document.getElementById('intentos');
const faltan = document.getElementById('faltan');
const juegoTerminado = document.getElementById('juegoTerminado');

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

//Efecto especial
function efectoEspecial() {
	document.body.style.backgroundColor = 'yellow';
	setTimeout(() => {
		document.body.style.backgroundColor = 'white';
	}, 2000);
}

function enviar() {
	salidas.innerHTML = '';

	efectoEspecial();

	if (!validarNumCaracteres() || !validarCaracteres()) {
		return false;
	}

	//Contador de las letras que faltan
	let contador = texto.value.length;
	//Número de intentos
	let intentosFallidos = 0;

	//Creamos la tabla
	const zonaDibujo = document.getElementById('zonadibujo');

	function dibujarTabla() {
		//Creamos una tabla de 30x30 que será el tablero de dibujo
		const tabla = document.createElement('table');
		//Agregamos la clase .tablerodibujo para que coja los estilos que ya nos proporcionan en el css
		tabla.classList.add('tablerodibujo');
		tabla.setAttribute('border', '1');

		//Creamos la tabla en función del número introducido
		let dificult = Number(dificultad.value);
		for (let i = 0; i < dificult; i++) {
			const tr = document.createElement('tr');
			for (let j = 0; j < dificult; j++) {
				const td = document.createElement('td');

				tr.appendChild(td);
			}
			tabla.appendChild(tr);
		}
		zonaDibujo.appendChild(tabla);

		//Ahora tenemos que agregar de forma aleatoria las letras de la palabra a las celdas:
		let txt = texto.value;
		//Recorremos las letras del string asignando a celdas de forma aleatoria
		for (let i = 0; i < txt.length; i++) {
			//Obtenemos el número de celdas total
			const celdas = tabla.getElementsByTagName('td');
			let numeroDeCeldas = celdas.length;

			//Obtener un número aleatorio entre 0 y el número de celdas sin repetirse:
			let numeroAleatorio =
				Math.floor(Math.random() * (numeroDeCeldas - 1 - 0 + 1)) + 0; //FIXME: Hay un bug. Puede repetirse el número aleatorio, de esa forma se pisan las letras. Para hacerlo bien habría que almacenar los números que vayan saliendo para repetirlos.

			//Si el número de celdas es menor que el número de letras mostramos mensaje de error
			if (numeroDeCeldas < txt.length) {
				divErrores.innerHTML =
					'El número de celdas es menor que el número de letras';
				return false;
			}

			//Agrego las letras a las celdas de forma aleatoria pero de forma oculta
			celdas[
				numeroAleatorio
			].innerHTML = `<p style="opacity: 0.5; margin: 0; padding: 0;">${txt[i]}</p>`; //Si hago de esta forma. Luego al pulsar la celda, el event.target es el párrafo.
		}

		const celdas = tabla.getElementsByTagName('td');
		//Ahora tengo que comprobar que cuando se pulsa una celda, su letra está en mi texto o no;
		function comprobarTexto(event) {
			//Obtengo la celda que se ha pulsado
			const celda = event.target; //Ojo. Esto es el párrafo dentro de la celda
			//console.log(celda);
			//Obtengo el contenido de la celda
			const contenidoCelda = celda.innerText.trim();
			//Comprueba si el contenido de la celda está en el string o no:
			if (contenidoCelda.length === 0) {
				console.log('El contenido no coincide');
				//Si no se acierta incrementamos el intentosFallidos;
				intentosFallidos++;
				intentos.innerHTML = 'Intendos fallidos ' + intentosFallidos;
			} else if (txt.includes(contenidoCelda)) {
				console.log('El contenido coincide');
				//Pintamos la letra de un color;
				celda.style.backgroundColor = 'deeppink';
				//Si acertamos reducimos el contador
				contador--;
				//Actualizamos el valor en el dom:
				faltan.innerHTML =
					'Te faltan ' + contador + ' letras por acertar';

				//Hacemos que el texto sea visible
				event.target.style.opacity = '1';
				//Eliminamos el evento para que no se pueda volver a pulsar. Hay que tener en cuenta que el event.target es el párrafo y su padre es el elemento td
				event.target.parentElement.removeEventListener(
					'click',
					comprobarTexto
				);
				//console.log(event.target.parentElement);

				//Cuando se acierta salta el Efecto especial:
				efectoEspecial();

				//Si el contador llega a cero se muestra un mensaje de juego terminado
				if (contador === 0) {
					juegoTerminado.innerHTML = 'Se ha acabado el juego';
				}
			}
		}

		//Agregamos la función a todas las celdas
		for (let i = 0; i < celdas.length; i++) {
			celdas[i].addEventListener('click', comprobarTexto);
		}
	}

	dibujarTabla();
	salidas.innerHTML += 'Todo ha ido bien';

	intentos.innerHTML = 'Intendos fallidos ' + intentosFallidos;
	faltan.innerHTML = 'Te faltan ' + contador + ' letras por acertar';
}

//Evento cuando damos a enviar
btnEnviar.addEventListener('click', enviar);

btnReset.addEventListener('click', () => {
	salidas.innerHTML = '';
	dificultad.value = null;
	texto.value = '';
	divErrores.value = '';
	contador = 0;
	juegoTerminado.innerHTML = 'Se ha acabado el juego';
});
