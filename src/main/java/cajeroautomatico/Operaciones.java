/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajeroautomatico;

/**
 *
 * @author chris
 */
public class Operaciones {
    
    //Creamos un objeto del tipo cuenta para operar con él
    private static Cuenta cuenta;

    public Operaciones(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    //Métodos para operar con la cuenta
    public void retirarSaldo(double cantidad){
        if(cuenta.getSaldo() >= cantidad){
            cuenta.setSaldo(cuenta.getSaldo() - cantidad);
        }
    }
    
    public void ingresarSaldo(double cantidad){
        cuenta.setSaldo(cuenta.getSaldo() + cantidad);
    } 
}
