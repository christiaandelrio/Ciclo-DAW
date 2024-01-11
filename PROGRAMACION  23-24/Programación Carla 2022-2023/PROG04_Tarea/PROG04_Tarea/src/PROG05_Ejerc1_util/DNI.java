/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG05_Ejerc1_util;

/**
 *
 * @author Carla Portela Ubeira
 * Esta clase permite comprobar que el DNI introducido es correcto
 * calculando la letra que debería corresponderle al dni del propietario
 */
public class DNI {
   private int dniPropietario;

    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    private static char calcularLetraNIF(int dni) {

        char letra;

        // Cálculo de la letra NIF
        letra = LETRAS_DNI.charAt(dni % 23);

        // Devolución de la letra NIF
        return letra;

    }

    private static char extraerLetraNIF(String nif) {

        char letra = nif.charAt(nif.length() - 1);

        return letra;

    }

    private static int extraerNumeroNIF(String nif) {

        int numero = Integer.parseInt(nif.substring(0, nif.length() - 1));

        return numero;

    }

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

                throw new Exception ("DNI inválido: ");
            }

        }

        return valido;
    }
}
