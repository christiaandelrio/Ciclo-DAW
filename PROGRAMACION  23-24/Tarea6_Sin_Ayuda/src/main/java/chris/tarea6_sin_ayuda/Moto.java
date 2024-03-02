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
public class Moto extends Vehiculo{
    //Atributos propios de la clase
    private boolean sidecar;

    public Moto(boolean sidecar, String marca, String matricula, String descripcion, String nombrePropietario, String dniPropietario, double precio, double numKilometros, LocalDate fechaMatriculacion) {
        super(marca, matricula, descripcion, nombrePropietario, dniPropietario, precio, numKilometros, fechaMatriculacion);
        this.sidecar = sidecar;
    }

    //Getters y setters de la clase

    public boolean isSidecar() {
        return sidecar;
    }

    public void setSidecar(boolean sidecar) {
        this.sidecar = sidecar;
    }
    
    
}
