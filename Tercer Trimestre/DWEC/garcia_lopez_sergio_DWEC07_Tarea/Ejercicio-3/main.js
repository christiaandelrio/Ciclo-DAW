//Utilizamos la función $.ajax() de jQuery para hacer la solicitud HTTP GET
$.ajax({
	url: 'https://feeds.bbci.co.uk/news/rss.xml',
	method: 'GET',
	dataType: 'xml', // Se especifica que el tipo de dato que se espera es XML
	// Se define una función callback que se ejecutará cuando la solicitud sea exitosa
	success: function (xml) {
		// Se utiliza la función find() de jQuery para obtener todos los elementos "item" del XML
		const items = $(xml).find('item');
		// Se crea un array vacío para almacenar las noticias
		const news = [];

		// Iterar sobre los elementos "item" del XML y crear un objeto JavaScript para cada noticia
		items.each(function () {
			const title = $(this).find('title').text();
			const description = $(this).find('description').text();
			const link = $(this).find('link').text();
			const pubDate = $(this).find('pubDate').text();
			const newsItem = { title, description, link, pubDate };
			news.push(newsItem);
		});

		//Dibujar las noticias en el DOM a partir del array de objetos (noticias) creado anteriormente
		const salida = $('#salida');
		news.forEach((item) => {
			salida.append(`
				<div class="new">
					<h2> ${item.title}</h2>
					<h4> ${item.description}</h4>
					<p><a href="${item.link}" target="__black">${item.link}</a></p>
					<p> ${item.pubDate}</p>
				</div>
			`);
		});
	},
	error: function (error) {
		console.log(error);
		salida.innerHTML = `<h2>Se ha producido un error</h2>`;
	},
});
