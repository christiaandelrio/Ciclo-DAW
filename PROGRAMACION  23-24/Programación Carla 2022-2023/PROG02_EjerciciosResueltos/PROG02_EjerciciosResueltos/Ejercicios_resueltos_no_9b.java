/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_9b;

/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_9b {

    public static void main(String[] args) {
        int N = 0; //número dado
        int S = 0; //inicializamos sumatorio
        for (N = 0; N <= 20; N += 1) {
            if ((N != 0 ) && (N != 2) && (N % 2 == 0)) { //0 y 2 no son múltiplos de 2
                System.out.println(N +" es múltiplo de 2");
            } else {
                System.out.println(N +" no es múltiplo de 2");
            }
        }
    }
}
