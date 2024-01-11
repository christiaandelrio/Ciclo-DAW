/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_7;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_7 {

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int NuevaNota = 0;
        System.out.println("Introduzca una nota");
        NuevaNota = scn.nextInt();
        if ((NuevaNota >= 19) && (NuevaNota <= 20)) {
        System.out.println("La calificación es A"); 
        // para que el caracter se vea en pantalla le damos formato con C
        } else if ((NuevaNota >= 16) && (NuevaNota <= 18)) {
        System.out.println("La calificación es B");
        } else if ((NuevaNota >= 13) && (NuevaNota <= 15)) {
        System.out.println("La calificación es C"); 
        } else if ((NuevaNota >= 10) && (NuevaNota <= 12)) {
        System.out.println("La calificación es D"); 
        } else if ((NuevaNota >= 1) && (NuevaNota <= 9)) {
        System.out.println("La calificación es E");
        } else {
        System.out.println("La nota debe estar comprendida entre 1 y 20");
        }
    }
}
