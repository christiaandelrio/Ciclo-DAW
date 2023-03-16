// debugger
const botonEnviar          = document.getElementById("botonEnviar");
const botonReset           = document.getElementById("botonReset");
const cajaMensaje          = document.getElementById('mensaje');
const cajaCodigo           = document.getElementById('codigo');   
const parrafoCodificado    = document.getElementById('mensajeCodificado');
const cajaCadenaReferencia = document.getElementById('cadenaReferencia');   

cajaCodigo.value     = 'fkdjfklhgosidfowheoihfosfdlkfjlkdfjoiwejf2343sd987238479lkdjflk4';
let cadenaReferencia = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789';
cajaCadenaReferencia.value = cadenaReferencia;

cajaCodigo.style.fontFamily           = 'monospace';
cajaMensaje.style.fontFamily          = 'monospace';
cajaCadenaReferencia.style.fontFamily = 'monospace';

botonEnviar.addEventListener('click', procesarMensaje);
botonReset.addEventListener('click', borrarFormulario);

function procesarMensaje () {
    if ( cajaCodigo.value.length != cadenaReferencia.length) {
        alert("El código y la Refer. deben tener la mísma longitud");
        return;
    }    

    parrafoCodificado.textContent = codificarMensaje();
}

function codificarMensaje () {
    let mensaje  = cajaMensaje.value;
    let cifrado  = mensaje;
    let codigo   = cajaCodigo.value;
    let posicion = 0;

    for(let i = 0; i < mensaje.length; i++ ) {
        posicion = cadenaReferencia.lastIndexOf( mensaje[i] );   // buscamos la posición del carácter de codificación 

        if ( posicion == -1 ) {  // -1 = caracter no está en cadenaReferencia
            return "Caracter en mensaje no soportado: '" + mensaje[i] + "' : PROCESO ABORTADO";
        } else if ( codigo[posicion] === '-' ) {
            ;
        } else if ( codigo[posicion] === '.' ) {
            // cadena antes del carácter a borrar = cifrado.slice(0, i);
            // cadena después del carácter a borrar = cifrado.slice(i+1, cifrado.length);
            // el caracter "^" queda "reservado" para indicar borrado posterior
            cifrado = cifrado.slice(0,i) + "^" + cifrado.slice(i+1, cifrado.length);
        } else {      
            // ver comentarios encima...mismo razonamiento
            cifrado = cifrado.slice(0, i) + codigo[posicion] +  cifrado.slice(i+1, cifrado.length);
        }
    }

    cifrado = cifrado.replaceAll('^', '');  // Eliminamos los caracteres codificados con "."
    return cifrado;   
}


function borrarFormulario() {
    cajaMensaje.value = '';
    cajaCodigo.value = 'fkdjfklhgosidfowheoihfosfdlkfjlkdfjoiwejf2343sd987238479lkdjflk4';
}