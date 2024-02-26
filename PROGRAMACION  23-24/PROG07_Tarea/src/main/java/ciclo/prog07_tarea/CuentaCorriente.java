/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo.prog07_tarea;

/**
 * Clase abstracta (no se pueden instanciar objetos de la misma) que representa
 * una cuenta corriente, que es un tipo de cuenta bancaria, con una lista de
 * entidades asociadas. Extiende la clase CuentaBancaria para heredar sus
 * atributos y comportamientos.
 *
 * @author Sergio García López
 */
public abstract class CuentaCorriente extends CuentaBancaria {

    private String listaEntidades;

    //Constructor de la clase CuentaCorriente.
    public CuentaCorriente(String listaEntidades, Persona titular, double saldo, String IBAN) {
        super(titular, saldo, IBAN);
        this.listaEntidades = listaEntidades;
    }

    //Getters y setters
    public String getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(String listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    // Método para devolver la información de la cuenta corriente en forma de cadena.
    // Concatena la información de la cuenta bancaria con la lista de entidades asociadas.
    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() + ", lista de entidades= " + listaEntidades;
    }

}
