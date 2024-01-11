/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog07_tarea.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author 
 */
public class Util_ES {
    
    //---------------------------------------------------------------        
    // MÉTODO lecturaTeclado: Captura de una cadena de teclado
    //---------------------------------------------------------------      
    public static String lecturaTeclado () throws Exception {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            return line;
        }
        catch (IOException e) {
            throw e;

        }

    }

    //---------------------------------------------------------------        
    // MÉTODO pulsacionTecla: Captura de un tecla de teclado
    //---------------------------------------------------------------      
    public static int pulsacionTecla () throws Exception {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            int c = reader.read();
            return c;

        }
        catch (IOException e) {
            throw e;

        }

    }


    //---------------------------------------------------------------        
    // MÉTODO pulsacionTecla: Captura de un tecla numérica de teclado
    // (opción del menú)
    //---------------------------------------------------------------      
    public static int leeOpcion() {

            int nOpcion = 10 ;

            try {

                // Leer desde teclado la opción
                String cOpcion = lecturaTeclado();
                nOpcion = Integer.parseInt(cOpcion) ;
             } catch(NumberFormatException e) {
                  System.err.println("NO ES UN NÚMERO VÁLIDO: Vuelve a intentarlo.");}
               catch(IOException ex) {
                   System.err.println("NO ES UN NÚMERO VÁLIDO: Vuelve a intentarlo.");}
               catch(Exception exc) {
                   System.err.println("NO ES UN NÚMERO VÁLIDO: Vuelve a intentarlo.");}

            return (nOpcion) ;
        }
}
