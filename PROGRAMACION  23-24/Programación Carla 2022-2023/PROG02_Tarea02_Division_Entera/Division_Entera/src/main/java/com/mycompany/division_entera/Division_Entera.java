/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.division_entera;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Division_Entera {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int dividendo = 0;
        int divisor = 0;
        int cociente = 0;
        int resto = 0;
        int N = 0;
        System.out.println("Primero, introduce un número entero");
        dividendo = scn.nextInt();
        System.out.println("El dividendo es " +dividendo);
        System.out.println("Ahora, introduce otro número entero, menor al anterior");
        divisor = scn.nextInt();
        System.out.println("El divisor es " +divisor);
        while (divisor > dividendo) {
            System.out.println("El número introducido es mayor que el anterior");
            System.out.println("¡Vuelve a intentarlo!");
            System.out.println("Ahora, introduce otro número menor al anterior");
            divisor = scn.nextInt();
        }
        while (divisor < dividendo) {
            dividendo = dividendo - divisor;
            cociente = cociente + 1;   
        }
        resto = dividendo;
        System.out.println("El cociente entre ambos es: " +cociente +"\nEl resto es: " +resto);
    }   
    
}
