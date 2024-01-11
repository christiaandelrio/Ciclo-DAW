/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario;

/**
 *
 * @author xavi
 */
public class Vehiculo {
    private String marca;
    private String matrícula;
    private int precio;
    private int kilómetros;
    private String descripción;
    
    public Vehiculo(String matricula,String marca,String descripción,int precio) {
        this.matrícula=matricula;
        this.marca=marca;
        this.precio=precio;
        this.descripción=descripción;
        this.precio=precio;
        this.kilómetros=0;
    }

    public String getMarca() {
        return marca;
    }

    public String getMatrícula() {
        return matrícula;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getKilómetros() {
        return kilómetros;
    }

    public void setKilómetros(int kilómetros) {
        this.kilómetros = kilómetros;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    
    @Override
    public String toString() {
        return  marca+" "+matrícula+"  "+precio+"€. "+kilómetros+"Kms\n\t"+descripción;
    }
    
    public static boolean matriculaValida(String matricula) {
        return matricula.matches("[0-9]{4}[A-Z]{3}");
    }
}
