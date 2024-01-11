/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author yo
 */
class Cliente {

    private String nombre;
    private String dni;

    public Cliente(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    static boolean validaDni(String dni) {//Solo valida que coincida el formato
        return dni.matches("[0-9]{8}[A-Z]");//Si coincide con el patrón devuelve true
    }
    
    static boolean validaNombre(String nombre) {
        boolean ok = false;
       //Dividimos el String entres partes separadas por un espacio
       String[] partes = nombre.split("\b*");//por cada separador de palabra dividimos
       if((partes.length>=3)&&(nombre.length()<=40)){//True si como mínimo tiene 3 partes y el número total de caracteres no excede 40;
           ok = true;
       }
       return ok;
    }

}
