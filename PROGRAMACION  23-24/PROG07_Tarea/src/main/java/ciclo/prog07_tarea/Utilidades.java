/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo.prog07_tarea;

import java.util.Scanner;

/**
 *
 * @author Sergio García López
 */
public class Utilidades {

    static Scanner scan = new Scanner(System.in);

    //Método para mostrar un menú de opciones al usuario. Las opciones se le pasan en un array de Strings.
    public static void mostrarMenu(String[] opciones) {
        System.out.println("\nElija una opción:");
        for (String opcion : opciones) {
            System.out.println(opcion);
        }
    }

    //Método para solicitar una cadena al usuario y escanearla.
    public static String scanString(String mensaje) {
        System.out.print(mensaje); //Muestra mensaje
        return scan.nextLine(); //Escanea un string
    }

    //Método para solicitar un entero al usuario y escanearlo.
    public static int scanInt(String mensaje) {
        System.out.print(mensaje); // Muestra mensaje
        int valor = scan.nextInt(); // Escanea un entero
        scan.nextLine(); // Consumir la nueva línea pendiente
        return valor;
    }

     //Método para solicitar un double al usuario y escanearlo.
    public static double scanDouble(String mensaje){
        System.out.print(mensaje); //Muestra mensaje
        double valor = scan.nextDouble(); //Escanea el double
        scan.nextLine(); //Consumir la línea.
        return valor;
    }
}
