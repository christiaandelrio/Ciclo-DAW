/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog05_Ejerc1;
import java.time.LocalDate;//importamos las utilidades que vamos  a necesitar de la API de Java y las que hemos creado 
import java.time.Month;
import java.util.Scanner;
import PROG05_Ejerc1_util.Validaciones;
import PROG05_Ejerc1_util.DNI;
/**
 *
 * @author Carla Portela Ubeira
 * Este es una clase principal que nos va a permitir crear un nuevo vehículo y gestionarlo
 */
public class Principal {
    //creamos un nuevo objeto de la clase Scanner que hemos importado
    static Scanner scn = new Scanner(System.in);
    //implementamos un método que nos mostrará el menu principal y las opciones
    public static int menuPrincipal(){
        System.out.println("Escoga una opción:");
        System.out.println("1.Nuevo Vehículo");
        System.out.println("2.Ver Matrícula");
        System.out.println("3.Ver Kilómetros");
        System.out.println("4.Actualizar Kilómetros");
        System.out.println("5.Ver años de antigüedad");
        System.out.println("6.Mostrar Propietario");
        System.out.println("7.Mostrar Descripción");
        System.out.println("8.Mostrar Precio");
        System.out.println("9. Salir");
        int opcion = scn.nextInt();
        scn.nextInt();
        return opcion;
    }
    public static void main(String[] args) {
        //Definimos las variables del método principal, que seran todos aquellos datos que vamos a solicitar
        int opcion;
        String marca, matricula, descripcion, nombrePropietario, dniPropietario;
        double numKilometros;
        int diaMatriculacion, mesMatriculacion, anioMatriculacion, precio;
        LocalDate fechaMatriculacion;
        boolean valido = true;
        //al principio el objeto v1 estará vacio. Lo inicializamos en valor null;
        Vehiculo v1 = null;
        //mediante un bucle do while, nos aseguramos que el bucle se produce por lo menos una vez
        do {
           //establecemos que la variable opción será aquella retornada por el método menuPrincipal
           opcion = menuPrincipal();
           //mediante un bucle switch establecemos todas las posibilidades 
           switch (opcion) {
            case 1:
                //en caso de introducir 1, se solicitan todos los datos para las variables que crearan el nuevo objeto vehículo
                System.out.println("Introduzca la marca");
                marca = scn.nextLine();
                System.out.println("Introduzca la matrícula");
                matricula = scn.nextLine();
                System.out.println("Introduzca el número de kilómetros");
                numKilometros = scn.nextDouble();
                System.out.println("Introduzca el día de matriculación");
                diaMatriculacion  = scn.nextInt();
                scn.nextLine();  //después de leer un entero, se lee una nueva línea para evitar errores de salto de línea
                System.out.println("Introduzca el mes de matriculación");
                mesMatriculacion  = scn.nextInt();
                scn.nextLine();  //después de leer un entero, se lee una nueva línea para evitar errores de salto de línea
                System.out.println("Introduzca el año de matriculación");
                anioMatriculacion  = scn.nextInt();
                scn.nextLine();  //después de leer un entero, se lee una nueva línea para evitar errores de salto de línea
                fechaMatriculacion = LocalDate.of(anioMatriculacion, mesMatriculacion, diaMatriculacion);
                System.out.println("Introduzca la descripción");
                descripcion = scn.nextLine();
                System.out.println("Introduzca el precio");
                precio = scn.nextInt();
                scn.nextLine();  //después de leer un entero, se lee una nueva línea para evitar errores de salto de línea
                System.out.println("Introduzca el nombre del propietario");
                nombrePropietario = scn.nextLine();
                System.out.println("Introduzca el DNI del propietario");
                dniPropietario = scn.nextLine();
                //antes de crear el objeto realizamos las siguientes validaciones
                //de que la fecha de matriculación sea anterior a la fecha actual
                if(!Validaciones.comparaFecha(fechaMatriculacion)) {
                    valido = false;
                    System.out.println("Los datos de la fecha de matriculación son incorrectos");
                //del DNI con expcepciones
                try {
                        Validaciones.validaDNI(dniPropietario);
                    } catch (Exception e) {
                        valido = false;
                        System.out.println("El formato del DNI no es correcto");
                    }
                //Si las validaciones son correctas, instanciamos el vehículo.
                    if (valido) {
                        v1 = new Vehiculo(marca, matricula, numKilometros, fechaMatriculacion, descripcion, precio, nombrePropietario, dniPropietario);
                        System.out.println ("El vehículo ha sido creado");
                    }
                    break;
                }   
            case 2:
                //si elige la opción 2 se visualizará la matrícula en caso de que el objeto vehículo se haya creado
                if (v1 != null) {
                        System.out.println("La matrícula del vehículo es: " + v1.getMatricula());
                    } else {
                        System.out.println("No existe el vehículo.");
                    }
                    break;

                case 3:
                    //si elige esta opción, se mostrarán el número de Km del vehículo
                    if (v1 != null) {
                        System.out.println("El número de kilómtros del Vehículo es: " + v1.getKm());
                    } else {
                        System.out.println("No existe el vehículo.");
                    }

                    break;
                case 4:
                    //si elige esta opción, se actualizarán el número de Km mediante el método de la calse Vehículo, kmActuales
                    if (v1 != null) {
                        System.out.println("Introduce el nuevo número de kilómetros");
                        int kmNuevos = scn.nextInt();
                        if (kmNuevos > 0) {
                            v1.kmActuales(kmNuevos);
                        }
                    } else {
                        System.out.println("No existe el vehículo.");
                    }
                    break;

                case 5:
                    //si elige esta opción, se mostrarán los años que tiene el vehículo
                    if (v1 != null) {
                        System.out.println("El vehículo tiene: " + v1.getAnios() + " años");
                    } else {
                        System.out.println("No existe el vehículo.");
                    }
                case 6:
                    //si elige esta opción se mostrará el propietario del vehículo y el DNI
                    if (v1 != null) {
                        System.out.println("El propietario del vehículo es: " + v1.getNombre() + ", con DNI " + v1.getDni());
                    } else {
                        System.out.println("No existe el vehículo.");
                    }
                case 7:
                    //si elige esta opción, se mostrará la descripción del vehículo
                    if (v1 != null) {
                        System.out.println("La descripción del vehículo es: " + v1.getDescripcion());
                    } else {
                        System.out.println("No existe el vehículo.");
                    }
                case 8:
                    //si elige esta opción, se mostrará el precio del vehículo
                    if (v1 != null) {
                        System.out.println("El precio del vehículo es: " + v1.getPrecio() + "?");
                    } else {
                        System.out.println("No existe el vehículo.");
                    }
                case 9: {
                    //si elige esta opción, saldrá del programa
                    System.out.println("Hasta pronto");
                    break;
                }
            }
        } while (opcion != 9);//este bucle se repetirá siempre y cuando la opcion que se elija sea diferente a 9
    }
        
    
    
}
