/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_9a;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_9a {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int J = 2;
        int S = 0; //sumatorio de números no primos
        int N = 0; //número a introducir
        System.out.println("Introduzca un número");
        N = scn.nextInt();
        //0, 1 y 4 no son primos
        if ((N == 0) || (N == 1) || (N == 4)) {
            System.out.println(N +" no es primo");
        }
        for (J = 2; J <= N / 2; J+=1) {
            if (N % J == 0) {
            J = J + 1;
            S = J + 1;
        } 
        if (S == 0){
            //si es divisible por cualquiera de estos número no es primo
        System.out.println(N +" es primo");
        } else {
        System.out.println(N +" no es primo");
        }      
        }
    }
}
//-Errores-
//"=" es un operador de asignación no de comparación(==)
//al usar la sentencia while, si se cumple la condición, el bucle no se para nunca
//falta por añadir que si 0, 1 o 4, el número no es primo
//la condicion es si N % J == 0, no es primo; en lugar de N / J == 0