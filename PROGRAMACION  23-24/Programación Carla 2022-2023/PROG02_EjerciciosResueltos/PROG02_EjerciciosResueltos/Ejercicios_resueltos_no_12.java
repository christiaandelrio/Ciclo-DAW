/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_12;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_12 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = 0; // nota
        int Cuenta = 0; // numero total de notas
        int Acumula = 0; // suma del total acumulado
        int Suma = 0; //cuenta regresiva while, coincidente con numero total de notas
        float Promedio = 0;
        System.out.println("Introduzca el número total de notas");
        Suma = scn. nextInt();
        while (Suma > 0 ) {
            System.out.println("Introduce una nota");
            N = scn.nextInt();
            Suma = Suma - 1;
            Cuenta = Cuenta + 1;
            Acumula = Acumula + N;
        }
        Promedio = (Acumula / Cuenta);
        System.out.println("El promedio de las notas es " +Promedio); 
    }
}
//medio y promedia son lo mismo
//para calcular el promedio necesitamos saber el total de notas
//asi como el valor de cada nota
//iniciamos la cuenta regresiva del total de notas
//mientras solicitamos ingresar el valor de las notas
//y acumulamos su suma
//cuando el numero total de notas llega a 0, se calcula el promedio