/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_1b;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_1b {

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int A;
        int B;
        int suma;
        System.out.println("Introduce un valor");
        A = scn.nextInt();
        System.out.println("Introduce otro valor");
        B = scn.nextInt();
        suma = (int) A + B;
        System.out.printf("La suma del valor %d y del valor %d es %d", A, B, suma);
    }
}
