/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG06_Ejerc1_util;

/**
 *
 * @author chris
 */
public class Validaciones {
    
    //Clase para validar el DNI con expresión regular
    public static boolean validarDNI(String dni){
        return dni.matches("[0-9]{8}[A-Za-z]");
    }
    
    //Clase para validar matrícula según el formato NNNNLLL
    public static boolean validarMatricula(String matricula){
        return matricula.matches("[0-9]{4}[A-Za-z]{3}");
    }
    
}
