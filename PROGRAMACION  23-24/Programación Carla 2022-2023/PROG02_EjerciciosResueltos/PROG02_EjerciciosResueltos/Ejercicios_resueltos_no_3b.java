/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_3b;

/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_3b {

    public static void main(String[] args) {
        int N = 0;
        int Suma = 0;
        while ((N + 2) <= 100){
        Suma = (int) Suma + N; //Asignamos acumulador
        N = (int) (N + 2);  //Asignamos contador para números pares
        System.out.println(N); // muestra los números pares
        }
        System.out.println("La suma de los números pares comprendidos entre 1 y 100 es " +Suma);
    }
}
