/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo.prog07_tarea;

/**
 * Clase que representa una cuenta de ahorro, que es un tipo de cuenta bancaria,
 * con un tipo de interés anual adicional. Extiende la clase CuentaBancaria para
 * heredar sus atributos y comportamientos.
 *
 * @author Sergio García López
 */
public class CuentaAhorro extends CuentaBancaria {

    private double tipoInteresAnual;

    //Constructor de la clase CuentaAhorro.
    public CuentaAhorro(double tipoInteres, Persona titular, double saldo, String IBAN) {
        super(titular, saldo, IBAN);
        this.tipoInteresAnual = tipoInteres;
    }

    //Getters y setters.
    public double getTipoInteresAnual() {
        return tipoInteresAnual;
    }

    public void setTipoInteresAnual(double tipoInteresAnual) {
        this.tipoInteresAnual = tipoInteresAnual;
    }

    // Método para devolver la información de la cuenta de ahorro en forma de cadena.
    // Concatena la información de la cuenta bancaria con el tipo de interés anual.
    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() + ", tipo de interés anual= " + tipoInteresAnual;
    }

}
