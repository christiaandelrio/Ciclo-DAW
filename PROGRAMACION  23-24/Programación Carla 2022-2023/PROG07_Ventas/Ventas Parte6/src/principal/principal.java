/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package principal;

import java.util.Scanner;

/**
 *
 * @author yo
 */
public class principal {
    public static final Scanner scn=new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //Inicializamos la variable opción a 0;
       int op=0;
        do {
            System.out.println("Menú Principal\n--------------");
            System.out.println("1.- Gestión de Clientes");
            System.out.println("2.- Gestión de Artículos");
            System.out.println("3.- Gestión de Ventas");
            System.out.println("4.- Facturación ");
            System.out.println("5.- Salir");
            try {
                System.out.print("Elige una opción: ");
                op=scn.nextInt();
                switch(op) {
                    case 1: Clientes.start(); break;
                    case 2: Artigos.start(); break;
                    case 3: Ventas.start(); break;
                    case 4: Facturacion.start(); break;
                }
            } catch(NumberFormatException e) {}
        } while(op!=5);
    }  
}
