/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.numero_e;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Numero_e {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        double e = 0; //e=1 + 1/1! + 1/2! +....+1/n!
        int n = 0;
        double factorial = 1; //Inicializamos a 1. SI inicializasemos a 0 el producto de factorial * i sería 0
        System.out.println("Introduce un valor");
        n = scn.nextInt();
        System.out.println("El valor de e con precisión " +n +" es:");
        for ( double i = 1; i <= n; i++) { 
        factorial =  1 / (factorial * i)  + factorial; //-> este +1 del primer ciclo corresponderia al primer más 1 de la formula de e
        }
        e = factorial;
        System.out.println(e);
    }
}
