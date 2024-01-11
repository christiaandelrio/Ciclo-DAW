/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_4b;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_4b {

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        double lado = 0;
        double area = 0;
        System.out.println("¿Cuánto mide un lado del cuadrado (en cm)?");
        lado = scn.nextDouble();
        area = (double) Math.pow(lado, 2); //para elevar a un exponente se utiliza Math.pow
        System.out.println("El área del cuadrado es " +area +" cm2");
    }
}
