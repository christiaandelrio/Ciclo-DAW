window.onload = function () {
	try {
		//Abrir y cerrar ventana
		document.getElementById('open-window').onclick = createNewWindow;
		document.getElementById('close-window').onclick = closeWindow;
		let newWindow = null;

		function createNewWindow() {
			newWindow = window.open(
				'./window.html',
				'newWindow',
				'height=400,width=800'
			);
		}

		function closeWindow() {
			if (newWindow) {
				newWindow.close();
				newWindow = null;
			}
		}

		//Formulario para introducir los datos
		document.getElementById('main-texts').innerHTML = `
		<h1 class="my-4">TAREA DWEC03</H2><HR />
		<form id="form">
			<p>
				<input type="text"name="name-surname" id="name-surname" placeholder="Nombre y apellidos" value="Antonia"/>
				<lable for="name-surname" >Nombre y apellidos</lable>
			</p>
			<p>
				<input type="number"name="birth-day" id="birth-day" placeholder="Día de nacimiento" value="1"/>
				<lable for="birth-day" >Día de nacimiento</lable>
			</p>
			<p>
				<input type="number"name="birth-month" id="birth-month" placeholder="Mes de nacimiento" value="2"/>
				<lable for="birth-month" >Mes de nacimiento</lable>
			</p>
			<p>
				<input type="number"name="birth-year" id="birth-year" placeholder="Año de nacimiento" value="1900"/>
				<lable for="birth-year" >Año de nacimiento</lable>
			</p>
			<input type="submit" class="btn btn-primary" value="Enviar" />
		</form>
		<br />
		`;

		//Función para calcular la edad a partir de la fecha de nacimiento
		function calcAge(day, month, year) {
			let today = new Date();
			let birthDate = new Date(year, month, day);
			let age = today.getFullYear() - birthDate.getFullYear();
			let m = today.getMonth() - birthDate.getMonth();
			if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
				age--;
			}
			return age;
		}

		document.getElementById('form').onsubmit = function (e) {
			e.preventDefault();

			//Captura de los datos del formulario
			let name = document.getElementById('name-surname').value;
			let day = Number(document.getElementById('birth-day').value);
			let month = Number(document.getElementById('birth-month').value);
			let year = Number(document.getElementById('birth-year').value);

			//Salida de los resultados
			document.getElementById('show-texts').innerHTML = `
					<p>Buenos días	${name} </p>
					<p>Tu nombre tiene ${name.length} caracteres incluidos espacios.</p>
					<p>La primera letra A de tu nombre está en la posición: </p>
					<p>La primera letra A de tu nombre está en la posición: ${name
						.toLowerCase()
						.indexOf('a')}</p>
					<p>La última letra A de tu nombre está en la posición: ${name
						.toLowerCase()
						.lastIndexOf('a')}</p>
					<p>Tu nombre menos las 3 primeras letras es: ${name.substring(0, 3)}</p>
					<p>Tu nombre todo en mayúsculas es: ${name.toUpperCase()}</p>
					<p>Tu edad es: ${calcAge(day, month, year)} años.</p>
					<p>Naciste un feliz ${day} del año ${year}.</p>
					<p>El coseno de 180 es: ${Math.cos((180 * Math.PI) / 180)}</p> 
					<p>El número mayor de (34, 67, 23, 75, 35, 19) es: ${Math.max(
						34,
						67,
						23,
						75,
						35,
						19
					)}</p>
					<p>Ejemplo de número al azar: ${Math.round(Math.random() * 1000)}</p>
				`;
		};
	} catch (error) {
		console.log(error);
	}
};
