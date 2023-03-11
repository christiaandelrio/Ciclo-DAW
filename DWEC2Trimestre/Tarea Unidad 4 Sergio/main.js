const resultados = document.getElementById('resultados');

class Edificio {
	constructor(calle, numero, codigo) {
		this.calle = calle;
		this.numero = numero;
		this.codigo = codigo;
		this.plantas = [];

		//Mensaje que se va a mostrar cada vez que instanciamos un objeto de la clase Edificio
		resultados.innerHTML += `Construido nuevo edificio en calle: ${this.calle}, nº: ${this.numero}, CP: ${this.codigo}. </br>`;
	}

	agregarPlantasYPuertas(numplantas, puertas) {
		let numeroPlantasExistentes = this.plantas.length + 1;

		if (numplantas <= 0 || puertas <= 0) {
			resultados.innerHTML += `No se han podido agregar ${numplantas} plantas y ${puertas} puertas porque deben ser superiores a 0 </br>`;
			return;
		}

		for (let i = 0; i < numplantas; i++) {
			const planta = {
				id: numeroPlantasExistentes + i,
				puertas: [],
			};

			for (let j = 1; j <= puertas; j++) {
				const puerta = {
					id: j,
					propietario: '',
				};

				planta.puertas.push(puerta);
			}

			this.plantas.push(planta);
		}
	}

	modificarNumero(numero) {
		this.numero = numero;
	}

	modificarCalle(calle) {
		this.calle = calle;
	}

	modificarCodigoPostal(codigo) {
		this.codigo = codigo;
	}

	imprimeCalle(nombreEdificio) {
		resultados.innerHTML += `La calle del edificio ${nombreEdificio}: ${this.calle} </br>`;
	}

	imprimeDireccion(nombreEdificio) {
		resultados.innerHTML += `El edificio ${nombreEdificio} está situado en la calle ${this.calle}, número ${this.numero} </br>`;
	}

	imprimeNumero() {
		resultados.innerHTML += `Número: ${this.numero} </br>`;
	}

	imprimeCodigoPostal(nombreEdificio) {
		resultados.innerHTML += `Código postal del edificio ${nombreEdificio}: ${this.codigo} </br>`;
	}

	agregarPropietario(nombre, planta, puerta) {
		if (puerta <= 0 || puerta <= 0) {
			resultados.innerHTML += `Propietario ${nombre} no agregado a la planta ${planta} y puerta ${puerta} porque no existe</br>`;
			return;
		}

		this.plantas[planta - 1].puertas[puerta - 1].propietario = nombre;
		resultados.innerHTML += `${nombre} es ahora el propietario de la puerta ${puerta} de la planta ${planta}. </br>`;
	}

	imprimePlantas() {
		if (this.plantas.length === 0) {
			resultados.innerHTML +=
				'No se han añadido plantas al edificio por el momento. </br>';
			return;
		}

		for (let i = 0; i < this.plantas.length; i++) {
			for (let j = 0; j < this.plantas[i].puertas.length; j++) {
				resultados.innerHTML += `Propietario del piso ${this.plantas[i].puertas[j].id} de la planta ${this.plantas[i].id}: ${this.plantas[i].puertas[j].propietario} </br>`;
			}
		}
	}
}

// Crear edificios
resultados.innerHTML += '</br><b>Crear Edificios A, B y C:</b></br></br>';

const edificioA = new Edificio('Garcia Prieto', 58, 15706);
const edificioB = new Edificio('Camino Caneiro', 29, 32004);
const edificioC = new Edificio('San Clemente', 's/n', 15705);

// Mostrar códigos postales
resultados.innerHTML += '</br><b>Mostrar información:</b></br></br>';

edificioA.imprimeCodigoPostal('A');
edificioC.imprimeCalle('C');
edificioB.imprimeDireccion('B');

// Agregar propietarios al Edificio A
resultados.innerHTML +=
	'</br><b>Agregar propietarios al edificio A: </b></br></br>';

edificioA.agregarPlantasYPuertas(2, 3);
edificioA.agregarPropietario('José Antonio López', 1, 1);
edificioA.agregarPropietario('Luísa Martinez ', 1, 2);
edificioA.agregarPropietario('Marta Castellón', 1, 3);
edificioA.agregarPropietario('Antonio Pereira', 2, 2);

// Mostrar el listado de propietarios del Edificio A
resultados.innerHTML +=
	'</br><b>Listado de propietarios del edificio calle García Prieto número 58 (edificio A): </b></br></br>';

edificioA.imprimePlantas();

//Agregar una planta más al edificio A
edificioA.agregarPlantasYPuertas(1, 2);

//Agregar un nuevo propietario al edificio A
resultados.innerHTML +=
	'</br><b>Agregar propietario a la planta 3 puerta 2 (Tras añadir nueva planta):</b></br></br>';

edificioA.agregarPropietario('Pedro Mejide', 3, 2);

//Mostrar el nuevo listado de propietarios del edificio A
resultados.innerHTML +=
	'</br><b>Listado de propietarios del edificio calle García Prieto número 58 (edificio A): </b></br></br>';

edificioA.imprimePlantas();
