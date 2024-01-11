/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog07_tarea.clases;


//---------------------------------------------------------------        
//                      CLASE CuentaCorrientePersonal
//--------------------------------------------------------------- 
public class CuentaCorrienteEmpresa extends CuentaCorriente {

    //---------------------------------------------------------------        
    //                          ATRIBUTOS
    //---------------------------------------------------------------            
    // Atributos privados que almacenan el "estado" del objeto
    protected double comisionFijaDescubierto;
    protected double tipoInteresDescubierto;
    protected double maximoDescubierto;
    
    // Atributos estátticos públicos que almacenan constantes
    // relacionadas con características específicas de la clase
    public static final double MIN_INTERES_DESCUBIERTO=   0.00;
    public static final double MAX_INTERES_DESCUBIERTO=  30.00;    
    public static final double MAX_DESCUBIERTO=  50000.00;    
    public static final double MIN_COMISION_DESCUBIERTO=    0.00;
    public static final double MAX_COMISION_DESCUBIERTO=  100.00;    
    
    
    
    //---------------------------------------------------------------        
    //             CONSTRUCTORES
    //---------------------------------------------------------------            
    
    // Constructores
    public CuentaCorrienteEmpresa (Persona titular, String ccc, 
            double comisionFijaDescubierto, double tipoInteresDescubierto, double maximoDescubierto) throws Exception {
        // Llamada al constructor de la superclase
        super (titular, ccc);

        // Establecimiento de atributos específicos
        establecerComisionFijaDescubierto (comisionFijaDescubierto);
        establecertipoInteresDescubierto (tipoInteresDescubierto);
        establecermaximoDescubierto (maximoDescubierto);        
    }
 
    //---------------------------------------------------------------        
    //             MÉTODOS DE INTERFAZ
    //---------------------------------------------------------------    
    // Métodos públicos para manipular los objetos de esta clase
    
    // Método obtenerComisionFijaDescubierto
    // Para consultar la comisión fija por cada descubierto que se produzca en la cuenta.      
    public  double obtenerComisionFijaDescubierto () {
        return this.comisionFijaDescubierto;
    }    
    
    // Método establecerComisionFijaDescubierto
    // Para establecer la comisión fija por cada descubierto que se produzca en la cuenta. 
    public final void establecerComisionFijaDescubierto (double comisionFijaDescubierto) throws Exception {
        if (comisionFijaDescubierto < MIN_COMISION_DESCUBIERTO || comisionFijaDescubierto > MAX_COMISION_DESCUBIERTO) 
            throw new Exception ("Comisión fija de descubierto de cuenta corriente de empresa no válida.");
        else
            this.comisionFijaDescubierto= comisionFijaDescubierto;
    }
    
    // Método obtenertipoInteresDescubierto
    // Para consultar el tipo de interés que se aplicará cuando se produzcan descubiertos.      
    public  double obtenertipoInteresDescubierto () {
        return this.tipoInteresDescubierto;
    }    
    
    // Método establecertipoInteresDescubierto
    // Para establecer el tipo de interés que se aplicará cuando se produzcan descubiertos.      
    public final void establecertipoInteresDescubierto (double tipoInteresDescubierto) throws Exception {
        if (tipoInteresDescubierto < MIN_INTERES_DESCUBIERTO || tipoInteresDescubierto > MAX_INTERES_DESCUBIERTO) 
            throw new Exception ("Tipo de inter�s de descubierto de cuenta corriente de empresa no válido.");
        else
            this.tipoInteresDescubierto= tipoInteresDescubierto;
    }
    
     // Método obtenertipoInteresDescubierto
    // Para consultar el tipo de interés que se aplicará cuando se produzcan descubiertos.      
    public double obtenermaximoDescubierto () {
        return this.tipoInteresDescubierto;
    }    
    
    // Método establecertipoInteresDescubierto
    // Para establecer el tipo de interés que se aplicará cuando se produzcan descubiertos.      
    public final void establecermaximoDescubierto (double maximoDescubierto) throws Exception {
        if ( maximoDescubierto > MAX_DESCUBIERTO) 
            throw new Exception ("M�ximo descubierto de cuenta corriente de empresa no v�lido.");
        else
            this.maximoDescubierto= maximoDescubierto;
    }   
    
    // Implementaci�n de m�todos abstractos 
    // -------------------------------------
    
   
    // Para ingresar una cantidad en la cuenta
    @Override
    public void ingresar (double cantidad) throws Exception {
        if (cantidad >0) {
            saldo += cantidad;
        }
        else {
            throw new Exception ("Cantidad no v�lida.");
        }
    }
    
    // Para retirar una cantidad de la cuenta
    @Override
    public void retirar (double cantidad) throws Exception {
        if (cantidad<0) {
            throw new Exception ("Cantidad no válida.");        
        }
        if (saldo + maximoDescubierto >= cantidad) { // Si el saldo permite retirar esa cantidad
            saldo -= cantidad;  // Se retira esa cantidad (puede haber descubiertos si saldo<0)
        }
        else {
            throw new Exception ("Saldo insuficiente.");        
        }
    }    

        // Implementaci�n de los m�todos de la interfaz Imprimible
        // -------------------------------------------------------
        
    @Override
    public String devolverContenidoString() {
        String contenido=super.devolverContenidoString() + " Comisión Fija Descubierto: " + comisionFijaDescubierto + " Tipo de Interés Descubierto: " + tipoInteresDescubierto + " Máximo Descubierto: " + maximoDescubierto;
        return contenido;
    }
}   

