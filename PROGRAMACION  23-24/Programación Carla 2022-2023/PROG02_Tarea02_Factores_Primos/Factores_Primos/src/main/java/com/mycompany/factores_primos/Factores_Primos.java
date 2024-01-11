/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.factores_primos;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Factores_Primos {

    public static void main(String[] args) {
        Scanner scn = new Scanner (System.in);
//Los factores primos son los números primos divisores exactos
//de un numero entero
        int N = 0;
        System.out.println("Introduzca un número mayor de 1");
        N = scn.nextInt();
        System.out.println("Los factores primos de " +N +" son:");
        for (int i = 2; i <= N; i++) { //0 y 1 no son primos
            while (N % i == 0) {
                N = N / i;
                System.out.println(i);
            }
        }
    }
 }
  
