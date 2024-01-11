/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_5a;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_5a {

    public static void main(String[] args) {
    Scanner scn=new Scanner(System.in);
    double lado1 = 0;
    double lado2 = 0;
    System.out.println("¿Cuánto mide un lado (en cm)?");
    lado1 = scn.nextDouble();
    System.out.println("¿Cuánto mide el otro (en cm)?");
    lado2 = scn.nextDouble(); 
    double area = (double) lado1 * lado2; //aplicamos la fórmula del área de un rectángulo
    System.out.println("El área del rectángulo es " +area +" cm2");
    }
}
