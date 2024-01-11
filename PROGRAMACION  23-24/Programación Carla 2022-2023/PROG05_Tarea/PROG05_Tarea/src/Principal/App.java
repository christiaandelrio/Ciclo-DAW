/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Principal;

import java.util.Scanner;
import Principal.Vehiculo;
import Principal.Concesionario;
import Utiles.*;

/**
 *
 * @author Carla Portela Ubeira
 * Aplicaci�n para gesti�n de los veh�culos de un concesionario
 */
public class App {

    
    /**
     * @param args the command line arguments
     */
    Scanner scn = new Scanner(System.in);
    
    public static final int MAX_VEHICULOS=50;//Constante, n�mero m�ximo de veh�culos del array;
    public static final int MAX_CLIENTES=50;//Constante, n�mero m�ximo de clientes del array;
    public static final int MAX_VENTAS=50;//Constante, n�mero m�ximo de ventas del array;
    
    public static void main(String[] args) {
        
        Concesionario almacen = new Concesionario(MAX_VEHICULOS, MAX_CLIENTES, MAX_VENTAS);
        int opcion;
        String[] menu = {"1 - Nuevo veh�culo", "2 - Listar veh�culos", "3 - Buscar veh�culo", 
                         "4 - Modificar kms veh�culo", "5 - Vender Veh�culo", "6 - Salir"};
        
        do{
           opcion = Inputs.eligeOpcion("Concesionario ACME", menu, "123456"); 
            switch(opcion){
                case 1: 
                        Vehiculo v = nuevoVehiculo();
                        if (v != null) {
                          almacen.addVehiculo(v);  
                        } else {
                            System.out.println("No se ha a�adido el Nuevo Veh�culo");
                        }
                        break;
                case 2: listaVehiculo(almacen); break;
                case 3: busquedaVehiculo(almacen); break;
                case 4: cambioKms(almacen); break;
                case 5: ventaVehiculo(almacen); break;
            }
        } while (opcion !=6);
        System.out.println("�Hastapronto!");   
    } 
    
    public static Vehiculo nuevoVehiculo(){
        Vehiculo v = null;
        Inputs.subrayar("Datos del Nuevo Veh�culo");
        String matricula = Inputs.leeString("Introduzca la matr�cula: ");
        //Comprobamos la validez de la matr�cula
        if (Vehiculo.matriculaValida(matricula)){
            String marca = Inputs.leeString("Introduzca la marca: ");
            int precio = Inputs.getInt("Introduzca el precio: ");
            String descripcion = Inputs.leeString("Introduzca una breve descripci�n: ");
            int kms=0;
            v = new Vehiculo (marca, matricula, precio, descripcion, kms);
        } else {
            System.out.println("La matr�cula no es v�lida");
        }
        return v;
    }
    
    public static Cliente nuevoCliente() {
        Cliente c = null;
        Inputs.subrayar("Datos del Nuevo Cliente");
        String dni = Inputs.leeString("Introduzca el DNI: ");
        //Comprobamos la validez de la matr�cula
        if (Cliente.validaDni(dni)){
            String nombre = Inputs.leeString("Introduzca el nombre y apellidos: ");
            if(Cliente.validaNombre(nombre)) {
            c = new Cliente (nombre, dni);
            } else{
            System.out.println("El nombre y apellidos no son v�lidos");   
            }
        } else {
        System.out.println("El DNI no es v�lido");
        }
        return c;  
    }
    
    public static void listaVehiculo(Concesionario almacen) {
        Inputs.subrayar("Listado de Veh�culos");
        //Se obtiene mediante el metodo getVehiculos una lista del array veh�culos
        Vehiculo[] listaVehiculos = almacen.getVehiculos();
        for(int index=0; index<listaVehiculos.length; index++) {
            if(listaVehiculos[index]!=null){
                System.out.println(listaVehiculos[index]);
            }
        }
        Inputs.leeString("Pulsa una tecla para continuar");
    }
    
    public static void busquedaVehiculo(Concesionario almacen) {
        Inputs.subrayar("B�squeda de Veh�culos");
        //Solicita la entrada de una matr�cula
        String matricula = Inputs.leeString("Introduzca la matr�cula a buscar: ");
        Vehiculo v = almacen.getVehiculo(matricula);
        if(v!=null) {
            System.out.println(v);
        } else {
            System.out.println("No existe ning�n veh�culo con esa matr�cula");
        } 
    }
    
    public static void cambioKms(Concesionario almacen) {
        Inputs.subrayar("Modificar Kil�metros de Veh�culos");
        //Solicita la entrada de una matr�cula
        String matricula = Inputs.leeString("Introduzca la matr�cula del Veh�culo: ");
        Vehiculo v = almacen.getVehiculo(matricula);
        if(v!=null) {
            System.out.println(v);
            int kms = Inputs.getInt("Introduce el nuevo n�mero de Kil�metros: ");
            v.setKms(kms);
        } else {
            System.out.println("No existe ning�n veh�culo con esa matr�cula");
        }
    }
    
    public static void ventaVehiculo(Concesionario almacen) {
        Inputs.subrayar("Venta de Veh�culo");
        String dni = Inputs.leeString("Introduzca el DNI del propietario: ");
        Cliente cliente = almacen.getCliente(dni);
        if(cliente == null) {
            cliente = nuevoCliente();
        } else {
            String matricula = Inputs.leeString("Introduzca la matr�cula");
            Vehiculo vehiculo = almacen.getVehiculo(matricula);
            if(vehiculo!=null) {
                if(almacen.addCliente(cliente)){
                Venta venta = new Venta(cliente, vehiculo);
                almacen.addVenta(venta);
                }  
            }
        }
    }
   
}
