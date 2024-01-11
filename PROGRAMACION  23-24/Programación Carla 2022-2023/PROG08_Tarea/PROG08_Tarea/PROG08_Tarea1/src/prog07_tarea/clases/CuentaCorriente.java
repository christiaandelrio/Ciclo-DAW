/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog07_tarea.clases;

//---------------------------------------------------------------        
//                      CLASE CuentaCorriente (abstracta)
//--------------------------------------------------------------- 
public abstract class CuentaCorriente extends CuentaBancaria {

    //---------------------------------------------------------------        
    //                          ATRIBUTOS
    //---------------------------------------------------------------            
    // Atributos privados que almacenan el "estado" del objeto
    public String listaEntidadesAutorizadas;
    
    // Atributos estátticos públicos que almacenan constantes
    // relacionadas con características específicas de la clase
 
    
    
    
    //---------------------------------------------------------------        
    //             CONSTRUCTORES
    //---------------------------------------------------------------            
    
    // Constructores
    public CuentaCorriente (Persona titular, String ccc) throws Exception {
        super (titular, ccc);
        this.listaEntidadesAutorizadas=null;
    }    
 
    //---------------------------------------------------------------        
    //             MÉTODOS DE INTERFAZ
    //---------------------------------------------------------------    
 
    @Override
    public String devolverContenidoString() {
        String contenido=super.devolverContenidoString();
        return contenido;
    }
}

