/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo.prog07_tarea;

/**
 * Clase que representa a una persona con nombre, apellidos y DNI.
 * Implementa la interfaz Imprimible para proporcionar un método de impresión común.
 * @author Sergio García López
 */
public class Persona implements Imprimible {

    private String nombre;
    private String apellidos;
    private String DNI;

    //Constructor de la clase Persona.
    public Persona(String nombre, String apellidos, String DNI) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.DNI = DNI;
    }

    //Métodos getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    //Método para obtener una representación en cadena de la información de la persona.
    @Override
    public String toString() {
        return "nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI;
    }

    //Método para devolver la información de la persona en forma de cadena. Implementa el método de la interface Imprimible.
    @Override
    public String devolverInfoString() {
        return "nombre=" + nombre + ", apellidos=" + apellidos + ", DNI=" + DNI;
    }

}
