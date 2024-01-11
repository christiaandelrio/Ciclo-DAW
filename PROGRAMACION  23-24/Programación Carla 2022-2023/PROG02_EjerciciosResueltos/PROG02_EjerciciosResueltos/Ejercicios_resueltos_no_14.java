/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_14;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_14 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        float Sub_total = 0;
        float Total = 0;
        float Precio_Producto = 0;
        float Codigo_Producto = 0;
        System.out.println("Código de producto");
        Codigo_Producto = scn.nextInt();
        while (Codigo_Producto != 0) {
            System.out.println("Precio del producto");
            Precio_Producto = scn.nextFloat();
            Sub_total = Sub_total + Precio_Producto;
            System.out.println("Producto: " +Codigo_Producto +"\nPrecio: " +Precio_Producto);
            System.out.println("Código de producto");
            Codigo_Producto = scn.nextInt();
        }
    float IVA = Sub_total * 0.15f;
    Total = Sub_total + IVA;
    System.out.println("Precio total con IVA: " +Total +" euros");
    System.out.println("IVA aplicado: " +IVA +" euros");
    }
}
