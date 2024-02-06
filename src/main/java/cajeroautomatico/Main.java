/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cajeroautomatico;
import java.util.Scanner;
/**
 *
 * @author chris
 */
public class Main {
    
    static Scanner sc = new Scanner(System.in); //Defino un scanner para la entrada de datos
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creo una cuenta
        Cuenta ccc = new Cuenta(12345678, 12345, 100000, "Patricia");
        //Definición de variables
        int pin;
        
        //Pedimos al usuario el pin de la cuenta
        System.out.println("Bienvenido, introduce el PIN de tu cuenta:");
        pin = sc.nextInt();
        sc.nextLine();
        
        do{
            System.out.println("PIN erróneo, vuelve a intentarlo");
            pin = sc.nextInt();
            sc.nextLine();
        }while(pin != ccc.getPin());
    }
    
    
/*    public static int mostrarMenu(){
    
        return opcion;
    }*/
    
}
