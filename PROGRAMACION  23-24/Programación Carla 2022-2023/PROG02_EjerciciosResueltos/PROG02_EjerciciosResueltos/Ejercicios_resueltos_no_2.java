/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_2;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_2 {

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
        if ((A > B)&& (A > C)) {
                            System.out.println(A +" es el mayor de los valores");
        } else if ((B > A) && (B > C)) {
                            System.out.println(B +" es el mayor de los valores");
        } else {
                            System.out.println(C +" es el mayor de los valores");
        }
    }
}
