/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.prog03.Principal;

import com.prog03.figuras.Rectangulo;

/**
 *
 * @author yo
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Rectangulo rect1 = new Rectangulo();
        rect1.setBase(5);
        rect1.setAltura(10);
        float arearect1 = rect1.getArea(5, 10);
        System.out.println("-------Resultados para el primer rectángulo-------");
        String areaAlturarect1 = rect1.toString();
        System.out.println(areaAlturarect1);
        String cuadradorect1 = rect1.isCuadrado()? "Es cuadrado" : "No es cuadrado";
        System.out.println(cuadradorect1);
        Rectangulo rect2 = new Rectangulo();
        System.out.println("-------Resultados para el segundo rectángulo-------");
        rect2.setBase(7);
        rect2.setAltura(7);
        float arearect2 = rect2.getArea(7,7);
        String areaAlturarect2 = rect2.toString();
        System.out.println(areaAlturarect2);
        String cuadradorect2 = rect2.isCuadrado()? "Es cuadrado" : "No es cuadrado";
        System.out.println(cuadradorect2);   
    }
    
}
