function getTiempo() {
    // Obtenemos los IDs del formulario para obtener sus valores y pasárselos a la función
    var la = document.getElementById('lat').value;
    var lo = document.getElementById('lon').value;

    // Llamamos a la función definida en php
    jaxon_getTiempo(la, lo);
}

function getLocalizacion() {
    // Lo mismo que antes
    var la = document.getElementById('lat').value;
    var lo = document.getElementById('lon').value;
    
    // Llamamos a la función definida en php
    jaxon_getLocalizacion(la, lo);
}