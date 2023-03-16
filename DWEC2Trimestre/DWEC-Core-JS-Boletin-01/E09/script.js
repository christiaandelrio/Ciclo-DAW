const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const campoNumero = document.querySelector("#numero");
const parrafoSalida = document.querySelector("#salida");


botonEnviar.addEventListener('click', () => {
        let numero = campoNumero.value;

        if ( numero >= 0) {
            digito1 = numero % 10;
            numero = Math.floor(numero / 10);
            digito2 = numero % 10;
            numero = Math.floor(numero / 10);
            digito3 = numero % 10;
            numero = Math.floor(numero / 10);
            digito4 = numero % 10;
            numero = Math.floor(numero / 10);
            digito5 = numero % 10;
        
            numero = Math.floor(numero / 10);

            if ( numero !== 0) {
                alert("El número debe tener exactamente 5 dígitos (o menos)");
            } else {
                parrafoSalida.textContent = `${digito5}    ${digito4}    ${digito3}    ${digito2}    ${digito1}`;
            }
        } else {
            alert("El número debe ser positivo");
        }
} );

botonReset.addEventListener('click', limpiarEntradas);

function limpiarEntradas( ) {
    salida.innerHTML = '&nbsp;';
    campoNumero.focus();
}
