/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajeroautomatico;

/**
 *
 * @author chris
 */
public class Cuenta {
    
    //Definición de atributos de la cuenta
    int numeroCuenta, pin;
    double saldo;
    String titular;

    //Constructores de la clase cuenta
    public Cuenta() {
    }

    public Cuenta(int numeroCuenta, int pin, int saldo, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.saldo = saldo;
        this.titular = titular;
    }
    
    //Métodos getter y setter

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
    
}
