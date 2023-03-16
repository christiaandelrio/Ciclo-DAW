const cajasTexto = document.querySelectorAll('textarea');  // ejemplo de recoger todas las textareas en un "array" (nodelist)
const ancho = 10;                                          // ancho máximo triangulo
for (const cajaTexto of cajasTexto)  {                     // estas cuatro líneas del ancho no están incluídas en los
    cajaTexto.setAttribute('rows', ancho + 1);             //  requisitos del enunciado, pero las pongo para mostrar la utilidad de 
    cajaTexto.setAttribute('cols', ancho + 1);             // usar una constante para "parametrizar" todo el código:
}                                                          // con cambiar el valor de la constante cambia todo los demás correctamente

// Figura: (A)
cajasTexto[0].textContent = '';
for (let fila = 1; fila <= ancho; fila++) {
    for (let columna = 1; columna <= fila; columna++) {
        cajasTexto[0].textContent += '*';
    }
    cajasTexto[0].textContent +=  '\n';
}

// Figura: (B)
cajasTexto[1].textContent = '';
for (let fila = 1; fila <= ancho; fila++) {
    for (let columna = 1; columna <= ancho - fila + 1; columna++) {
        cajasTexto[1].textContent += '*';
    }
    cajasTexto[1].textContent +=  '\n';
}

// Figura: (C)
cajasTexto[2].textContent = '';
for (let fila = 1; fila <= ancho; fila++) {
    for (let columna = 1; columna <= ancho; columna++) {
        if ( columna < fila ) {
            cajasTexto[2].textContent += ' ';
        } else {
            cajasTexto[2].textContent += '*';
        }
    }
    cajasTexto[2].textContent +=  '\n';
}

// Figura: (D)
cajasTexto[3].textContent = '';
for (let fila = 1; fila <= ancho; fila++) {
    for (let columna = 1; columna <= ancho; columna++) {
        if ( columna <= fila ) {
            cajasTexto[3].textContent += '*';
        } else {
            cajasTexto[3].textContent += ' ';
        }
    }
    cajasTexto[3].textContent +=  '\n';
}