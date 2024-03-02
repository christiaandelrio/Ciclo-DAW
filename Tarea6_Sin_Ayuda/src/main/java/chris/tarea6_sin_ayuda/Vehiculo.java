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
public class Vehiculo {
    //Definición de atributos de la clase
    private String marca,matricula,descripcion,nombrePropietario,dniPropietario;
    double precio, numKilometros;
    LocalDate fechaMatriculacion;

    //Constructor de la clase
    public Vehiculo(String marca, String matricula, String descripcion, String nombrePropietario, String dniPropietario, double precio, double numKilometros, LocalDate fechaMatriculacion) {
        this.marca = marca;
        this.matricula = matricula;
        this.descripcion = descripcion;
        this.nombrePropietario = nombrePropietario;
        this.dniPropietario = dniPropietario;
        this.precio = precio;
        this.numKilometros = numKilometros;
        this.fechaMatriculacion = fechaMatriculacion;
    }
    
    //Métodos get y set para solicitar atributos

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

    public String getDniPropietario() {
        return dniPropietario;
    }

    public void setDniPropietario(String dniPropietario) {
        this.dniPropietario = dniPropietario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getNumKilometros() {
        return numKilometros;
    }

    public void setNumKilometros(double numKilometros) {
        this.numKilometros = numKilometros;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }
    
    //Método get_Anios(): Retorna un entero con el número de años del vehículo.
    public int get_Anios(){
    
        int anioActual = LocalDate.now().getYear(); //Año actual
        
        int anioMatriculacion = fechaMatriculacion.getYear(); //Año de matriculación
        
        int aniosCoche = anioActual - anioMatriculacion; //Calculamos la diferencia de años
        
        return aniosCoche;
    }
}
