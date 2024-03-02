/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
//Paquetes e imports
package chris.tarea6_sin_ayuda;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
/**
 *
 * @author chris
 */
public class Principal {
    
    static Scanner sc = new Scanner(System.in); //Creo un nuevo escáner para las entradas del usuario
    
    //Métodos útiles
    public static int mostrarMenu(){ 
        System.out.println("Escoge una opción, por favor:");
        
        System.out.println("1.Nuevo vehículo");
        System.out.println("2.Listar vehículos");
        System.out.println("3.Buscar vehículo");
        System.out.println("4.Actualizar kms vehículo");
        System.out.println("5.Salir");
                
        return sc.nextInt();
    }

    public static void main(String[] args) {
        
        //Definición de atributos
        int opcion, numRuedas, dia, mes, anio;
        double numKm, precio;
        boolean isRemolque = false,isAirbag = false,isSidecar = false;
        String marca,matricula,inputAirbag, inputRemolque, inputSidecar, descripcion, nombrePropietario, dniPropietario;
        LocalDate fechaMatriculacion;
        Vehiculo v;
        Concesionario c = new Concesionario();
        
        do{
            opcion = mostrarMenu();
            
            switch(opcion){
                
                case 1:
                    
                    System.out.println("Introduce la marca del vehículo:");
                    marca = sc.next();
                    
                    System.out.println("Introduce la matrícula del vehículo:");
                    matricula = sc.next();
                    
                    System.out.println("Introduce su kilometraje:");
                    numKm = sc.nextDouble();
                    
                    //Fecha de matriculación
                    System.out.println("Introduce el día de matriculación:");
                    dia = sc.nextInt();
                    System.out.println("Introduce el mes de matriculación:");
                    mes = sc.nextInt();
                    System.out.println("Introduce el año de matriculación:");
                    anio = sc.nextInt();
                    fechaMatriculacion = LocalDate.of(anio, mes, dia);
                    
                    System.out.println("Aporta una descripción del vehículo:");
                    descripcion = sc.next();
                    
                    System.out.println("Cual es su precio?");
                    precio = sc.nextDouble();
                    
                    System.out.println("Facilita el nombre del propietario:");
                    nombrePropietario = sc.next();
                    
                    System.out.println("Aporta el dni del propietario");
                    dniPropietario = sc.next();

                    //Submenú según el tipo de vehículo
                    System.out.println("Selecciona el tipo de vehículo:");
                    System.out.println("1.Coche");
                    System.out.println("2.Camión");
                    System.out.println("3.Moto");
                    
                    int tipoVehiculo = sc.nextInt(); 
                    
                    switch(tipoVehiculo){
                        
                        case 1:
                            System.out.println("Indica el número de ruedas del coche");
                            numRuedas = sc.nextInt();
                            System.out.println("Indica si tiene airbag(si o no)");
                            inputAirbag = sc.next();

                            if(inputAirbag.equalsIgnoreCase("si")){
                                isAirbag = true;
                            }else if(inputAirbag.equalsIgnoreCase("no")){
                                isAirbag = false;
                            }else{
                                System.out.println("Entrada no válida, error");
                            }
                            
                            v = new Coche(numRuedas, isAirbag, marca, matricula, descripcion, nombrePropietario, dniPropietario, precio, numKm, fechaMatriculacion);
        
                            c.insertarVehiculo(v);
                            
                            break;
                        case 2:
                            System.out.println("Indica el número de ruedas del camión");
                            numRuedas = sc.nextInt();
                            System.out.println("El camión tiene remolque? (si o no)");
                            inputRemolque = sc.next();
                            
                            if(inputRemolque.equalsIgnoreCase("si")){
                                isRemolque = true;
                            }else if(inputRemolque.equalsIgnoreCase("no")){
                                isRemolque = false;
                            }else{
                                System.out.println("Entrada no válida, error");
                            }
                            
                            v = new Camion(numRuedas, isRemolque, marca, matricula, descripcion, nombrePropietario, dniPropietario, precio, numKm, fechaMatriculacion);
                            c.insertarVehiculo(v);
                            break;
                        case 3:
                            System.out.println("Tiene sidecar la moto");
                            inputSidecar = sc.next();
                            
                            if(inputSidecar.equalsIgnoreCase("si")){
                                isSidecar = true;
                            }else if(inputSidecar.equalsIgnoreCase("no")){
                               isSidecar = false;
                            }else{
                                System.out.println("Entrada no válida, error");
                            }
                            
                            v = new Moto(isSidecar, marca, matricula, descripcion, nombrePropietario, dniPropietario, precio, numKm, fechaMatriculacion);
                            c.insertarVehiculo(v);
                            break;
                        
                        
                    }
                    
                    
                    
                    break;
                    
                case 2://Muestra una lista de los vehículos del concesionario por pantalla            
                    c.listaVehiculos();
                    break;
                    
                case 3://Dada una matrícula determinada, muestra su vehículo correspondiente
                    
                    System.out.println("Introduce la matrícula del vehículo que quieres encontrar");
                    matricula = sc.next(); //Almacena la matrícula introducida por el usuario
                    
                    if(c.buscaVehiculo(matricula) != null){
                        System.out.println("Vehículo encontrado "+c.buscaVehiculo(matricula));
                    }else{
                        System.out.println("No hay vehículos con esa matrícula en el concesionario");
                    }
                    
                    break;
            }
            
        }while(opcion != 5);
    }
}
