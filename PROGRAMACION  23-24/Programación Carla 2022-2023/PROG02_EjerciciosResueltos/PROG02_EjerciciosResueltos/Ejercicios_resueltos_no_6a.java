/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_6a;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_6a {

    public static void main(String[] args) {
    Scanner scn=new Scanner(System.in);
    int N = 0;
    System.out.println("Escribe un número");
    N=scn.nextInt();
    if (N % 2 == 0){ //Si es par el resto de divid entre 2 es cero
            System.out.println(N +" es par");
    } else {
            System.out.println(N +" es impar");
    }
    }
}
