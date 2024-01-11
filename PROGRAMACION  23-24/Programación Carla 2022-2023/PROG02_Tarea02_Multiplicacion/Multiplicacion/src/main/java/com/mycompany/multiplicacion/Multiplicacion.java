/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.multiplicacion;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Multiplicacion {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int A = 0;
        int B = 0;
        int producto = 0;
        System.out.println("Introduce un número entero");
        A = scn.nextInt();
        System.out.println("Introduce otro número entero");
        B = scn.nextInt();
        System.out.println("El producto de " +A +" x " +B +" es:");
        while (B > 0) {
            producto = producto + A;
            B = B - 1;
            
        }
        System.out.println(producto);    
    }
}
