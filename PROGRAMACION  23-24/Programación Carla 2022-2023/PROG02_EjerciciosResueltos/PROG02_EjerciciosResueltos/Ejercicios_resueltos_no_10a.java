/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_10a;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_10a {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int Entradas = 0; //número de entradas
        int descuento = 0; //descuento a realizar
        final int precioEnt = 30;// ponemos que la entrada vale 30 euros y es constante
        int pago = 0; //precio a pagar por las entradas
        int calcdesc = 0; //descuento a realizar en el precio
        System.out.println("¿Cuántas entradas desea comprar?");
        Entradas = scn.nextInt();
        if ((Entradas == 0) && (Entradas <=4)) {
            descuento = 0;
            calcdesc = (precioEnt * descuento) / 100 ; 
            pago = (Entradas *  precioEnt) - calcdesc;
            System.out.println("Al comprar " +Entradas + " el descuento es del " +descuento + " % y el precio total a abonar es " +pago +" euros");
        } else if ((Entradas == 1) && (Entradas <=4)) {
            descuento = 0;
            calcdesc = (precioEnt * descuento) / 100 ; 
            pago = (Entradas *  precioEnt) - calcdesc;
            System.out.println("Al comprar " +Entradas + " el descuento es del " +descuento + " % y el precio total a abonar es " +pago +" euros");
        } else if ((Entradas == 2) && (Entradas <=4)) {
            descuento = 10;
            calcdesc = (precioEnt * descuento) / 100 ; 
            pago = (Entradas *  precioEnt) - calcdesc;
            System.out.println("Al comprar " +Entradas + " el descuento es del " +descuento + " % y el precio total a abonar es " +pago +" euros");
        } else if ((Entradas == 3) && (Entradas <= 4)) {
            descuento = 15;
            calcdesc = (precioEnt * descuento) / 100 ; 
            pago = (Entradas *  precioEnt) - calcdesc;
            System.out.println("Al comprar " +Entradas + " el descuento es del " +descuento + " % y el precio total a abonar es " +pago +" euros");
        } else if ((Entradas == 4) && (Entradas <= 4)) {
            descuento = 20;
            calcdesc = (precioEnt * descuento) / 100 ; 
            pago = (Entradas *  precioEnt) - calcdesc;
            System.out.println("Al comprar " +Entradas + " el descuento es " +descuento + " % y el precio total a abonar es " +pago +" euros");
        } else {
            System.out.println("!No se pueden comprar más de 4 entradas!");
        }
    }
}
