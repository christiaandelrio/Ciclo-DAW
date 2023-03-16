<?php 

class Instrumento {
    static private int $contador = 0;

    public static function totalInstrumentos() {
        return self::$contador;
    }

    public function afinar() {
        echo  "Afinando Instrumento ...";
    }

    public function tocarNota($nota) {
        echo  "Instrumento tocando " . $nota;
    }

    public function __construct() {
        self::$contador++;
    }
}


class Cuerda extends Instrumento {
    static private int $contadorCuerda = 0;

    protected int $identidad;
   
    public static function totalInstrumentosCuerda() {
        return self::$contadorCuerda;
    }

    public function afinar() {
        echo  "Afinando instrumento Cuerda...";
    }

    public function tocarNota($nota) {
        echo  "Instrumento Cuerda tocando " . $nota;
    }

    public function cambiarCuerdas() {
        echo  "Instrumento Cuerda cambiando cuerdas... ";

    }

    public function __construct() {
        parent::__construct();
        self::$contadorCuerda++;
        $this->identidad = self::$contadorCuerda;
    }
}


class Viento extends Instrumento {
    static private int $contadorViento = 0;

    protected int $identidad;    

    public static function totalInstrumentosViento() {
        return self::$contadorViento;
    }
    
    public function afinar() {
        echo  "Afinando instrumento Viento...";
    }

    public function tocarNota($nota) {
        echo  "Instrumento Viento tocando " . $nota;
    }

    public function limpiarTubo() {
        echo  "Intrumento Viento limpiando tubo... ";
    }

    public function __construct() {
        parent::__construct();
        self::$contadorViento++;
        $this->identidad = self::$contadorViento;        
    }
}

class Percusion extends Instrumento {
    static private int $contadorPercusion = 0;

    protected int $identidad;    
    
    public static function totalInstrumentosPercusion() {
        return self::$contadorPercusion;
    }
    
    public function afinar() {
        echo  "Afinando instrumento Percusión ...";
    }

    public function tocarNota($sonido) {
        echo  "Instrumento percusion tocando " . $sonido;
    }

    public function ajustarTensores() {
        echo  "Intrumento Percusion ajustando tensores... ";
    }

    public function __construct() {
        parent::__construct();
        self::$contadorPercusion++;
        $this->identidad = self::$contadorPercusion;           
    }
}

class Guitarra extends Cuerda {
    public function afinar() {
        echo  "Afinando guitarra " . $this->identidad . " ...";
    }

    public function tocarNota($nota) {
        echo  "Guitarra " . $this->identidad . " tocando " . $nota;
    }

    public function cambiarCuerdas() {
        echo  "Cambiando cuerdas guitarra " . $this->identidad . " ... ";
    }

    public function __construct() {
        parent::__construct();
    }
}

class Bajo extends Cuerda {
    public function afinar() {
        echo  "Afinando bajo "  . $this->identidad . " ...";
    }

    public function tocarNota($nota) {
        echo  "Bajo " . $this->identidad . " tocando " . $nota;
    }

    public function cambiarCuerdas() {
        echo  "Cambiando cuerdas bajo "  . $this->identidad . " ... ";
    }

    public function __construct() {
        parent::__construct();
    }
}


class Trompeta extends Viento {
    public function afinar() {
        echo  "Afinando trompeta " . $this->identidad . " ...";
    }

    public function tocarNota($nota) {
        echo  "Trompeta " . $this->identidad . " tocando " . $nota;
    }

    public function limpiarTubo() {
        echo  "Trompeta "  . $this->identidad . " limpiando tubo... ";
    }

    public function __construct() {
        parent::__construct();
    }
}

class Clarinete extends Viento {
    public function afinar() {
        echo  "Afinando clarinete " . $this->identidad . " ...";
    }

    public function tocarNota($nota) {
        echo  "Clarinete " . $this->identidad . " tocando " . $nota;
    }

    public function limpiarTubo() {
        echo  "Clarinete "  . $this->identidad . " limpiando tubo... ";
    }

    public function __construct() {
        parent::__construct();
    }
}

class Timbal extends Percusion {
    public function afinar() {
        echo  "Afinando timbal " . $this->identidad . " ...";
    }

    public function tocarNota($sonido) {
        echo  "Timbal " . $this->identidad . " tocando " . $sonido;
    }

    public function ajustarTensores() {
        echo  "Timbal " . $this->identidad . " ajustando tensores... ";
    }

    public function __construct() {
        parent::__construct();
    }
}


?>