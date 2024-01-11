/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG05_Ejerc1_util;

/**
 * Clase que proporciona métodos para validar el formato de un número de DNI español.
 * También incluye métodos para calcular la letra correspondiente a un número de DNI.
 * @author Sergio García López
 */
public class DNI {

    private int numDNI;
    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    /**
     * Método privado que calcula la letra NIF correspondiente a un número de DNI.
     * @param dni Número de DNI.
     * @return Letra NIF calculada.
     */
    private static char calcularLetraNIF(int dni) {
        char letra;

        // Cálculo de la letra NIF
        letra = LETRAS_DNI.charAt(dni % 23);

        // Devolución de la letra NIF
        return letra;
    }

    /**
     * Método privado que extrae la letra NIF de una cadena que representa un NIF completo.
     * @param nif Cadena que representa un NIF.
     * @return Letra NIF extraída.
     */
    private static char extraerLetraNIF(String nif) {
        char letra = nif.charAt(nif.length() - 1);
        return letra;
    }

    /**
     * Método privado que extrae el número de DNI de una cadena que representa un NIF completo.
     * @param nif Cadena que representa un NIF.
     * @return Número de DNI extraído.
     */
    private static int extraerNumeroNIF(String nif) {
        int numero = Integer.parseInt(nif.substring(0, nif.length() - 1));
        return numero;
    }

    /**
     * Método público que valida un número de DNI español completo (número + letra).
     * @param nif Cadena que representa un NIF completo.
     * @return true si el NIF es válido, false si no lo es.
     * @throws Exception Si el formato del DNI no es correcto.
     */
    public static boolean validarNIF(String nif) throws Exception {
        boolean valido = true;   // Suponemos el NIF válido mientras no se encuentre algún fallo
        char letra_calculada;
        char letra_leida;
        int dni_leido;

        if (nif == null) {  // El parámetro debe ser un objeto no vacío
            valido = false;
        } else if (nif.length() < 8 || nif.length() > 9) {    // La cadena debe estar entre 8(7+1) y 9(8+1) caracteres
            valido = false;
        } else {
            letra_leida = DNI.extraerLetraNIF(nif);    // Extraemos la letra de NIF (letra)
            dni_leido = DNI.extraerNumeroNIF(nif);  // Extraemos el número de DNI (int)
            letra_calculada = DNI.calcularLetraNIF(dni_leido);  // Calculamos la letra de NIF a partir del número extraído

            if (letra_leida != letra_calculada) {   // Comparamos la letra extraída con la calculada
                throw new Exception("DNI inválido: ");
            }
        }
        return valido;
    }
}
