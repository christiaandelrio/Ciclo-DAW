/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo.prog07_tarea;

/**
 * Clase que representa una cuenta corriente de empresa, que es un tipo de
 * cuenta corriente con características específicas para empresas, como un
 * máximo descubierto, tipo de interés por descubierto y comisión fija por
 * descubierto. Extiende la clase CuentaCorriente para heredar sus atributos y
 * comportamientos.
 *
 * @author Sergio García López
 */
public class CuentaCorrienteEmpresa extends CuentaCorriente {

    private double maximoDescubierto;
    private double tipoInteresDescubierto;
    private double comisionFijaDescubierto;

    // Constructor de la clase CuentaCorrienteEmpresa
    public CuentaCorrienteEmpresa(double maximoDescubierto, double tipoInteresDescubierto, double comisionFijaDescubierto, String listaEntidades, Persona titular, double saldo, String IBAN) {
        super(listaEntidades, titular, saldo, IBAN);
        this.maximoDescubierto = maximoDescubierto;
        this.tipoInteresDescubierto = tipoInteresDescubierto;
        this.comisionFijaDescubierto = comisionFijaDescubierto;
    }

    // Getters y setters.
    public double getMaximoDescubierto() {
        return maximoDescubierto;
    }

    public void setMaximoDescubierto(double maximoDescubierto) {
        this.maximoDescubierto = maximoDescubierto;
    }

    public double getTipoInteresDescubierto() {
        return tipoInteresDescubierto;
    }

    public void setTipoInteresDescubierto(double tipoInteresDescubierto) {
        this.tipoInteresDescubierto = tipoInteresDescubierto;
    }

    public double getComisionFijaDescubierto() {
        return comisionFijaDescubierto;
    }

    public void setComisionFijaDescubierto(double comisionFijaDescubierto) {
        this.comisionFijaDescubierto = comisionFijaDescubierto;
    }

    // Método para devolver la información de la cuenta corriente de empresa en forma de cadena.
    // Concatena la información de la cuenta corriente con las características específicas para empresas.
    @Override
    public String devolverInfoString() {
        return super.devolverInfoString() + ", máximo descubierto= " + maximoDescubierto + ", tipo de interés por descubierto= " + tipoInteresDescubierto + ", comisión fija por descubierto= " + comisionFijaDescubierto;
    }

}
