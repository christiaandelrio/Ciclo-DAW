/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ecuacion_segundo_grado;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ecuacion_segundo_grado {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        double a = 0; //coeficientes a , b ,c
        double b = 0;
        double c = 0;
        double x = 0;
        double x2 = 0; //dos posible soluciones si D > 0
        double D = Math.pow(b, 2) - 4 * a * c; //Discriminante
        //una ecuación de segundo grado se define como:
        //a * x^2 + b * x + c = 0
        //a != 0
        System.out.println("Introduzca el valor de \"a\"");
        a = scn.nextDouble();
        while (a <= 0) {
            System.out.println("el valor de \"a\" debe ser distinto a 0");
            a = scn.nextDouble();
        }
        System.out.println("Introduzca el valor de \"b\"");
        b = scn.nextDouble();
        System.out.println("Introduzca el valor de \"c\"");
        c = scn.nextDouble();
        System.out.println("La ecuación de segundo grado\nax^2 + bx + c = 0");
       
        if (D >= 0 ) {
            if (D == 0) {
            x = ((-b) - (4 * a * c)) / (2 * a);
            System.out.println("Tiene solución\nx = " +x);
            } else { // D > 0
                x = ((-b) + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
                x2 = x = ((-b) + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
            System.out.println("Tiene dos posibles soluciones\nx = " +x +"\nx = " +x2);
            }
        } else { // D < 0
        System.out.println("No tiene solución");
        }  
    }
}
