/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_3;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_3 {

    public static void main(String[] args) {
        int N = 0;
        int Suma = 0;
        while (N <= 10) { //el numero máximo que va a alcanzar N
        Suma = (int) Suma + N; //Asignamos acumulador
        N = (int) (N + 1);  //Asignamos contador   
        }
        System.out.println("La suma de los números comprendidos entre 1 y 10 es " +Suma);
    }
}

        
        
        
