window.onload = function () {
	const zonaDibujo = document.getElementById('zonadibujo');

	//Creamos una tabla de 30x30 que será el tablero de dibujo
	const tabla = document.createElement('table');
	//Agregamos la clase .tablerodibujo para que coja los estilos que ya nos proporcionan en el css
	tabla.classList.add('tablerodibujo');
	tabla.setAttribute('border', '1');
	for (let i = 0; i < 30; i++) {
		const tr = document.createElement('tr');
		for (let j = 0; j < 30; j++) {
			const td = document.createElement('td');
			tr.appendChild(td);
		}
		tabla.appendChild(tr);
	}
	zonaDibujo.appendChild(tabla);

	//Accedemos a la paleta de colores;
	const paleta = document.getElementById('paleta');

	//Creamos una variable que almacena el color seleccionado de la paleta y le asignamos por defecto el color del primer elemento de la paleta que es el que viene seleccionado por defecto en el html
	let colorSeleccionado = window
		.getComputedStyle(paleta.getElementsByTagName('td')[0])
		.getPropertyValue('background-color');

	//Creamos una función que selecciona el color de la paleta accediendo a su background-color definido en el css
	function seleccionarColor(event) {
		const estilosElemento = window.getComputedStyle(event.target);
		colorSeleccionado =
			estilosElemento.getPropertyValue('background-color');
	}

	//Creamos una función que añade la clase "seleccionado" al elemento seleccionado de la paleta
	function agregarClaseSeleccionado(event) {
		const elementosPaleta = paleta.getElementsByTagName('td');
		//Eliminamos la clase "seleccionado" de todos los elementos de la paleta
		for (let i = 0; i < 6; i++) {
			elementosPaleta[i].classList.remove('seleccionado');
		}
		//Agregamos la clase "seleccionado" al elemento seleccionado
		event.target.classList.add('seleccionado');
	}

	//Agregamos las funciones a los elementos de la paleta cuando se hace click en ellos
	const elementosPaleta = paleta.getElementsByTagName('td');
	for (let i = 0; i < 6; i++) {
		elementosPaleta[i].addEventListener('click', seleccionarColor);
		elementosPaleta[i].addEventListener('click', agregarClaseSeleccionado);
	}

	//Una vez seleccionado un color de la paleta, haremos un click en una celda (que se pintará del color activo en la paleta) y desde ese momento al mover el ratón por el tablero pintará del color activo todas las celdas por las que vayamos pasando el ratón. En el momento que volvamos a hacer click en otra celda dejará de pintar.

	//Creamos una función que pinta la celda en la que se ha hecho click
	function pintarCelda(event) {
		//Pinta de color la celda en la que se ha hecho click
		const elemento = event.target;
		elemento.style.backgroundColor = colorSeleccionado;
	}

	//Creamos una variable que se pone a true cuando hacemos click en una celda y a false cuando volvemos a hacer click en otra celda
	let pintar = false;
	//Creamos una función que alterna el valor de la variable pintar
	function togglePintar() {
		pintar = !pintar;
	}

	//Creamos una función que cambia el texto del elemento con id "pincel" para indicar si el pincel está activado o no
	function cambiarTextoPincel() {
		const estadoPincel = document.getElementById('pincel');
		if (pintar) {
			estadoPincel.textContent = 'PINCEL ACTIVADO';
		} else {
			estadoPincel.textContent = 'PINCEL DESACTIVADO';
		}
	}

	//Creamos una función que pinta las celdas del tablero cuando el ratón pasa por encima de ellas
	function pintarCeldasRaton(event) {
		if (pintar) {
			const elemento = event.target;
			elemento.style.backgroundColor = colorSeleccionado;
		}
	}

	//Agregamos la función a las celdas del tablero
	const celdas = tabla.getElementsByTagName('td');
	for (let i = 0; i < celdas.length; i++) {
		celdas[i].addEventListener('click', pintarCelda);
		celdas[i].addEventListener('click', togglePintar);
		celdas[i].addEventListener('click', cambiarTextoPincel);
		celdas[i].addEventListener('mouseover', pintarCeldasRaton);
	}
};
