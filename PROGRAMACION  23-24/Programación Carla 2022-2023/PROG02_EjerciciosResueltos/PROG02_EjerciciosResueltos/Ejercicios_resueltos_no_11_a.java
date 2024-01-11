/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_11_a;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_11_a {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int Vel = 0;
        int Versal = 0;
        System.out.println("Introduzca la velocidad en m/s");
        Vel = scn.nextInt();
        Versal = Vel * 3600 / 1000;
        System.out.println("La velocidad en km/h es " +Versal);   
    }
}
