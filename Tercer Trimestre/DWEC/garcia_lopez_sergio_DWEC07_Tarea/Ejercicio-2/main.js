const salida = document.querySelector('#salida');

const fetchAndShowData = () => {
	try {
		//Instanciamos el objeto XMLHttpRequest
		const xhttp = new XMLHttpRequest();
		//Creamos la petición
		xhttp.open('GET', 'https://rickandmortyapi.com/api/character', true);
		//Enviamos la petición
		xhttp.send();

		//Mostramos el loader mientras no se cargan los datos
		salida.innerHTML = '<h3 class="loader">Cargando...</h3>';

		//Recibimos los datos
		xhttp.onreadystatechange = function () {
			//Si la petición se ha completado y la respuesta es correcta mostramos los datos
			if (this.readyState == 4 && this.status == 200) {
				let datos = JSON.parse(this.responseText);
				// console.log(datos.results);
				const characters = datos.results;

				//Limpiamos las salidas
				salida.innerHTML = '';

				characters.forEach((character) => {
					salida.innerHTML += `
					<article class="card">
						<img src="${character.image}" alt="name">
						<h4>${character.name}</h4>
					</article>
					`;
				});
			} else {
				//Si la petición no se ha completado o la respuesta no es correcta mostramos un error
				salida.innerHTML = `
				<div class="error">
					<img class="error-img" src="./morty-triste.jpg" alt="Morty triste"/>
					<h3>Error al cargar la API</h3>
				</div>`;
			}
		};
	} catch (error) {
		//Si se produce un error mostramos un mensaje
		console.log('Se ha producido un error' + error);
		salida.innerHTML = `
		<div class="error">
			<img class="error-img" src="./morty-triste.jpg" alt="Morty triste"/>
			<h3>Se ha producido un error.</h3>
			<p>(Compruebe la resolución de su espacio-tiempo)</p>
		</div>
		`;
	}
};

//Llamamos a la función al cargar la página
window.addEventListener('load', function () {
	fetchAndShowData();
});
