/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog05_Ejerc1;
import java.time.LocalDate;
import java.time.Period;
/**
 *
 * @author Carla Portela Ubeira
 * Esta clase permite la creación de objetos cuyos atributos y métodos se describen a continuación
 */
public class Vehiculo {
    //Declaración de atributos de la clase vehiculo
    String marca;
    String matricula;
    double numKilometros;
    LocalDate fechaMatriculacion;
    String descripcion;
    int precio;
    String nombrePropietario;
    String dniPropietario;
    //Implemetamos el constructor personalizado con los siguientes argumentos
    public Vehiculo (String marca, String matricula, double numKilometros, LocalDate fechaMatriculacion, String descripcion, int precio, String nombrePropietario, String dniPropietario) {
    this.marca = marca;// utilizamos el operador this para diferenciar entre atributos de clase y argumentos del constructor
    this.matricula = matricula;
    this.numKilometros = numKilometros;
    this.descripcion = descripcion;
    this.precio = precio;
    this.nombrePropietario = nombrePropietario;
    this.dniPropietario = dniPropietario;
    }
    //establecemos métodos set y get para los atributos de clase para poner obtenerlos y modificarlos
    public void setMarca (String marca) {
        this.marca = marca;
    };
    public String getMarca () {
        return marca;
    }
    public void setMatricula (String matricula) {
        this.matricula = matricula;
    }
    public String getMatricula () {
        return matricula;
    }
    public void setKm (double km) {
        this.numKilometros = km;
    }
    public double getKm () {
        return numKilometros;
    }
    public LocalDate getFechaMatriculacion () {
        return fechaMatriculacion;
    }
    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
      this.fechaMatriculacion = fechaMatriculacion;  
    }
    public void setDescripcion (String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion () {
        return descripcion;
    }
    public void setPrecio (int precio) {
        this.precio = precio;
    }
    public double getPrecio () {
        return precio;
    }
    public void setNombre (String nombre) {
        this.nombrePropietario = nombre;
    }
    public String getNombre() {
        return nombrePropietario;
    }
    public void setDni(String dniPropietario) {
        this.dniPropietario = dniPropietario;
    }
    public String getDni() {
        return dniPropietario;
    }
    //implementamos un metodo para calcular los años de matriculacion
    public int getAnios() {
        LocalDate hoy = LocalDate.now();
        return (Period.between(this.fechaMatriculacion, hoy).getYears());
    }
    //implementamos un metodo para actualizar el numero de km del vehiculo
    public void kmActuales (int kmNuevos) {
        this.numKilometros = this.numKilometros + kmNuevos;
    }
}
