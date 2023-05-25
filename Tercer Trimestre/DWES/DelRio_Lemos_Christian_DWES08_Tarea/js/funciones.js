function validarFecha() {
    let fecha = document.getElementById('title').value;

    if (fecha.length == 0) { //Si no se ha escogido una fecha, pide que se introduzca una
        alert("Elija una fecha.");
        return false
    }

    let miFecha = new Date(fecha); //Crea un nuevo objeto de tipo fecha
    let hoy     = new Date();

    hoy.setHours(0, 0, 0, 0);
    miFecha.setHours(0, 0, 0, 0);

    if (miFecha < hoy) { //Si la fecha escogida es inferior a la actual mostrará un alert
        alert("La fecha no puede se inferior a la actual");
        return false;
    }

    return true;
}

function getCoordenadas() {
    let dir = document.getElementById('dir').value;

    jaxon_getCoordenadas(dir); //Invoca a la función de jaxon get coordenadas 
    return true;
}


// function ordenarEnvios(id) {
//     // ¿Ya fue ordenado este elemento y está oculto?
//     const idFormulario = '#forden_' + id;
//     if (document.getElementById(idFormulario) == !null /* COMPLETAR comprobarQueYaestacreado */ ) {  // ya existe: lo mostramos
//         if ($('#forden_'+id).is(':hidden')/* COMPLETAR comprobarSiEstaOculto */ ) {
//             $(idFormulario).show();
//         } else {
//             $(idFormulario).hide();
//         }  
  
//         return true;
//     }
   

//     var puntos = $("#t_" + id + " input:hidden").map(function () {
//         return this.value;
//     }).get().join("|");

//     jaxon_ordenarEnvios(puntos, id);
// }
function ordenarEnvios(id) {
    // Comprobamos si ya están los datos de la lista ordenada en la página; si es así los mostramos (ON) u ocultamos (OFF)
    const idListaOrdenada = "#lista_" + id;
    if ( $(idListaOrdenada).has("form").length ) {  // si existe, es que ya hay la lista
        $(idListaOrdenada).toggle(); //Hacemos uso de una función toggle que se encargue de alternar entre ocultar o mostrar la lista ordenada
        return true;
    }

    // Si no están, los generamos usando la API REST, invocanda a través de Jaxon
    var puntos = $("#t_" + id + " input:hidden").map(function () {
        return this.value;
    }).get().join("|");

    jaxon_ordenarEnvios(puntos, id);
}

function obtuvimosDatos(datosRespuesta) {
    if (datosRespuesta['respuesta'] == "404" ) {
        alert("Servicio para ordenar Rutas de Bing Maps no disponible temporalmente");
        return datosRespuesta['respuesta'];
    }

    // Si obtuvimos una respuesta, reordenamos los envíos del reparto
    // Cogemos la URL base del documento, quitando los parámetros GET si los hay
    let url = "http://localhost/dwes_tema_08/TAREA_08/public/repartosAux.php";

    // Añadimos el código de la lista de reparto
    // url += '?action=oEnvios&idLt=' + datosRespuesta['id'];
    url += '?idLt=' + datosRespuesta['id'];
    const respuesta =  datosRespuesta['respuesta'];
  
    // Y un array con las nuevas posiciones que deben ocupar los envíos
    //for (var r of datosRespuesta['respuesta']) url += '&pos[]=' + respuesta[r];
    for (let i=0; i < respuesta.length; i++) url += '&pos[]=' + respuesta[i];
    
    // [CODIGO NUEVO]
    const idListaOrdenada = "#lista_" + datosRespuesta['id'];
    $(idListaOrdenada).load(url);
}


function semaforo() {
    let latitud = document.getElementById('lat').value
    let pro     = document.getElementById("pro").value

    if (latitud.length == 0 || pro == -1) {
        //alert("Elija un producto.");
        return false;
    }

    return true;
}
