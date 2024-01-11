/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_16;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_16 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int BC = 0; 
        int BV = 0;
        int BD = 0;
        int BC2 = 0;
        int BM = 0;
        int C = 0; // //C = N
        int N = 0; // el valor dado
        System.out.println("Introduce un  valor");
        N = scn.nextInt();
        while (N > 50000) { //Por cada 50000 N, 1 BC
            BC = N / 50000;
            N = N - BC * 50000;    
        }
        while (N > 20000) { //Por cada 20000 N, 1 BV, y así sucesivamente
            BV = N / 20000;
            N = N - BV * 20000;    
        }
        while (N > 10000) {
            BD = N / 10000;
            N = N - BD * 10000;    
        }
        while (N > 5000) {
            BC2 = N / 5000;
            N = N - BC2 * 5000;    
        }
        while (N > 1000) {
            BM = N / 1000;
            N = N - BM * 1000;    
        }
        System.out.println("BM: " +BM +"\nBV: " +BV +"\nBD: " +BD +"\nBC2: " +BC2 +"\nBM: " +BM +"\nC: " +N);
    } //Finalmente imprimimos los BM, BV, BD, BC2; BM y C contenidos en el valor N
}
