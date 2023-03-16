const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const numero1   = document.querySelector("#numero1");
const numero2   = document.querySelector("#numero2");
const numero3   = document.querySelector("#numero3");
const media    = document.querySelector("#media");
const producto = document.querySelector("#producto");
const mayor    = document.querySelector("#mayor");
const suma     = document.querySelector("#suma");
const menor    = document.querySelector("#menor");

botonEnviar.addEventListener('click', (evento) => {
    if ( numero1.value == '' || 
         numero2.value == ''      || 
         numero3.value == '') {
      alert("Introduzca los 3 enteros, por favor");
    } else {
        const n1 = Number(numero1.value);
        const n2 = Number(numero2.value);
        const n3 = Number(numero3.value);

        entrada.textContent = `Número1 = ${n1}  Número2 = ${n2}  Número3 = ${n3}`;
        suma.textContent = `Suma = ${n1 + n2 + n3}`;
        producto.textContent = `Producto = ${n1 * n2 * n3}`;
        media.textContent = `Media = ${(n1 + n2 + n3) / 3}`;
        
        let max = n1;   
        if ( n1 < n2 ) {
            max = n2;
            if ( n2 < n3) {
                max = n3;
            }
        } else if ( n1 < n3) {
            max = n3;
        }
        mayor.textContent = `Mayor = ${max}`;
        
        let min = n1; 
        if ( n2 < n1 ) {
            min = n2;
            if ( n3 < n2) {
                min = n3;
            }
        } else if ( n3 < n1) {
            min = n3;
        }
        menor.textContent =  `Menor = ${min}`;
    }
} );

botonReset.addEventListener('click', limpiarEntradas);

function limpiarEntradas( ) {
    entrada.innerHTML = '&nbsp;';
    suma.innerHTML = '&nbsp;';
    producto.innerHTML = '&nbsp;';
    media.innerHTML = '&nbsp;';
    mayor.innerHTML = '&nbsp;';
    menor.innerHTML = '&nbsp;';
    numero1.focus();
}
