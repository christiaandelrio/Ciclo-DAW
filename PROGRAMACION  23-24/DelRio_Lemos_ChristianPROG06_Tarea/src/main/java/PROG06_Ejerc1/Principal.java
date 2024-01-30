/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PROG06_Ejerc1;
import java.util.Scanner;
import java.time.LocalDate;
import PROG06_Ejerc1_util.Validaciones;
/**
 *
 * @author chris
 */
public class Principal {
    
    //Creamos un scanner
    static Scanner sc = new Scanner(System.in);
    
    private static int mostrarMenu(){
        System.out.println("1. Nuevo vehículo");
        System.out.println("2. Listar vehículos");
        System.out.println("3. Buscar vehículo");      
        System.out.println("4. Modificar kms vehículo");      
        System.out.println("5. Salir");        
        return sc.nextInt();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Definición de variables
        String matricula, marca, descripcion, nombrePropietario, dniPropietario;
        double numKilometros, precio;
        int diaMatriculacion, mesMatriculacion, anioMatriculacion, opcion;
        LocalDate fechaMatriculacion;
        Concesionario concesionario = new Concesionario();      
        boolean valido = true;
        
        
        do { //Según el valor de opción se hará una cosa u otra  
            
            opcion = mostrarMenu(); //Mostramos el menú y guardamos la opción introducida por el usuario
            sc.nextLine();
            
            switch(opcion){
                
                case 1:
                    //////////Introducción de datos para la creación del nuevo vehículo//////////
                    System.out.println("Introduce la marca del vehículo");
                    marca = sc.nextLine();
                    
                    System.out.println("Introduce la matrícula del vehículo");
                    matricula = sc.nextLine();
                    
                    System.out.println("Proporciona una breve descripción, por favor");
                    descripcion = sc.nextLine();
                    
                    System.out.println("Introduce el nombre del propietario");
                    nombrePropietario = sc.nextLine();
                    
                    System.out.println("Introduce ahora su DNI");
                    dniPropietario = sc.nextLine();
                    
                    System.out.println("Introduce el número de kilómetros del vehículo");
                    numKilometros = sc.nextDouble();
                    sc.nextLine();
                    
                    System.out.println("Introduce el precio");
                    precio = sc.nextDouble();
                    sc.nextLine();
                    
                    //Ahora vamos con la fecha, donde hay que pedir día, mes y año
                    System.out.println("Introduce el día de matriculación");
                    diaMatriculacion = sc.nextInt();
                    sc.nextLine(); //Para evitar errores de salto de línea 
                    
                    System.out.println("Introduce el mes de matriculación");
                    mesMatriculacion = sc.nextInt();
                    sc.nextLine();//Para evitar errores de salto de línea
                    
                    System.out.println("Introduce el año de matriculación");
                    anioMatriculacion = sc.nextInt();
                    sc.nextLine();//Para evitar errores de salto de línea 
                    
                    fechaMatriculacion = LocalDate.of(anioMatriculacion, mesMatriculacion, diaMatriculacion); 
   
                    //////////////Validaciones////////////
                    if(Validaciones.validarDNI(dniPropietario) != true){
                        System.out.println("DNI erróneo");
                        valido = false;
                    }
                    if(Validaciones.validarMatricula(matricula) != true){
                        System.out.println("Matrícula incorrecta");
                        valido = false;
                    }
                    
                    if(valido){
                        Vehiculo v = new Vehiculo(marca, matricula, numKilometros, fechaMatriculacion, descripcion, precio, nombrePropietario, dniPropietario);
                        
                        concesionario.insertarVehiculo(v);
                        System.out.println("Vehículo creado correctamente");
                        break;
                    }
                    
                    break;
                case 2:
                    concesionario.listaVehiculos();
                    break;
                case 3:
                    System.out.println("Introduce la matrícula del vehículo");
                    matricula = sc.nextLine();
                    
                    if(concesionario.buscaVehiculo(matricula) != null){
                        System.out.println("Vehículo con datos: "+concesionario.buscaVehiculo(matricula)+ "encontrado");
                    }else{
                        System.out.println("El vehículo solicitado no se encuentra en el concesionario");
                    }
                    break;
                case 4:
                    System.out.println("Introduce la matrícula del vehículo");
                    matricula = sc.nextLine();
                    System.out.println("Ahora introduce los kms a modificar");
                    double kms = sc.nextDouble();
                    
                    if(concesionario.buscaVehiculo(matricula) != null){
                        concesionario.actualizaKms(matricula, kms);
                    }else{
                        System.out.println("Vehículo no encontrado");
                    }
                    
                    break;
                case 5:
                    System.out.println("Adiós, hasta pronto!");
                    break;
            }
            
        } while (opcion != 5); //Si opción vale 5 se saldrá del bucle
    }
    
}
