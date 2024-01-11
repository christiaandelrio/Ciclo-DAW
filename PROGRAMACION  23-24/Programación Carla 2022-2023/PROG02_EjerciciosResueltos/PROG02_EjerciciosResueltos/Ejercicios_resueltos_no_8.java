/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_8;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_8 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int A = 0;
        int B = 0;
        int temporal = 0;
        System.out.println("Introduce un número");
        A = scn.nextInt();
        System.out.println("Introduce otro número");
        B = scn.nextInt();
        if (A > B) {
            temporal = B;
            B = A;
            A = temporal;
        } else {
        }
        System.out.printf("\nOrden = %d, %d ", A, B);
    }
}
