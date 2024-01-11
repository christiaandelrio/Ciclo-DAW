/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introexceptions;

import java.util.Scanner;

/**
 *
 * @author xavi
 */
public class IntroExceptions {
   
    
    /**
     * Busca a primeira posición na que se atopa a letra c no String str
     * @param c - Letra a buscar
     * @param str - String onde buscamos
     * @return - A primeira posición da letra ou -1 si non se atopa
     */
    int charPos(char c, String str) {
        int len=str.length();
        int idx=0;
        while(idx!=len) {
            if (str.charAt(idx)==c) return idx;
        }
        return -1;
    }
    
    int divide(int dividendo, int divisor) throws Exception {
        int resultado;
        
        if (divisor==0) throw new Exception("Non é posible dividir entre 0");
        resultado=dividendo/divisor;
        return resultado;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        IntroExceptions ie=new IntroExceptions();
        
        int dividendo;
        int divisor;
        
        System.out.print("Dividendo: ");
        dividendo=Integer.parseInt(scn.nextLine());
        
        System.out.print("Divisor: ");
        divisor=Integer.parseInt(scn.nextLine());
        try {
            System.out.println("Resultado: "+ie.divide(dividendo, divisor));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
