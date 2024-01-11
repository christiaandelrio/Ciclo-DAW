/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroautomatico;

import java.util.Scanner;

/**
 *
 * @author xavi
 */
public class Inputs {
    
    public static String pregunta(String question) {
        Scanner scn=new Scanner(System.in);
        
        System.out.print(question);
        return scn.nextLine();
    }

    public static float leefloat(String texto) {
        Scanner scn=new Scanner(System.in);
        
        System.out.print(texto);
        return Float.parseFloat(scn.nextLine());
    }
    
    public static int leeIntMaxMin(String texto,int min,int max) {
        Scanner scn=new Scanner(System.in);
        int r;
        
        do {
            System.out.print(texto);
            r=Integer.parseInt(scn.nextLine());   
            if ((r<min) || (r>max)) System.out.println("O número debe estar entre "+max+" e "+min);
        } while((r<min) || (r>max));
        return r;
    }
    

}
