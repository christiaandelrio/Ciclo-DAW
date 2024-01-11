/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resuelto_no_2c;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resuelto_no_2c {

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int A;
        int B;
        int C;
        System.out.print("Introduce tres valores distintos");
        System.out.println();
        A=scn.nextInt();
        B=scn.nextInt();
        C=scn.nextInt();
        if ((A != B) && (B != C) && (C != A) && (A < B) && (A < C)) {
            System.out.println(A +" es el menor de los valores");   
        } else if ((A != B) && (B != C) && (C != A) && (B < A) && (B < C)) {
            System.out.println(B +" es el menor de los valores");
        } else if ((A != B) && (B != C) && (C != A) && (B < A) && (C < B)) {
            System.out.println(C +" es el menor de los valores");   
        } else {
            System.out.println("Los valores no son distintos\n¡Inténtalo de nuevo!");
        }
    }
}
