/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.millas_a_km;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Millas_a_Km {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int Km = 0;
        double Millas = 0;
        System.out.println("Introduce un número de Km");
        Km = scn.nextInt();
        Millas = (double) Km * 0.621371; // 1 Km equivale a 0.621371 millas
        System.out.println("En " +Km +" Km hay " +Millas +" millas");
    }
}
