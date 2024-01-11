/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_4a;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_4a {

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        double catA = 0;
        double catB = 0;
        double hip = 0;
        System.out.println("¿Cuánto mide el cateto A (en cm.)?");
        catA=scn.nextDouble();
        System.out.println("¿Cuánto mide el cateto B (en cm.)?");
        catB=scn.nextDouble();
        hip = (double) Math.sqrt(catA) + Math.sqrt(catB);
        System.out.println(" La hipotenusa mide " +hip +" cm");
        // falta elevar los catetos al cuadrado; sería
        //double catA2 = Math.pow(catA, 2);
        //double catB2 = Math.porw(catB, 2);
        //hip = Math.sqrt(catA2 + catB2);  

    }
}
