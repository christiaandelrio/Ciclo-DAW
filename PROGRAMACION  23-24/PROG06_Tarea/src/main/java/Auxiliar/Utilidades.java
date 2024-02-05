/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Auxiliar;

import java.util.Scanner;

/**
 * Clase de utilidades que proporciona métodos para mostrar menús y escanear
 * diferentes tipos de datos.
 *
 * @author Sergio García López
 */
public class Utilidades {

    static Scanner scan = new Scanner(System.in);

    /**
     * Muestra un menú con las opciones proporcionadas.
     *
     * @param opciones Arreglo de cadenas que representan las opciones del menú.
     */
    public static void mostarMenu(String[] opciones) {
        System.out.println("\nElija una opción:");
        for (String opcion : opciones) {
            System.out.println(opcion);
        }
    }

    /**
     * Muestra un mensaje para el usuario solicitando un dato y escanea una
     * cadena de texto
     *
     * @param mensaje Mensaje que se muestra antes de escanear la entrada del
     * usuario.
     * @return Cadena de texto introducida por el usuario.
     */
    public static String scanString(String mensaje) {
        System.out.print(mensaje); //Muestra mensaje
        return scan.nextLine(); //Escanea un string
    }

    /**
     * Muestra un mensaje para el usuario solicitando un dato y escanea un
     * entero
     *
     * @param mensaje Mensaje que se muestra antes de escanear la entrada del
     * usuario.
     * @return Valor entero introducido por el usuario.
     */
    public static int scanInt(String mensaje) {
        System.out.print(mensaje); // Muestra mensaje
        int valor = scan.nextInt(); // Escanea un entero
        scan.nextLine(); // Consumir la nueva línea pendiente
        return valor;
    }

    /**
     * Muestra un mensaje para el usuario solicitando un dato y escanea un
     * double
     *
     * @param mensaje Mensaje que se muestra antes de escanear la entrada del
     * usuario.
     * @return Número decimal introducido por el usuario.
     */
    public static double scanDouble(String mensaje) {
        System.out.print(mensaje); // Muestra mensaje
        double valor = scan.nextDouble(); // Escanea un double
        scan.nextLine(); // Consumir la nueva línea pendiente
        return valor;
    }
}
