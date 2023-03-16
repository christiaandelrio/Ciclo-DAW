function NombreClaseVieja() {
    // Campos de instancia
    propiedadPrivada = 10;  
    this.propiedadPublic = 20 ;

    

    // Cuerpo constructor
    // super();      // no soportado
    // código restante:
    //  - acceso a variables miembro:  this.propiedad
    //  - acceso a variables static:  NombreClase.propiedadStatic
}




// *********************************************************
// ÁREA STATIC
NombreClaseVieja.propiedadStaticPublic = "valor";
// NombreClase.#propiedadStaticPrivada;   // no soportado

NombreClaseVieja.metodoStaticPublic = function (parametros) {
    // Tu Código 
}

/* No soportado
NombreClaseVieja.#metodoStaticPrivado = function (parametros) {  
    // Tu Código 
}
*/




// *********************************************************
// ÁREA DE INSTANCIA

NombreClaseVieja.prototype.setNombrePropiedad1 = function (parametros) {     
    // Código de modificación de propiedad
}


NombreClaseVieja.prototype.getNombrePropiedad1 = function (parametros) {     
    // Código de modificación de propiedad
}




const prueba = new NombreClaseVieja();
(function() {
    // Codigo de inicialización estatico
})();

Object.defineProperty(prueba, "propiedad", {
    get() { /* codigo; */ },
    set (nuevoValor) {  /* codigo;*/ }
});

console.log(prueba);