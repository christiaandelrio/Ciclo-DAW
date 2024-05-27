/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package paquetePrincipal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author sergi
 */
public class LecturaGuardadoTextos {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        try {
            ArrayList<String> palabras = new ArrayList<String>();
            BufferedWriter bw = new BufferedWriter(new FileWriter("fichero_salida.txt"));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("fichero_copiaseguridad.txt"));
            BufferedReader br = new BufferedReader(new FileReader("fichero_salida.txt"));
            /*
            //Es importante cuando trabajamos con .dat el orden, si hacemos el reader antes de haberse creado el archivo con writer, dará error.
            BufferedWriter bw = new BufferedWriter(new FileWriter("fichero_salida.dat"));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("fichero_copiaseguridad.dat"));
            BufferedReader br = new BufferedReader(new FileReader("fichero_salida.dat"));*/

            //1- Solicito las palabras y las guardo en el archivo "fichero_salida.txt":
            String palabra = ""; //Esto es importante inicializarlo.
            do {
                System.out.print("Introduce una palabra. 'exit' para salir: ");
                palabra = scan.nextLine();
                if (palabra != null && !palabra.equalsIgnoreCase("exit")) {
                    bw.write(palabra + "\n");
                }
            } while (!palabra.equalsIgnoreCase("exit"));

            bw.close(); //Importante cerrar el writer antes de usar el reader

            //2- Leo el contenido del archivo "fichero_salida.txt" añadiendo cada palabra al ArrayList:
            String linea = ""; //Esto es importante inicializarlo.

            while (linea != null) {
                linea = br.readLine();
                if (linea != null) {
                    palabras.add(linea);
                }
            }

            br.close();

            //3- Ordeno el ArrayList:
            Collections.sort(palabras);

            //4- Guardo en el archivo "fichero_copiaseguridad.txt" los elementos del ArrayList ordenados:
            for (String palabra1 : palabras) {
                bw2.write(palabra1 + "\n");
                System.out.println(palabra1);
            }

            bw2.close();

// TODO code application logic here
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
