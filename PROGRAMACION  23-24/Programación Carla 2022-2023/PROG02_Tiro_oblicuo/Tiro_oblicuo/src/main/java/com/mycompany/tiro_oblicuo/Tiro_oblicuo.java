/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tiro_oblicuo;
import java.util.Scanner;
/**
 *
 * @author yo
 */
public class Tiro_oblicuo {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        byte opcion = 0;
        double velocidad = 0;
        double angulo = 0;
        double tiempo = 0; //en segundos
        double distancia = 0; // en metros
        double alcance_max = 0;
        final double g = 9.80665; // constante en m/s^2
        System.out.println("Escoge de entre las siguientes opciones:\nPulsa 1 si deseas calcular el alcance\nPulsa 2 si deseas calcular el ángulo");
        opcion = scn.nextByte();
        if (opcion == 1) {
            System.out.println("Introduce la velocidad en m/s");
            velocidad = scn.nextDouble();
            System.out.println("Introduce el ángulo de disparo");
            angulo = scn.nextDouble();
            tiempo = 2 * velocidad *  Math.sin(angulo) / g;
            distancia = velocidad + tiempo * Math.cos( angulo);
            System.out.println("El impacto a una velocidad de " +velocidad +" y con un ángulo de " +angulo +"º:\nTendrá un alcance de " +distancia +" metros\nSe producirá en " +tiempo +" segundos de tiempo");
        } else if (opcion == 2) {
            System.out.println("Introduce el alcance en metros");
            distancia = scn.nextDouble();
            System.out.println("Introduce la velocidad inicial en m/s");
            velocidad = scn.nextDouble();
            alcance_max = Math.pow(velocidad, 2) * Math.sin(2 * 45 / g); //el alcance máximo se calcula con un ángulo de 45º
            if (distancia <= alcance_max) {
                angulo = (distancia * g / Math.pow(velocidad, 2) / 2);
                System.out.println("Para que el impacto se produzca a " +distancia +"metros y con una velocidad inicial de " +velocidad +" m/s\nEl angulo será de " +angulo +"º");
            } else {
                System.out.println("La distancia introducida es superior al alcance máximo para la velocidad indicada");
            }
        }
    }
}
        
