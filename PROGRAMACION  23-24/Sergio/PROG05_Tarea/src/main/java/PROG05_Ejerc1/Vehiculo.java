/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG05_Ejerc1;

import java.time.LocalDate; // Este paquete contiene LocalDate.
import java.time.Period; 

/**
 * Clase que representa un vehículo con atributos como marca, matrícula, kilómetros, etc.
 *
 * @author Sergio García López
 */
public class Vehiculo {

    //Creamos las varibles necesarias encapsulando mediante el modificador private. Accederemos a las mismas con sus getters y setters definidos en esta clase.
    private String marca, matricula, descripcion, nombrePropietario, DNIPropietario;
    private int numKm;
    private double precio;
    private LocalDate fechaCreacion;
    private LocalDate fechaMatriculacion;

    /**
     * Constructor de la clase Vehiculo.
     *
     * @param marca Marca del vehículo.
     * @param matricula Matrícula del vehículo.
     * @param descripcion Descripción del vehículo.
     * @param nombrePropietario Nombre del propietario del vehículo.
     * @param DNIPropietario DNI del propietario del vehículo.
     * @param numKm Número de kilómetros del vehículo.
     * @param fechaMatriculacion Fecha de matriculación del vehículo.
     * @param precio Precio del vehículo.
     */
    public Vehiculo(String marca, String matricula, String descripcion, String nombrePropietario, String DNIPropietario, int numKm, LocalDate fechaMatriculacion, double precio) {
        this.marca = marca;
        this.matricula = matricula;
        this.descripcion = descripcion;
        this.nombrePropietario = nombrePropietario;
        this.DNIPropietario = DNIPropietario;
        this.numKm = numKm;
        this.precio = precio;
        this.fechaCreacion = LocalDate.now();
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getDNIPropietario() {
        return DNIPropietario;
    }

    public void setDNIPropietario(String DNIPropietario) {
        this.DNIPropietario = DNIPropietario;
    }

    public int getNumKm() {
        return numKm;
    }

    public void setNumKm(int numKm) {
        this.numKm = numKm;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    /**
     * Método que devuelve la antigüedad del vehículo en años.
     *
     * @return Número de años desde la matriculación hasta la fecha actual.
     */
    public int get_Anios() {
        return (Period.between(this.fechaMatriculacion, LocalDate.now()).getYears());
    }

    /**
     * Actualiza el número de kilómetros de un vehículo sumándole los introducidos
     * @param nuevosKms 
     */
    public void actualizar_kms (int nuevosKms){
        this.numKm = this.numKm + nuevosKms;
    }

    /**
     * Representación en cadena del objeto Vehiculo.
     *
     * @return Cadena que representa el objeto Vehiculo.
     */
    @Override
    public String toString() {
        return "Vehiculo: marca=" + marca + ", matrícula=" + matricula + ", descripción=" + descripcion + ", nombre del propietario=" + nombrePropietario + ", DNI del propietario=" + DNIPropietario + ", número de kilómetros=" + numKm + ", precio=" + precio + ", fecha de creación=" + fechaCreacion + ", fecha de matriculación=" + fechaMatriculacion;
    }

}
