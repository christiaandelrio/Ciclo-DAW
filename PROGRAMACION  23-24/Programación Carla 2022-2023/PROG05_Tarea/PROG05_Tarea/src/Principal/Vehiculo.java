/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.time.LocalDate;
import java.time.Period;
import Utiles.Inputs;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author yo
 */
public class Vehiculo {
    //Atributos de la clase
    private String marca;
    private String matricula;
    private int kms;
    private String descripcion;
    private int precio;
    
    //Constructor
    public Vehiculo(String marca, String matricula, int precio, String descripcion, int kms){
       this.marca = marca;
       this.matricula=matricula;
       this.descripcion=descripcion;
       this.precio=precio;
       this.kms=0;
    }
    
    //Métodos setter y getter para obtener y modificar atributos de instancias de clase

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }//Este setter no tiene mucho sentido, ya que la marca de un coche no se cambia, por lo que podría suprimirse

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }//Este setter no tiene mucho sentido, ya que la matrícula de un coche no se cambia, por lo que podría suprimirse

    public double getKms() {
        return kms;
    }

    public void setKms(int kms) {
        this.kms = kms;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    //Para actualizar el número de Km
    public void kmsActuales(int kmsNuevos) {
        this.kms= this.kms + kmsNuevos;
    }
    
    //Creamos un método que comrpuebe la validez de la matricula mediante expresiones regulares
    public static boolean matriculaValida(String matricula) {
        Pattern patron = Pattern.compile("[0-9]{4}[a-z A-Z]{3}");
        Matcher m = patron.matcher(matricula);
        return m.matches();
    }
    
    //Creamos un método que mediante toString() de la clase Object nos va a imprimir todas las propiedades de cada vehículo
    @Override
    public String toString() {
        return marca+" "+matricula+": "+kms+" Kms.\n"+descripcion+".\n"+precio+" euros.";
    }
    
}
