/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG05_Ejerc1_util;

/**
 *
 * @author Carla Portela Ubeira
 * Esta clase permite comprobar que el DNI introducido es correcto
 * calculando la letra que deber�a corresponderle al dni del propietario
 */
public class DNI {
   private int dniPropietario;

    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    private static char calcularLetraNIF(int dni) {

        char letra;

        // C�lculo de la letra NIF
        letra = LETRAS_DNI.charAt(dni % 23);

        // Devoluci�n de la letra NIF
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

        boolean valido = true;   // Suponemos el NIF v�lido mientras no se encuentre alg�n fallo

        char letra_calculada;

        char letra_leida;

        int dni_leido;

        if (nif == null) {  // El par�metro debe ser un objeto no vac�o

            valido = false;

        } else if (nif.length() < 8 || nif.length() > 9) {    // La cadena debe estar entre 8(7+1) y 9(8+1) caracteres

            valido = false;

        } else {

            letra_leida = DNI.extraerLetraNIF(nif);    // Extraemos la letra de NIF (letra)

            dni_leido = DNI.extraerNumeroNIF(nif);  // Extraemos el n�mero de DNI (int)

            letra_calculada = DNI.calcularLetraNIF(dni_leido);  // Calculamos la letra de NIF a partir del n�mero extra�do

            if (letra_leida != letra_calculada) {   // Comparamos la letra extra�da con la calculada

                throw new Exception ("DNI inv�lido: ");
            }

        }

        return valido;
    }
}
