// En esta función, validamos una fecha ingresada en el HTML
function validarFecha() {
    // 1) Obtenemos el valor del campo de entrada con ID 'title'
    let fecha = document.getElementById('title').value;
    // 2) Si la longitud de la variable fecha es 0, mostramos alerta y retornamos false
    if (fecha.length == 0) {
        alert("Elija una fecha.");
        return false
    }
    // 3) Instanciamos los objetos 'Date' utilizando el valor de la variable 'fecha', y la fecha actual. Para realizar comparaciones
    let miFecha = new Date(fecha);
    let hoy = new Date();

    // 4) Establecemos las fechas a cero, para comparar solo las fechas sin tener en cuenta la parte de tiempo
    hoy.setHours(0, 0, 0, 0);
    miFecha.setHours(0, 0, 0, 0);

    // 5) Si mi fecha es anterior a hoy, mostramos una alerta y retornamos false
    if (miFecha < hoy) {
        alert("La fecha no puede ser inferior a la actual");
        return false;
    }

    // 6) Si todas las validaciones son superadas, se retorna 'true' indicando que la fecha es válida
    return true;
}

function getCoordenadas() {
    // Obtenemos el ID del formulario para obtener su valor y pasárselo a la función
    let dir = document.getElementById('dir').value;

    // Llamamos a la función definida en php mediante Jaxon
    jaxon_getCoordenadas(dir);
    return true;
}

// Esta función se encarga de ordenar los envíos
function ordenarEnvios(id) {
    // Comprobamos si ya están los datos de la lista ordenada en la página; si es así los mostramos (ON) u ocultamos (OFF)
    const idListaOrdenada = "#lista_" + id;
    // Se comprueba si el elemento seleccionado tiene un formulario como hijo, con el método 'has()' de jQuery y comprobando la longitud
    if ( $(idListaOrdenada).has("form").length ) { 
        // Si hay formulario presente, con el método 'toggle()' se muestra u oculta el elemento seleccionado 
        $(idListaOrdenada).toggle();
        return true;
    }

    // Con 'map()' iteramos sobre los elementos seleccionados y obtenemos sus valores
    // Devolvemos los valores obtenidos en un array con 'get()' y los concatenamos en una cadena separada por "|"
    var puntos = $("#t_" + id + " input:hidden").map(function () {
        return this.value;
    }).get().join("|");
    // Llamamos a la funcion definida en php mediante Jaxon, y le pasamos los argumentos
    jaxon_ordenarEnvios(puntos, id);
}

// Esta es la función a la que llamamos mediante 'call()' en la función de 'ordenarEnvios()' de Tools.php
// Además de llamarla, le pasabamos un array definido en la variable $datosRespuesta
function obtuvimosDatos(datosRespuesta) {
    // Si el valor de 'respuesta' es 404, mostramos una alerta
    if (datosRespuesta['respuesta'] == "404" ) {
        alert("Servicio para ordenar Rutas de Bing Maps no disponible temporalmente");
        return datosRespuesta['respuesta'];
    }

    // Si obtuvimos una respuesta, reordenamos los envíos del reparto
    // Cogemos la URL base del documento, quitando los parámetros GET si los hay
    let url = "http://localhost/TAREA_DWES_UD08/public/repartosAux.php";

    // Añadimos el código de la lista de reparto y el valor del atributo 'id' en 'datosRespuesta'
    url += '?idLt=' + datosRespuesta['id'];
    // url += '?action=oEnvios&idLt=' + datosRespuesta['id'];
    // Creamos una constante y le asignamos el valor de 'respuesta' en datosRespuesta
    const respuesta =  datosRespuesta['respuesta'];
  
    // Y un array con las nuevas posiciones que deben ocupar los envíos
    // En cada iteración le concatenamos a la URL el parámetro 'pos[]' seguido del valor correspondiente en 'respuesta'
    for (let i=0; i < respuesta.length; i++) {
        url += '&pos[]=' + respuesta[i];
    } 
    
    
    //window.location = url;
    
    const idListaOrdenada = "#lista_" + datosRespuesta['id'];
    // Finalmente, redirigimos la página actual, a la URL construida
    $(idListaOrdenada).load(url);
}

// Con esta función realizamos validaciones
function semaforo() {
    // Obtenemos los valores de los elementos con los 'id' detallados
    var latitud = document.getElementById('lat').value
    var pro = document.getElementById("pro").value

    // Si la longitud de latitud es 0, o si el value del select de productos es -1 (no se seleccionó), mostramos alerta y retornamos false
    if (latitud.length == 0 || pro == -1) {
        // alert("Elija un producto.");
        return false;
    }
    // Si valida, retornamos true
    return true;
}
