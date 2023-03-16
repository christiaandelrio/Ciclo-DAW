<?php 

/* ********************************** INTERFACES ************************ */
interface Interfaz1 {
    const constantei1 = 'hola';
    // ....
}

interface Interfaz2 {
    // ....
}

interface Interfaz3 extends Inteface2 {
    // ....
}

/* ********************************** TRAITS **************************** */
trait nombreTrait1 {
    // ...
}


/* ********************************** CLASES **************************** */

class NombreClaseBase {
    // ......
}


/* readonly | abstract | final */ class NombreClase extends NombreClaseBase 
                                                    implements Interfaz1, Interfaz3
{
    use nombreTrait1, nombreTrait2;

    // -----------------------------------------------------------------------
    //            PARTE STATIC
    // -----------------------------------------------------------------------

    /* public | private | protected */ const constante1   = valor;
    /* public | private | protected */ static $varStatic1 = valor;

     /* abstract */ /* public | private | protected */ /* final */ static function nombreFuncStatic1() {
        // ...
        //  self::constante1
        //  parent::constanteStaticEnClasePadre
        //  self::$varStatic1
        //  return new static;   <---- si se invoca este método crea un objeto y devueve una referencia
     }



    // -----------------------------------------------------------------------
    //            PROPIEDADES Y MÉTODOS MIEMBRO (DE INSTANCIA)
    // -----------------------------------------------------------------------
    /* public | private | protected */  $var1 = valor;

     /* abstract */ /* public | private | protected */ /* final */ function nombreFunc1() {
        // ...
        // $this->var1 
        //  self::constante1
        //  parent::constanteStaticEnClasePadre
        //  self::$varStatic1
        //  self::nombreFuncionStatic1()
        //  parent::nombreFuncionStaticPadre1()
        //  NombreClase::nombreFuncionStatic1()
        //  NombreClase::$varStatic1;
        //  
        //  parent::nombreFuncion1()   permite invocar la versión sobreescrita
    }

    // -----------------------------------------------------------------------
    //            CONSTRUCTORES
    // -----------------------------------------------------------------------    
    /* public | private | protected */ function __construct() {
        parent::__construct($x, $y);    // Normalmente debemos inicializar la "parte padre" del objeto
                                        // Si usamos un modificador de acceso para un parámetro de constructor
                                        //  se convierte en una propiedad del objeto
        //....
    }

    // -----------------------------------------------------------------------
    //            DESTRUCTORES
    // -----------------------------------------------------------------------    
    /* public | private | protected */ function __destruct() {
       //....
       parent::__destruct();     // al acabar de destruirnos, destruimos a "nuestra parte padre"
    }

    // -----------------------------------------------------------------------
    //            MÉTODOS MÁGICOS
    //  Permiten operar sobre propiedades y métodos creados dinámicamente
    // -----------------------------------------------------------------------    
	public __set  (string $nombre, mixed $valor): void {
        // ...
    }
	
    public __get  (string $nombre): mixed  {
        // ...
    }

	public __isset(string $nombre): bool  {
        // ...
    }

	public __unset(string $nombre): void   {
        // ...
    }


}


?>