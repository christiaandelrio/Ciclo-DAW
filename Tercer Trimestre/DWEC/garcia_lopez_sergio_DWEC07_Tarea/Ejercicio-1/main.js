const salida = document.querySelector('#salida');

const url = 'http://restapi.adequateshop.com/api/Traveler?page=1';

const fetchData = async (url) => {
	try {
		//Mostramos el loader
		salida.innerHTML = '<h2>Cargando...</h2>';

		//Creamos una instsancia de la clase XMLHttpRequest
		const xhttp = new XMLHttpRequest();
		//Creamos nuestra consulta
		xhttp.open('GET', url, true);
		//Enviamos la consulta
		xhttp.send();
		//Obtenemos la respuesta
		xhttp.onreadystatechange = function () {
			//Limpiamos el contenido
			salida.innerHTML = '';

			//Si el estado de la petición es 4 (completada) y el estatus es 200 (correcto) dibujamos los datos
			if (this.readyState == 4 && this.status == 200) {
				//Obentemos todos los datos que tienen la etiqueta XML Travelerinformation
				let travelers =
					xhttp.responseXML.documentElement.getElementsByTagName(
						'Travelerinformation'
					);

				//Recorremos los datos y los dibujamos en el DOM
				for (let i = 0; i < travelers.length; i++) {
					let traveler = travelers[i];
					console.log(traveler);

					salida.innerHTML += `
						<div class="card">
						<img src="./user-img.webp"/>
						<ul>
							<li><b>Id</b>: ${traveler.getElementsByTagName('id')[0].textContent}</li>
							<li><b>Name</b>: ${traveler.getElementsByTagName('name')[0].textContent}</li>
							<li><b>Email</b>: ${traveler.getElementsByTagName('email')[0].textContent}</li>
							<li><b>Adderes</b>: ${
								traveler.getElementsByTagName('adderes')[0]
									.textContent
							}</li>
						</ul>
						</div>
					`;
				}
			} else {
				salida.innerHTML = '<h3>Error al obtener los datos</h3>';
			}
		};
	} catch (error) {
		console.log(error);
	}
};

window.addEventListener('load', function () {
	fetchData(url);
});
