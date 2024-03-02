/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chris.tarea6_sin_ayuda;

import java.time.LocalDate;

/**
 *
 * @author chris
 */
public class Camion extends Vehiculo {
    
    //Atributos propios de la clase
    private double numRuedas;
    private boolean remolque;

    //Constructor
    public Camion(double numRuedas, boolean remolque, String marca, String matricula, String descripcion, String nombrePropietario, String dniPropietario, double precio, double numKilometros, LocalDate fechaMatriculacion) {
        super(marca, matricula, descripcion, nombrePropietario, dniPropietario, precio, numKilometros, fechaMatriculacion);
        this.numRuedas = numRuedas;
        this.remolque = remolque;
    }
    
    //Métodos getter y setter

    public double getNumRuedas() {
        return numRuedas;
    }

    public void setNumRuedas(double numRuedas) {
        this.numRuedas = numRuedas;
    }

    public boolean isRemolque() {
        return remolque;
    }

    public void setRemolque(boolean remolque) {
        this.remolque = remolque;
    }

    
}
