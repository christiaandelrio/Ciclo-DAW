package Inputs;

import java.util.Scanner;

/**
 *
 * @author xavi
 */
public class InputMethod {
    private static Scanner scn;
    
    
    public static String getString(String title) {
        if (scn==null) scn=new Scanner(System.in);
        System.out.print(title);
        return scn.nextLine();
    }
    
    public static int getOpcion(String title,String[] options,String validchars) {
        String input;
        Integer opt;
        
        if (scn==null) scn=new Scanner(System.in);
        do {
            System.out.println(title);
            System.out.println(linea(title.length()));
            for(int idx=0;idx<options.length;idx++) System.out.println(options[idx]);
            System.out.print("\nElixe Opcion: ");
            input=scn.nextLine();
            opt=charPosition(input.charAt(0),validchars);
        } while(opt==null);
        return opt;
    }
    
    
    /**
     * EXERCICIO: Sería mellor facelo con StringBuilder
     * @param len
     * @return 
     */
    public static String linea(int len) {
        String l="";
        
        while(len>0) {
            l+="-";
            len--;
        }
        return l;
    }
    
    public static void showSuliñado(String str) {
        int len=str.length();
        System.out.println(str);
        System.out.println(linea(len));
    }
    
    
    private static Integer charPosition(char l,String validchars) {
        Integer pos;
        
        l=Character.toUpperCase(l);
        validchars=validchars.toUpperCase();
        pos=0;
        while(pos<validchars.length()) {
            if (validchars.charAt(pos)==l) return pos+1;
            pos=pos+1;
        } 
        return null;
    }

    public static int getInt(String title) {
        if (scn==null) scn=new Scanner(System.in);
        System.out.print(title);
        String line=scn.nextLine();
        return Integer.parseInt(line);
    }

}
