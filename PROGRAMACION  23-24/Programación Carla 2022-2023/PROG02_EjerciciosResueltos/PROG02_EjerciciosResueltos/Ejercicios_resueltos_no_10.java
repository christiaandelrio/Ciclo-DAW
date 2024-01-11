/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ejercicios_resueltos_no_10;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Ejercicios_resueltos_no_10 {

    public static void main(String[] args) {
                Scanner scn = new Scanner(System.in);
        int HE = 0; //hora de entrada en formato militar, 1800 equivaldria a 18:00
        int HS = 0; //hora de salida
        int HEst = 0; //horas de estadia
        int Pago = 0; 
        System.out.println("Introduzca hora de entrada");
        HE = scn.nextInt();
        System.out.println("Introduzca hora de salida");
        HS = scn.nextInt();
        HEst = (HS - HE);
        // se divide entre 100 por el formato militar usado
        //Es HS - HE porque sino nos daría un numero negativo
        if ((HEst > 0) && (HEst <= 100)) { // 100 sería una hora en este formato
            HEst = 1; // como las fracciones son horas completas; entre 1 minuto y una hora sería igual a una hora
            Pago = HEst * 1000;
            System.out.println("Pago a realizar: " +Pago +" bolívares");
        } else if ((HEst > 0) && (HEst > 100)) { //en formato militar 1000 equivaldria a 1 h
            Pago = 1000 + (Math.round(HEst /100))  * 600; //la primer hora cuesta 1000 bolívares y las siguientes 600
            //redondeamos con Math.round para no perder los valores por encima de cero y menores a 1
            System.out.println("Pago a realizar: " +Pago +" bolívares");
        } else {
            Pago = 0;
            System.out.println("Pago a realizar: " +Pago +" bolívares");
        } 
    }
}
// -Errores-
// No se inicializó la variable horas de estadia al inicio
// Al contabilizarse como hora entera las fracciones de hora 
//  no es necesario inicializar la variable fracciones de hora
// Para calcular las horas de estadia, utilizando un formato militar para las horas
//  es necesario dividir el resultado de la resta de HS y HE entre 100 
