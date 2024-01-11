/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_7a;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_7a {

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int NuevaNota = 0;
        System.out.println("Introduzca una nota");
        NuevaNota = scn.nextInt();
        while ((NuevaNota <=1) || (NuevaNota >=20)){
        System.out.println("La nota debe estar comprendida entre 1 y 20");
        NuevaNota = scn.nextInt();
        }
        switch (NuevaNota){ //establecemos todos los casos posibles
            case 0: //secuencia de sentencias
                System.out.println("El valor debe estar comprendido entre 0 y 20");
                break; 
            case 1:
            case 2: 
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                System.out.println("la calificación es E");
                break;
            case 10:
            case 11:
            case 12:
                System.out.println("la calificación es D");
                break;
            case 13:
            case 14:
            case 15:
                System.out.println("la calificación es C");
                break;
            case 16: 
            case 17:
            case 18:
                System.out.println("la calificación es B");
                break;
            case 19:
            case 20:
                System.out.println("la calificación es A");
                break;
        }
    }
}
