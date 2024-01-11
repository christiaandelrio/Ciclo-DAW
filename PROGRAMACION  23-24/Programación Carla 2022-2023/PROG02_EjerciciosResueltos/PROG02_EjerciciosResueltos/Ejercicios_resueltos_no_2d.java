/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_2d;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_2d {

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int A;
        int B;
        int C;
        int D;
        System.out.print("Introduce tres valores distintos");
        System.out.println();
        A=scn.nextInt();
        B=scn.nextInt();
        C=scn.nextInt();
        D=scn.nextInt();
        if ((A != B) && (B != C) && (C != A) && (D != A) && (D != B) && (D != C) && (A > B) && (A > C) && (A > D)) {
            System.out.println(A +" es el mayor de los valores");   
        } else if ((A != B) && (B != C) && (C != A) && (D != A) && (D != B) && (D != C) && (B > A) && (B > C) && (B > D)) {
            System.out.println(B +" es el mayor de los valores");
        } else if ((A != B) && (B != C) && (C != A) && (D != A) && (D != B) && (D != C) && (C > A) && (C > B) && (C > D)) {
            System.out.println(C +" es el mayor de los valores"); 
        } else if ((A != B) && (B != C) && (C != A) && (D != A) && (D != B) && (D != C) && (D > A) && (D > B) && (D > C)) {
            System.out.println(D +" es el mayor de los valores"); 
        } else {
            System.out.println("Los valores no son distintos\n¡Inténtalo de nuevo!");
        }
        if ((A != B) && (B != C) && (C != A) && (D != A) && (D != B) && (D != C) && (A < B) && (A < C) && (A < D)) {
            System.out.println(A +" es el menor de los valores");   
        } else if ((A != B) && (B != C) && (C != A) && (D != A) && (D != B) && (D != C) && (B < A) && (B < C) && (B < D)) {
            System.out.println(B +" es el menor de los valores");
        } else if ((A != B) && (B != C) && (C != A) && (D != A) && (D != B) && (D != C) && (B < A) && (C < B) && (C < D)) {
            System.out.println(C +" es el menor de los valores"); 
        } else if ((A != B) && (B != C) && (C != A) && (D != A) && (D != B) && (D != C) && (D < A) && (D < B) && (D < C)) {
            System.out.println(D +" es el menor de los valores");   
        } else {
            System.out.println("Los valores no son distintos\n¡Inténtalo de nuevo!");
        }
    }
}
