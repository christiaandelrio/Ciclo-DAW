/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_3c;

/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_3c {

    public static void main(String[] args) {
        int N = 1;// inicializamos en el primer número impar
        int Suma = 0;
        int Suma_impar = 0;
        while ((N + 2) <= 300){
        N = (N + 2); // asignamos el contador de números impares
        Suma = Suma + N; // asignamos el acumulador del sumatorio
        Suma_impar = (N / 2) + 1; //el total de números impares es la mitad de N
        }    
        System.out.println("el número de números impares es " +Suma_impar);
        System.out.println("La suma de los números impares de los primeros 300 números es " +Suma +" números");
    }
}
