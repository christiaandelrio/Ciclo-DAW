/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_1a;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_1a {

    public static void main(String[] args) {
      Scanner scn=new Scanner(System.in);
      int A;
      int B;
      System.out.println("Introduce un valor");
      A = scn.nextInt();
      System.out.println("Introduce otro valor distinto al anterior");
      B = scn.nextInt();
      while (A == B) {
      System.out.println("Los valores son iguales\n¡Inténtalo de nuevo!");
      System.out.println("Introduce un valor");
      A = scn.nextInt();
      System.out.println("Introduce otro valor distinto al anterior");
      B = scn.nextInt();
      }
      if(A > B) {
      System.out.println(A +" es el mayor de los valores");
      } else {
      System.out.println(B +" es el mayor de los valores");
      }
    }
}
