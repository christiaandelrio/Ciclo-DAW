const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const suma        = document.getElementById("suma");
const resta       = document.getElementById("resta");
const producto    = document.getElementById("producto");
const resto       = document.getElementById("resto");
const numero1     = document.getElementById("numero1");
const numero2     = document.getElementById("numero2");

botonEnviar.addEventListener('click', (evento) => {
    if ( numero1.value == '' || numero2.value == '') {
      alert("Complete ambos números, por favor");
    } else {
        const num1 = Number(numero1.value);
        const num2 = Number(numero2.value);
        suma.textContent = "La suma es: " + ( num1 + num2);
        resta.textContent = "La resta es: " + ( num1 - num2);
        producto.textContent = "El producto es: " + ( num1 * num2);
        resto.textContent = "El resto es: " + ( num1 % num2);
    }
} );

botonReset.addEventListener('click', limpiarEntradas);

function limpiarEntradas( ) {
    suma.innerHTML = '&nbsp;';
    resta.innerHTML = '&nbsp;';
    producto.innerHTML = '&nbsp;';
    resto.innerHTML = '&nbsp;';
    numero1.value = '';
    numero2.value = '';
    numero1.focus();
}
