/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroautomatico;

/**
 *
 * @author xavi
 */
public class Autorizacion {
    private static int id=0;
    private final String ccc;
    private final double importe;
    private boolean valid;
    
    public Autorizacion(String ccc,double importe) {
        Autorizacion.id=Autorizacion.id+1;
        this.ccc=ccc;
        this.importe=importe;
        this.valid=true;
    }

    public static int getId() {
        return id;
    }

    public String getCcc() {
        return ccc;
    }

    public double getImporte() {
        return importe;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public void anula() {
        this.valid=false;
    }
    
}
