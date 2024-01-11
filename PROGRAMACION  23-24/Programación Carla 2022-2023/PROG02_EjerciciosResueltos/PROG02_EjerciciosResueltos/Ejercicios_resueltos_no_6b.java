/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_6b;

/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_6b {

    public static void main(String[] args) {
        int N = 100; // dado un número
        int a = 0; // contador de números entre 50 y 75
        int b = 0; // contador de números mayores de 80
        int c = 0; // contador de números menores de 30
        System.out.println("Entre los números " +N +" y 0 hay:");
        for(N = 100; N >= 0; N--) { // cuenta regresiva de 100 a 0
        if ((N > 50) && (N <= 75)) {
        a = a + 1;
        } else if (N > 80) {
        b = b +1;
        } else if (N < 30) {
        c = c + 1;
        } else {
        }
        }
        System.out.println(+a +" números entre 50 y 75, ambos inclusives");
        System.out.println(+b +" números mayores de 80");
        System.out.println(+c +" números menores de 30");
    }
}
