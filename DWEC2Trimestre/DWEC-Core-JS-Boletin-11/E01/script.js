const manipulador = document.getElementById('salida');
manipulador.textContent = '';

const instrumentos = [ new Guitarra(), new Timbal(), new Clarinete(), new Bajo(), new Trompeta()];

// Ejemplo 1 código polimórfico
for (const instrumento of instrumentos) {
    instrumento.lugarParaTocar(manipulador);
    instrumento.afinar();
    manipulador.textContent += '\n';
}

manipulador.textContent += '\n\n';

// Ejemplo 2 código polimórfico
for (const instrumento of instrumentos) {
    instrumento.tocarNota('Do3');
    manipulador.textContent += '\n';
}

manipulador.textContent += '\n\n';

// Ejemplo de qué pasa con métodos que no pertenecen a la clase base
// Si el código no es totalmente polimórfico, cada vez que
// añadamos un nuevo instrumento a nuestra jerarquía de clases
// tendremos que revisar toda nuestra base de código que tenga
// código como el siguiente, que tiene que revisar qué tipo 
// de objeto es para saber como procesarlo.
// NOTA: si en vez de haber creado 3 métodos distintos cambiarCuerdas,
//  limpiarTubo, ajustarTensores hubieramos creado un único método
//   mantenerInstrumento (por ejemplo) no tendríamos este problema
for (const instrumento of instrumentos) {
    if (instrumento instanceof Cuerda) {
        instrumento.cambiarCuerdas();
    }

    if (instrumento instanceof Viento) {
        instrumento.limpiarTubo();
    }

    if (instrumento instanceof Percusion) {
        instrumento.ajustarTensores();
    }

    manipulador.textContent += '\n';
}

manipulador.textContent += '\n\n';


// Ejemplo de qué pasa con métodos que no pertenecen a la clase base
// NOTA: IDEM que la anterior nota
for (const instrumento of instrumentos) {
    switch(instrumento.constructor.name) {
        case 'Viento': 
        case 'Clarinete': 
        case 'Trompeta': instrumento.limpiarTubo(); break;
        case 'Cuerda':
        case 'Guitarra': 
        case 'Bajo': instrumento.cambiarCuerdas(); break;
        case 'Percusion':
        case 'Timbal': instrumento.ajustarTensores();break;
        default: throw new Error("Tipo de Instrumento no reconocido");
    }
    manipulador.textContent += '\n';
}


manipulador.textContent += '\n\n';

manipulador.textContent += "Total instrumentos: " + Instrumento.totalInstrumentos() + "\n";
manipulador.textContent += "Total instrumentos Viento: " + Viento.totalInstrumentosViento() + "\n";
manipulador.textContent += "Total instrumentos Cuerda: " + Cuerda.totalInstrumentosCuerda() + "\n";
manipulador.textContent += "Total instrumentos Percusion: " + Percusion.totalInstrumentosPercusion() + "\n";

