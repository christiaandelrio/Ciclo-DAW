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
 * Aplicación para gestión de los vehículos de un concesionario
 */
public class App {

    
    /**
     * @param args the command line arguments
     */
    Scanner scn = new Scanner(System.in);
    
    public static final int MAX_VEHICULOS=50;//Constante, número máximo de vehículos del array;
    public static final int MAX_CLIENTES=50;//Constante, número máximo de clientes del array;
    public static final int MAX_VENTAS=50;//Constante, número máximo de ventas del array;
    
    public static void main(String[] args) {
        
        Concesionario almacen = new Concesionario(MAX_VEHICULOS, MAX_CLIENTES, MAX_VENTAS);
        int opcion;
        String[] menu = {"1 - Nuevo vehículo", "2 - Listar vehículos", "3 - Buscar vehículo", 
                         "4 - Modificar kms vehículo", "5 - Vender Vehículo", "6 - Salir"};
        
        do{
           opcion = Inputs.eligeOpcion("Concesionario ACME", menu, "123456"); 
            switch(opcion){
                case 1: 
                        Vehiculo v = nuevoVehiculo();
                        if (v != null) {
                          almacen.addVehiculo(v);  
                        } else {
                            System.out.println("No se ha añadido el Nuevo Vehículo");
                        }
                        break;
                case 2: listaVehiculo(almacen); break;
                case 3: busquedaVehiculo(almacen); break;
                case 4: cambioKms(almacen); break;
                case 5: ventaVehiculo(almacen); break;
            }
        } while (opcion !=6);
        System.out.println("¡Hastapronto!");   
    } 
    
    public static Vehiculo nuevoVehiculo(){
        Vehiculo v = null;
        Inputs.subrayar("Datos del Nuevo Vehículo");
        String matricula = Inputs.leeString("Introduzca la matrícula: ");
        //Comprobamos la validez de la matrícula
        if (Vehiculo.matriculaValida(matricula)){
            String marca = Inputs.leeString("Introduzca la marca: ");
            int precio = Inputs.getInt("Introduzca el precio: ");
            String descripcion = Inputs.leeString("Introduzca una breve descripción: ");
            int kms=0;
            v = new Vehiculo (marca, matricula, precio, descripcion, kms);
        } else {
            System.out.println("La matrícula no es válida");
        }
        return v;
    }
    
    public static Cliente nuevoCliente() {
        Cliente c = null;
        Inputs.subrayar("Datos del Nuevo Cliente");
        String dni = Inputs.leeString("Introduzca el DNI: ");
        //Comprobamos la validez de la matrícula
        if (Cliente.validaDni(dni)){
            String nombre = Inputs.leeString("Introduzca el nombre y apellidos: ");
            if(Cliente.validaNombre(nombre)) {
            c = new Cliente (nombre, dni);
            } else{
            System.out.println("El nombre y apellidos no son válidos");   
            }
        } else {
        System.out.println("El DNI no es válido");
        }
        return c;  
    }
    
    public static void listaVehiculo(Concesionario almacen) {
        Inputs.subrayar("Listado de Vehículos");
        //Se obtiene mediante el metodo getVehiculos una lista del array vehículos
        Vehiculo[] listaVehiculos = almacen.getVehiculos();
        for(int index=0; index<listaVehiculos.length; index++) {
            if(listaVehiculos[index]!=null){
                System.out.println(listaVehiculos[index]);
            }
        }
        Inputs.leeString("Pulsa una tecla para continuar");
    }
    
    public static void busquedaVehiculo(Concesionario almacen) {
        Inputs.subrayar("Búsqueda de Vehículos");
        //Solicita la entrada de una matrícula
        String matricula = Inputs.leeString("Introduzca la matrícula a buscar: ");
        Vehiculo v = almacen.getVehiculo(matricula);
        if(v!=null) {
            System.out.println(v);
        } else {
            System.out.println("No existe ningún vehículo con esa matrícula");
        } 
    }
    
    public static void cambioKms(Concesionario almacen) {
        Inputs.subrayar("Modificar Kilómetros de Vehículos");
        //Solicita la entrada de una matrícula
        String matricula = Inputs.leeString("Introduzca la matrícula del Vehículo: ");
        Vehiculo v = almacen.getVehiculo(matricula);
        if(v!=null) {
            System.out.println(v);
            int kms = Inputs.getInt("Introduce el nuevo número de Kilómetros: ");
            v.setKms(kms);
        } else {
            System.out.println("No existe ningún vehículo con esa matrícula");
        }
    }
    
    public static void ventaVehiculo(Concesionario almacen) {
        Inputs.subrayar("Venta de Vehículo");
        String dni = Inputs.leeString("Introduzca el DNI del propietario: ");
        Cliente cliente = almacen.getCliente(dni);
        if(cliente == null) {
            cliente = nuevoCliente();
        } else {
            String matricula = Inputs.leeString("Introduzca la matrícula");
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
