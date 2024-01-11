/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_3a;

/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_3a {

    public static void main(String[] args) {
        int N = 0;
        int Suma = 0;
        while ((N +1) <= 100) {
        N = (int) (N + 1);  //Asignamos contador
        Suma = (int) Suma + N; //Asignamos acumulador
        System.out.println(N); // muestra los números del 1 al 100
        }
        System.out.println("La suma de los números comprendidos entre 1 y 100 es " +Suma);
    }
}
