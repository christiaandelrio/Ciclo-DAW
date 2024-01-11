package principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {
    private static final Scanner SCN=new Scanner(System.in);

    public static void waitEnter() {
        System.out.print("Pulsa Enter para continuar....");
        SCN.nextLine();
    }
    
    public static boolean confirm(String msg,char accept) {
        boolean ret=false;
        System.out.print(msg);
        String c=SCN.nextLine();
        if (!"".equals(c)) ret=(c.toUpperCase().charAt(0)==Character.toUpperCase(accept));
        return ret;
    }
    
    public static String getLine() {
        return SCN.nextLine();
    }
    
    public static String getString(String msg,char cancel) throws CancelException {
        System.out.print(msg+" ("+cancel+" para cancelar):");
        
        String str=SCN.nextLine();
        if ("".equals(str)) return null;
        if (str.charAt(0)==cancel) throw new CancelException();
        return str;
    }
    
    public static Integer getInt(String msg,int min, int max,char cancel) throws CancelException {
        int num=0;
        String str;
        boolean ok=false;
        
        do {
            try {
                System.out.print(msg+" ("+cancel+" para cancelar):");
                str=SCN.nextLine();
                if ("".equals(str)) return null;
                if (str.charAt(0)==cancel) throw new CancelException();
                num=Integer.parseInt(str);
                if ((num<min)||(num>max)) 
                    System.out.println("O numero debe ser >= "+min+" e <= "+max);
                else
                    ok=true;
            } catch(NumberFormatException e) {
                System.out.println("ERROR: "+e.getMessage());
            }
        } while(!ok);
        return num;   
    }
    
    public static Double getDouble(String msg,double min, double max,char cancel) throws CancelException {
        double num=0;
        String str;
        boolean ok=false;
        
        do {
            try {
                System.out.print(msg+" ("+cancel+" para cancelar):");
                str=SCN.nextLine();
                if ("".equals(str)) return null;
                if (str.charAt(0)==cancel) throw new CancelException();
                num=Double.parseDouble(str);
                if ((num<min)||(num>max)) 
                    System.out.println("O numero debe ser >= "+min+" e <= "+max);
                else
                    ok=true;
            } catch(NumberFormatException e) {
                System.out.println("ERROR: "+e.getMessage());
            }
        } while(!ok);
        return num;   
    }
    
    
    public static LocalDate getDate(String msg,char cancel) throws CancelException {
        LocalDate date=null;
        DateTimeFormatter format=DateTimeFormatter.ofPattern("d-M-yyyy");
        do {
            try {
                System.out.print(msg+" ("+cancel+" para cancelar):");
                String strdate=SCN.nextLine();
                if ("".equals(strdate)) return null;
                if (strdate.charAt(0)==cancel) throw new CancelException();
                strdate=strdate.replace("/","-");
                date=LocalDate.parse(strdate,format);
            } catch(DateTimeParseException e) {
                System.out.println("ERROR: "+e.getMessage());
            } 
        } while(date==null);
        return date;  
    }
}
