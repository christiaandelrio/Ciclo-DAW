/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_13_a;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_13_a {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int Num_Obreros = 50;
        int Num_H_trabajadas = 0;
        int Total_nomina = 0;
        System.out.println("Títulos de la nómina");
        while (Num_Obreros >0) {
            System.out.println("Introduzca horas trabajadas");
            Num_H_trabajadas = scn.nextInt();
            Total_nomina = Num_H_trabajadas * 30000; // el salario son horas trabajadas por el precio de la hora trabajada
            System.out.println("Obrero número " +Num_Obreros +"\nTotal: " +Total_nomina +" bolívares");
            Num_Obreros = Num_Obreros - 1;    
        }   
    }
}
