// 1) Primero definimos las variables y le asignamos a cada una la referencia a los elementos HTML
let numeroInput = document.getElementById("numero");
let botonNumero = document.getElementById("enviar");
let mensajeError = document.getElementById("mensajeError");
let colorFondo = document.getElementById("colorFondo");
let cuadrados = document.getElementById("cuadrados");
let urlInput = document.getElementById("urlInput");
let botonJSON = document.getElementById("botonJSON");
let salidaJSON = document.getElementById("salidaJSON");

// 2) Este evento se dispara cuando hacemos clic en el botón de enviar
botonNumero.addEventListener("click", function() {
    // 3) Obtenemos el valor ingresado por el usuario
    let numero = parseInt(numeroInput.value);
    // 4) Verificamos que el número está dentro del rango válido
    if (numero >= 1 && numero <= 50) {
        // 5) Si el número es válido, el contenedor de mensajes de error lo dejamos vacío
        mensajeError.textContent = "";
        // 6) Mostramos un mensaje de confirmación antes de dibujar los cuadrados
        if(confirm("Estás seguro que deseas enviar la entrada anterior?")){
            // 7) Si el usuario confirma, llamamos a la función de dibujar cuadrados y le pasamos el número introducido
            dibujarCuadrados(numero);
            // 8) Guardamos el valor en el almacenamiento local para que al reabrir la aplicación, se mantengan los cuadrados
            guardarValorLocalStorage(numero);
        }
    } else {
        // 9) Si no se superó la validación, se muestra un mensaje de error en su contenedor correspondiente
        mensajeError.textContent = "El número debe estar comprendido entre 1 y 50, ambos incluídos";
    }
});

// 10) Este evento se dispara al hacer clic en el botón de cambiar color de fondo
colorFondo.addEventListener("click", function() {
    // 11) Llamamos a la función de generar color aleatorio (la definimos después) y asignamos el valor devuelto a la propiedad backgroundColor
    document.body.style.backgroundColor = generarColorAleatorio();
});

// 12) Este evento se dispara cuando hacemos clic en el botón de descargarnos el documento con notación JSON
botonJSON.addEventListener("click", function() {
    // 13) Obtenemos la URL ingresada en su campo correspondiente
    let url = urlInput.value;
    // 14) Creamos el objeto para hacer la petición AJAX
    let peticion = new XMLHttpRequest();
    // 15) Configuramos la solicitud GET con la URL especificada
    peticion.open("GET", url);
    // 16) Indicamos que se espera una respuesta JSON
    peticion.responseType = "json";

    // 17) Con el evento 'onload' manejamos la respuesta de la solicitud
    peticion.onload = function() {
        // 18) Si la solicitud se completa correctamente, mostramos el JSON en el area de texto en letra color negro
        if(peticion.status = 200) {
            salidaJSON.value = JSON.stringify(peticion.response, null, 2);
            salidaJSON.style.color = "black";
        } else {
            // 19) Si hay un error, lo mostramos en el area de texto con letra roja
            salidaJSON.value = "Error al descargar el JSON" + peticion.status;
            salidaJSON.style.color = "red";
        }
    };
    // 20) Si hay error de conexión, lo mostramos también en el área de texto con letra roja
    peticion.onerror = function() {
        salidaJSON.value = "Error de conexión";
        salidaJSON.style.color = "red";
    };

    // 21) Envío de la petición
    peticion.send();

});

// 22) Este evento se dispara cuando la página se ha cargado por completo
window.addEventListener("load", function() {
    // 23) Verificamos si hay un valor guardado en el almacenamiento local
    let valorGuardado = obtenerValorLocalStorage();
    // 24) Si hay valor guardado, lo asignamos al campo 'numero' (en donde introducimos el numero para mostrar cuadrados)
    if(valorGuardado) {
        numero.value = valorGuardado;
        // 25) Creamos los cuadrados
        dibujarCuadrados(parseInt(valorGuardado));
    }
});

// 26) Función para dibujar los cuadrados que recibe como parámetro un número
function dibujarCuadrados(n) {
    // 27) Primero vaciamos el contenido del elemento cuadrados
    cuadrados.innerHTML = "";
    // 28) Bucle for que itera 'n' veces
    for (let i = 0; i < n; i++) {
        // 29) En cada iteración, se crea un nuevo elemento 'div'
        let cuadrado = document.createElement("div");
        // 30) A los elementos div creados, se le añade la clase 'cuadrado'
        cuadrado.classList.add("cuadrado");
        // 31) Asignamos propiedades aleatorias como color, posicion (top, left) y tamaño (width, height)
        cuadrado.style.backgroundColor = generarColorAleatorio();
        cuadrado.style.top = generarPosicionAleatoria();
        cuadrado.style.left = generarPosicionAleatoria();
        cuadrado.style.width = generarTamanoAleatorio();
        cuadrado.style.height = generarTamanoAleatorio();
        // 32) Agregamos la variable 'cuadrado' al contenedor del HTML
        cuadrados.appendChild(cuadrado);

    }
}

function generarColorAleatorio() {
    // 33) Generamos número aleatorio, multiplicamos por 16777215 para obtener un nº en el rango de colores hexadecimales, y lo convertimos a cadena
    // hexadecimal. Devolvemos el color generado con un signo "#" al principio
    return "#" + Math.floor(Math.random() * 16777215).toString(16);
}

function generarPosicionAleatoria() {
    // 34) Generamos nº aleatorio, lo multiplicamos por 400 para obtener un nº en el rango de posiciones posibles, y lo convertimos a cadena con "px"
    return Math.floor(Math.random() * 400) + "px";
}

function generarTamanoAleatorio() {
    // 35) Generamos nº aleatorio, multiplicamos por 150 pa obtener nº en el rango de tamaños posibles, y lo convertimos a cadena con "px"
    return Math.floor(Math.random() * 150) + "px";
}

function guardarValorLocalStorage(valor) {
    // 36) Guardamos un valor en el almacenamiento local con una clave específica "numeroGuardado" y el valor como parámetro
    localStorage.setItem("numeroGuardado", valor);
}

function obtenerValorLocalStorage() {
    // 37) Obtenemos el valor almacenado utilizando la clave "numeroGuardado". Retornamos el valor recuperado
    return localStorage.getItem("numeroGuardado");
}