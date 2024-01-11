/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG05_Ejerc1_util;

import java.time.LocalDate;

/**
 * Clase de utilidades con métodos para validar datos, como el formato del DNI y
 * la comparación de fechas.
 *
 * @author Sergio García López
 */
public class Validaciones {

    /**
     * Comprueba si la fecha introducida es anterior a la fecha actual.
     *
     * @param fecha Fecha a comprobar.
     * @return true si la fecha es anterior a la actual, false en caso contrario.
     */
    public static boolean compararFechaAnteriorActual(LocalDate fecha) {
        return fecha.compareTo(LocalDate.now()) < 0; //El método compareTo de LocalDate compara dos fechas y devuelve un valor negativo si la primera fecha es menor y viceversa. Por tanto si la condición es < 0 devuelve true, y sino false.
        //return fecha.isBefore(LocalDate.now()); //Esta sería otra forma de hacer lo mismo usando el método isBefore de LocalDate
    }

    /**
     * Valida el formato del DNI.
     * @param dni DNI a validar.
     * @throws Exception Si el formato del DNI no es correcto.
     */
    public static void validaDNI(String dni) throws Exception {
        DNI.validarNIF(dni); //Emplea el método validarNIF de la clase DNI
    }
}
