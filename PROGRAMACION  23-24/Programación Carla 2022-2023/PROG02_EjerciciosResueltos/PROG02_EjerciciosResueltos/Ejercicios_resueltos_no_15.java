/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_15;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_15 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int Años = 0;
        int Meses = 0;
        int Dias = 0;
        int Semanas = 0;
        System.out.println("Ingrese número de días");
        Dias = scn.nextInt();
        while (Dias > 365) {
            Años = Dias / 365;
            Dias = Dias - Años * 365;
        }
        while (Dias > 30) {
            Meses = Dias / 30;
            Dias = Dias - Meses * 30;
        }
        while (Dias > 7) {
            Semanas = Dias / 7;
            Dias = Dias - Semanas * 7;
        }
        System.out.println(+Años +" años, " +Meses +" meses, " +Semanas +" semanas y " +Dias + " días");
        
    }
}
