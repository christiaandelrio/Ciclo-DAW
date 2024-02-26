/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo.prog07_tarea;

/**
 * Clase que representa una cuenta corriente personal, que es un tipo de cuenta
 * corriente con características específicas para personas individuales, como
 * una comisión de mantenimiento. Extiende la clase CuentaCorriente para heredar
 * sus atributos y comportamientos.
 *
 * @author Sergio García López
 */
public class CuentaCorrientePersonal extends CuentaCorriente {

    private double comisionMantenimiento;

    // Constructor de la clase CuentaCorrientePersonal.
    public CuentaCorrientePersonal(double comisionMantenimiento, String listaEntidades, Persona titular, double saldo, String IBAN) {
        super(listaEntidades, titular, saldo, IBAN);
        this.comisionMantenimiento = comisionMantenimiento;
    }

    //Getters y setters
    public double getComisionMantenimiento() {
        return comisionMantenimiento;
    }

    public void setComisionMantenimiento(double comisionMantenimiento) {
        this.comisionMantenimiento = comisionMantenimiento;
    }

    // Método para devolver la información de la cuenta corriente personal en forma de cadena.
    // Concatena la información de la cuenta corriente con la comisión de mantenimiento.
    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() + ", comisión mantenimiento= " + comisionMantenimiento;
    }

}
