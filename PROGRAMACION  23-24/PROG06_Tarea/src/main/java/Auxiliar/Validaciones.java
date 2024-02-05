/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Auxiliar;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que proporciona métodos estáticos para validar diferentes tipos de
 * datos. Contiene validaciones para DNI, matrícula, nombre y apellidos, y
 * fecha.
 *
 * @author Sergio García López
 */
public class Validaciones {

    /**
     * Valida un número de DNI español.
     *
     * @param DNI Número de DNI a validar.
     * @return true si el DNI es válido, false en caso contrario.
     */
    public static boolean validaDNI(String DNI) {
        Pattern pattern = Pattern.compile("[0-9]{8}[a-zA-Z]"); //Creamos el patrón con la expresión regular.
        Matcher matcher = pattern.matcher(DNI); //Instanciamos el matcher.

        return matcher.matches(); //Devolvemos true o false en función de si el DNI introducido se ajusta o no al patrón
    }

    /**
     * Valida una matrícula de un vehículo español.
     *
     * @param matricula Matrícula a validar.
     * @return true si la matrícula es válida, false en caso contrario.
     */
    public static boolean validaMatricula(String matricula) {
        Pattern pattern = Pattern.compile("[0-9]{4}[bcdfghjklmnprstvwxyzBCDFGHJKLMNPRSTVWXYZ]{3}"); //Creamos el patrón. En las matrículas españolas sólo se permiten consonantes
        Matcher matcher = pattern.matcher(matricula); //Instanciamos el matcher

        return matcher.matches(); //Devolvemos true o false en función de si la matrícula introducida se ajusta o no al patrón
    }

    /**
     * Verifica que el nombre del propietario contenga al menos un nombre y dos
     * apellidos (no trata nombres compuestos) y su longitud no excede de 40
     * caracteres.
     *
     * @param nombreApellidos Nombre y apellidos del propietario a validar.
     * @return true si el nombre y apellidos son válidos, false en caso
     * contrario.
     */
    public static boolean validaNombreApellidos(String nombreApellidos) {
        String nombreYApellidos = nombreApellidos.trim();//Eliminamos los espacios al principio y al final
        String[] partesNombre = nombreYApellidos.split(" "); //Divimos la cadena introducida por los espacios que contenga
        return partesNombre.length >= 3 && nombreYApellidos.length() <= 40; //Comprobamos que mínimo haya 3 partes (nombre y dos apellidos) y que no superen los 40 caracteres
    }

    public static boolean FechaAnteriorAActual(LocalDate fechaIntroducida) {
        return fechaIntroducida.isBefore(LocalDate.now());
    }
}
