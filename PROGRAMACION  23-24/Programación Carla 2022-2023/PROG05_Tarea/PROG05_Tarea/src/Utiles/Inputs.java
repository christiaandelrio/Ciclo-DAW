/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utiles;

import java.util.Scanner;

/**
 *
 * @author Carla Portela Ubeira
 * Métodos para los inputs de menús
 */
public class Inputs {
    private static Scanner scn;
    
    public static int eligeOpcion(String title, String[] opciones, String validChars) {
        String input;
        Integer opcion = null;
        if (scn == null) scn = new Scanner(System.in);
        do {
           System.out.println(title);
           for(int index = 0; index < opciones.length; index++) {
               System.out.println(opciones[index]);
           }
           System.out.print("Escoja una opción: ");
           input = scn.nextLine();
           opcion = charPosition(input.charAt(0), validChars);
           
        } while (opcion == null);
        return opcion;
    }
    
    private static Integer charPosition(char c, String validChars){
        Integer posicion;
        
        c = Character.toUpperCase(c);
        validChars = validChars.toUpperCase();
        posicion = 0;
        while ( posicion < validChars.length()){
            if(validChars.charAt(posicion) == c)return posicion + 1;
            posicion = posicion + 1;    
        }  
        return null;
    }
    public static String leeString (String title) {
        if(scn == null) scn = new Scanner(System.in);
        System.out.println(title);
        return scn.nextLine();
    }
    public static int getInt (String title) {
        if(scn == null) scn = new Scanner(System.in);
        System.out.println(title);
        String n = scn.nextLine();
        return Integer.parseInt (n);
    }
    
    public static String lineaString (int tamanio ) {
        String str = "";
        while (tamanio >0) {
            str+= "-";
            tamanio --;
        }
        return str;
    }
    public static void subrayar (String str) {
        int tamanio = str.length();
        System.out.println(str);
        System.out.println(lineaString(tamanio));
    }
}
