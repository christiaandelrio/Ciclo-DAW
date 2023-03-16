class Instrumento {
    static #contador = 0;

    manipulador;

    static totalInstrumentos() {
        return Instrumento.#contador;
    }

    afinar() {
        this.manipulador.textContent += "Afinando Instrumento ...";
    }

    tocarNota(nota) {
        this.manipulador.textContent += "Instrumento tocando " + nota;
    }

    lugarParaTocar(manipulador) {
        this.manipulador = manipulador;
    }

    constructor() {
        Instrumento.#contador++;
    }
}

class Cuerda extends Instrumento {
    static #contadorCuerda = 0;

    identidad;
   
    static totalInstrumentosCuerda() {
        return Cuerda.#contadorCuerda;
    }

    afinar() {
        this.manipulador.textContent += "Afinando instrumento Cuerda...";
    }

    tocarNota(nota) {
        this.manipulador.textContent += "Instrumento Cuerda tocando " + nota;
    }

    cambiarCuerdas() {
        this.manipulador.textContent += "Instrumento Cuerda cambiando cuerdas... ";

    }

    constructor() {
        super();
        Cuerda.#contadorCuerda++;
        this.identidad = Cuerda.#contadorCuerda;
    }
}

class Viento extends Instrumento {
    static #contadorViento = 0;

    identidad;    

    static totalInstrumentosViento() {
        return Viento.#contadorViento;
    }
    
    afinar() {
        this.manipulador.textContent += "Afinando instrumento Viento...";
    }

    tocarNota(nota) {
        this.manipulador.textContent += "Instrumento Viento tocando " + nota;
    }

    limpiarTubo() {
        this.manipulador.textContent += "Intrumento Viento limpiando tubo... ";
    }

    constructor() {
        super();
        Viento.#contadorViento++;
        this.identidad = Viento.#contadorViento;        
    }
}

class Percusion extends Instrumento {
    static #contadorPercusion = 0;

    identidad;    
    
    static totalInstrumentosPercusion() {
        return Percusion.#contadorPercusion;
    }
    
    afinar() {
        this.manipulador.textContent += "Afinando instrumento Percusión ...";
    }

    tocarNota(sonido) {
        this.manipulador.textContent += "Instrumento percusion tocando " + sonido;
    }

    ajustarTensores() {
        this.manipulador.textContent += "Intrumento Percusion ajustando tensores... ";
    }

    constructor() {
        super();
        Percusion.#contadorPercusion++;
        this.identidad = Percusion.#contadorPercusion;           
    }
}

class Guitarra extends Cuerda {
    afinar() {
        this.manipulador.textContent += "Afinando guitarra " + this.identidad + " ...";
    }

    tocarNota(nota) {
        this.manipulador.textContent += "Guitarra " + this.identidad + " tocando " + nota;
    }

    cambiarCuerdas() {
        this.manipulador.textContent += "Cambiando cuerdas guitarra " + this.identidad + " ... ";
    }

    constructor() {
        super();
    }
}

class Bajo extends Cuerda {
    afinar() {
        this.manipulador.textContent += "Afinando bajo "  + this.identidad + " ...";
    }

    tocarNota(nota) {
        this.manipulador.textContent += "Bajo " + this.identidad + " tocando " + nota;
    }

    cambiarCuerdas() {
        this.manipulador.textContent += "Cambiando cuerdas bajo "  + this.identidad + " ... ";
    }

    constructor() {
        super();
    }
}


class Trompeta extends Viento {
    afinar() {
        this.manipulador.textContent += "Afinando trompeta " + this.identidad + " ...";
    }

    tocarNota(nota) {
        this.manipulador.textContent += "Trompeta " + this.identidad + " tocando " + nota;
    }

    limpiarTubo() {
        this.manipulador.textContent += "Trompeta "  + this.identidad + " limpiando tubo... ";
    }

    constructor() {
        super();
    }
}

class Clarinete extends Viento {
    afinar() {
        this.manipulador.textContent += "Afinando clarinete " + this.identidad + " ...";
    }

    tocarNota(nota) {
        this.manipulador.textContent += "Clarinete " + this.identidad + " tocando " + nota;
    }

    limpiarTubo() {
        this.manipulador.textContent += "Clarinete "  + this.identidad + " limpiando tubo... ";
    }

    constructor() {
        super();
    }
}

class Timbal extends Percusion {
    afinar() {
        this.manipulador.textContent += "Afinando timbal " + this.identidad + " ...";
    }

    tocarNota(sonido) {
        this.manipulador.textContent += "Timbal " + this.identidad + " tocando " + sonido;
    }

    ajustarTensores() {
        this.manipulador.textContent += "Timbal " + this.identidad + " ajustando tensores... ";
    }

    constructor() {
        super();
    }
}
