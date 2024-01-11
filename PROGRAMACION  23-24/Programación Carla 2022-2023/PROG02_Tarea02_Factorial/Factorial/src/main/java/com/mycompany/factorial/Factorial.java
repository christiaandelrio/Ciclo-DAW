/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.factorial;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Factorial {

    public static void main(String[] args) {
       Scanner scn = new Scanner (System.in);
       int n = 0;
       int Factorial = 1; //se inicializa en 1 (1 - n)
/*Factorial de n:
* Producto de todos los número enteros positivos
* entre 1 - n
*/
        System.out.println("Introduce un número entero");
        n = scn.nextInt();
        System.out.println("El factorial de " +n +" es:");
        while (n >= 1) {
            Factorial = Factorial * n;
            n = n - 1;   
        }
        System.out.println(Factorial);
    }
}
