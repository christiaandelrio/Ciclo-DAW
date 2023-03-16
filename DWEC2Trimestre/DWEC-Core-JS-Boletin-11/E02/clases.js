class NombreClase extends MiClaseBase {
    // *********************************************************
    // ÁREA STATIC
    // Propiedades y métodos static
    //  (una única copia para todas las instancias de la clase; existen aunque no haya objetos creados)
    // *********************************************************
    static #propiedadStaticPrivada;
    static propiedadStaticPublic;

    static ClaseAnidada = class {
        // podemos crear clases static anidadas

    }

    static #metodoStaticPrivado (parametros) {
        // Código 
    }

    static metodoStaticPublic (parametros) {
        // 
    }



    static {
        // código de inicialización de las propiedades static
    }


    // *********************************************************
    // ÁREA DE INSTANCIA (cada objeto tiene su copia propia de esta información)
    // Propiedades y métodos miembro (de instancia)
    // *********************************************************
    #propiedadPrivada;
    propiedadPublic;

   
    // -----------  Métodos accessor ------------------------------
    set propiedad(parametro) {
        // código que genera el valor de la propiedad
    }

    get propiedad() {
        // código que genera valor retornado 
    }

    // -----------  Métodos setters ------------------------------
    // Permiten modificar las propiedades al código cliente (ocultación de datos)
    // Buscan que el código cliente no trabaje directamente con la
    // forma en que representamos la información
    setNombrePropiedad1(parametros) {     
        // Código de modificación de propiedad
    }

    // -----------  Métodos getters ------------------------------
    // Permiten consultar las propiedades al código cliente (ocultación de datos)
    getNombrePropiedad1(parametros) {     
        // Código de modificación de propiedad
    }
    

    // *********************************************************
    // Constructor
    constructor(parametros) {
        super();                   // si la clase hereda de otra, deberíamos inicializar el objeto padre
        // código restante:
        //  - acceso a variables miembro:  this.propiedad o this.#propiedadOculta
        //  - acceso a variables static:  NombreClase.propiedadStatic
    }

}