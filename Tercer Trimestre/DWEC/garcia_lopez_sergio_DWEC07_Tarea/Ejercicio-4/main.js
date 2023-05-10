const salida = document.querySelector('#salida');

const fetchAndShowData = () => {
	try {
		//Mostramos el loader mientras no se cargan los datos
		salida.innerHTML = '<h3 class="loader">Cargando...</h3>';

		//Hacemos la petición con jQuery
		$.ajax({
			url: 'https://rickandmortyapi.com/api/character',
			method: 'GET',
		})
			.done(function (data) {
				//Si la petición se ha completado y la respuesta es correcta mostramos los datos
				const characters = data.results;

				salida.innerHTML = '';

				//Si hay datos los mostramos
				characters.forEach((character) => {
					salida.innerHTML += `
				<article class="card">
					<img src="${character.image}" alt="name">
					<h4>${character.name}</h4>
				</article>
				`;
				});
			})
			.fail(function () {
				//Si la petición no se ha completado o la respuesta no es correcta mostramos un error
				salida.innerHTML = `
					<div class="error">
						<img class="error-img" src="./morty-triste.jpg" alt="Morty triste"/>
						<h3>Error al cargar la API</h3>
					</div>
				`;
			});
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

$(document).ready(function () {
	fetchAndShowData();
});
