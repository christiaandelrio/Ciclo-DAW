const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const velocidadInicial   = document.getElementById("velocidadInicial");
const aceleracion        = document.getElementById("aceleracion");
const tiempoTranscurrido = document.getElementById("tiempoTranscurrido");
const velocidad          = document.getElementById("velocidad");
const espacio            = document.getElementById("espacio");

botonEnviar.addEventListener('click', (evento) => {
    if ( velocidadInicial.value == '' || 
         aceleracion.value == ''      || 
         tiempoTranscurrido.value == '') {
      alert("Complete todos los parámetros, por favor");
    } else {
        const u = Number(velocidadInicial.value);
        const a = Number(aceleracion.value);
        const t = Number(tiempoTranscurrido.value);
        velocidad.textContent = "v = " + ( u + a*t) + " m/s";
        espacio.textContent   = "s = " + ( u *t + 0.5 * a * (t ** 2) ) + " m";
    }
} );

botonReset.addEventListener('click', limpiarEntradas);

function limpiarEntradas( ) {
    velocidadInicial.value = '';
    aceleracion.value = '';
    tiempoTranscurrido.value = '';
    velocidad.innerHTML = "&nbsp;";
    espacio.innerHTML = "&nbsp;";
    velocidadInicial.focus();
}
