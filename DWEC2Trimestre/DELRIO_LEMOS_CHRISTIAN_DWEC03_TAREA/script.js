//Definición de variables
const botonAbrir = document.getElementById('abrirVentana');
const botonCerrar = document.getElementById('cerrarVentana');
const formulario = document.getElementById('formulario');
const botonEnviar = document.getElementById('enviar');
const resultados = document.getElementById('resultados');

// Abrir ventana //
function abrirVentana() {
	const nuevaVentana = window.open(
		'ventana.html',
		'nueva',
		'width=800,height=400,resizable=false'
	);
}

botonAbrir.addEventListener('click', () => {
	nuevaVentana = abrirVentana();
});
// Abrir ventana //

function calcularEdad(dia, mes, ano) {
	//fechaHoy = new Date().toLocaleDateString();
	//fechaNacimiento = (new Date(dia,mes-1,ano)).toLocaleDateString();

	const anoActual = Number(new Date().getFullYear());
	const mesActual = Number(new Date().getMonth()) + 1;
	const diaActual = Number(new Date().getDate());

	let edad = anoActual - Number(ano);

	if (mesActual < mes || (mesActual == mes && diaActual < dia)) {
		edad = edad - 1;
	}

	return edad;
}

botonEnviar.addEventListener('click', () => {
	let nombre = document.getElementById('nombre').value;
	let apellidos = document.getElementById('apellidos').value;
	let dia = document.getElementById('dia').value;
	let mes = document.getElementById('mes').value;
	let ano = document.getElementById('ano').value;

	resultados.innerHTML = `
    <p>Buenos días ${nombre} ${apellidos}</p>
    <p>Tu nombre tiene ${nombre.length} caracteres </p>
    <p>La posición de la primera A de tu nombre es ${
		nombre.toLowerCase().indexOf('a') + 1
	} </p>

    <p>La última letra A de tu nombre está en la posición:${
		nombre.toLowerCase().lastIndexOf('a') + 1
	}</p>
    <p>Tu nombre menos las 3 primeras letras es: ${nombre.substring(3)}</p>
    <p>Tu nombre todo en mayúsculas es: ${nombre.toUpperCase()} </p>
    <p>Tu edad es: ${calcularEdad(dia, mes, ano)} años</p>
    <p>Naciste un feliz ${dia} del año ${ano}.</p>
    <p>El coseno de 180 es: ${Math.cos((180 * Math.PI) / 180)}</p>
    <p>El número mayor de (34,67,23,75,35,19) es: ${Math.max(
		34,
		67,
		23,
		75,
		35,
		19
	)}</p>
    <p>Ejemplo de número al azar:${Math.floor(Math.random() * 100) + 1}</p>
    `;
});