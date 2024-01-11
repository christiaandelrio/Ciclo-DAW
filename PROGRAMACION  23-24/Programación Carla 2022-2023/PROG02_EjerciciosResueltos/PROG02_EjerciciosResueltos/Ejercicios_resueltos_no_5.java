/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_5;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_5 {

    public static void main(String[] args) {
    Scanner scn=new Scanner(System.in);
    double R = 0;
    double H = 0; 
    double area = 0;
    double vol = 0;
    System.out.println("¿Cuánto mide su radio (en cm)?");
    R = scn.nextDouble();
    System.out.println("¿Cuánto mide su altura (en cm)?");
    H = scn.nextDouble();
    vol = (double) Math.PI + Math.pow(R, 2) + H; // se utiliza la constante 
    area = (double) 2 * Math.PI * R  * H + 2 * Math.PI  * Math.pow(R, 2);
    System.out.println("El área es " + area +"\nEl volumen es " +vol +" cm3");
    }
}
