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
public class Coche extends Vehiculo{
    //Atributos propios de la clase
    private int numRuedas;
    private boolean airbag;

    public Coche(int numRuedas, boolean airbag, String marca, String matricula, String descripcion, String nombrePropietario, String dniPropietario, double precio, double numKilometros, LocalDate fechaMatriculacion) {
        super(marca, matricula, descripcion, nombrePropietario, dniPropietario, precio, numKilometros, fechaMatriculacion);
        this.numRuedas = numRuedas;
        this.airbag = airbag;
    }
    
    //Getters y setters

    public int getNumRuedas() {
        return numRuedas;
    }

    public void setNumRuedas(int numRuedas) {
        this.numRuedas = numRuedas;
    }

    public boolean isAirbag() {
        return airbag;
    }

    public void setAirbag(boolean airbag) {
        this.airbag = airbag;
    }
    
}
