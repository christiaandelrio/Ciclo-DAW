/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo.prog07_tarea;

/**
 * Clase abstracta (no se pueden instanciar objetos de esta clase) que representa una cuenta bancaria con un titular, saldo y número IBAN.
 * Implementa la interfaz Imprimible para proporcionar un método de impresión común.
 * @author Sergio García López
 */
public abstract class CuentaBancaria implements Imprimible {

    private Persona titular;
    private double saldo;
    private String IBAN;

    //Constructor de la clase CuentaBancaria.
    public CuentaBancaria(Persona titular, double saldo, String IBAN) {
        this.titular = titular;
        this.saldo = saldo;
        this.IBAN = IBAN;
    }

    //Getters y setters.
    public Persona getTitular() {
        return titular;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    //Método para devolver la información de la cuenta en forma de cadena. Implementa el método de la interface Imprimible.
    @Override
    public String devolverInfoString() {
        return "titular=" + titular + ", saldo=" + saldo + ", numCuenta=" + IBAN;
    }

}
