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
 * Este es una clase principal que nos va a permitir crear un nuevo veh�culo y gestionarlo
 */
public class Principal {
    //creamos un nuevo objeto de la clase Scanner que hemos importado
    static Scanner scn = new Scanner(System.in);
    //implementamos un m�todo que nos mostrar� el menu principal y las opciones
    public static int menuPrincipal(){
        System.out.println("Escoga una opci�n:");
        System.out.println("1.Nuevo Veh�culo");
        System.out.println("2.Ver Matr�cula");
        System.out.println("3.Ver Kil�metros");
        System.out.println("4.Actualizar Kil�metros");
        System.out.println("5.Ver a�os de antig�edad");
        System.out.println("6.Mostrar Propietario");
        System.out.println("7.Mostrar Descripci�n");
        System.out.println("8.Mostrar Precio");
        System.out.println("9. Salir");
        int opcion = scn.nextInt();
        scn.nextInt();
        return opcion;
    }
    public static void main(String[] args) {
        //Definimos las variables del m�todo principal, que seran todos aquellos datos que vamos a solicitar
        int opcion;
        String marca, matricula, descripcion, nombrePropietario, dniPropietario;
        double numKilometros;
        int diaMatriculacion, mesMatriculacion, anioMatriculacion, precio;
        LocalDate fechaMatriculacion;
        boolean valido = true;
        //al principio el objeto v1 estar� vacio. Lo inicializamos en valor null;
        Vehiculo v1 = null;
        //mediante un bucle do while, nos aseguramos que el bucle se produce por lo menos una vez
        do {
           //establecemos que la variable opci�n ser� aquella retornada por el m�todo menuPrincipal
           opcion = menuPrincipal();
           //mediante un bucle switch establecemos todas las posibilidades 
           switch (opcion) {
            case 1:
                //en caso de introducir 1, se solicitan todos los datos para las variables que crearan el nuevo objeto veh�culo
                System.out.println("Introduzca la marca");
                marca = scn.nextLine();
                System.out.println("Introduzca la matr�cula");
                matricula = scn.nextLine();
                System.out.println("Introduzca el n�mero de kil�metros");
                numKilometros = scn.nextDouble();
                System.out.println("Introduzca el d�a de matriculaci�n");
                diaMatriculacion  = scn.nextInt();
                scn.nextLine();  //despu�s de leer un entero, se lee una nueva l�nea para evitar errores de salto de l�nea
                System.out.println("Introduzca el mes de matriculaci�n");
                mesMatriculacion  = scn.nextInt();
                scn.nextLine();  //despu�s de leer un entero, se lee una nueva l�nea para evitar errores de salto de l�nea
                System.out.println("Introduzca el a�o de matriculaci�n");
                anioMatriculacion  = scn.nextInt();
                scn.nextLine();  //despu�s de leer un entero, se lee una nueva l�nea para evitar errores de salto de l�nea
                fechaMatriculacion = LocalDate.of(anioMatriculacion, mesMatriculacion, diaMatriculacion);
                System.out.println("Introduzca la descripci�n");
                descripcion = scn.nextLine();
                System.out.println("Introduzca el precio");
                precio = scn.nextInt();
                scn.nextLine();  //despu�s de leer un entero, se lee una nueva l�nea para evitar errores de salto de l�nea
                System.out.println("Introduzca el nombre del propietario");
                nombrePropietario = scn.nextLine();
                System.out.println("Introduzca el DNI del propietario");
                dniPropietario = scn.nextLine();
                //antes de crear el objeto realizamos las siguientes validaciones
                //de que la fecha de matriculaci�n sea anterior a la fecha actual
                if(!Validaciones.comparaFecha(fechaMatriculacion)) {
                    valido = false;
                    System.out.println("Los datos de la fecha de matriculaci�n son incorrectos");
                //del DNI con expcepciones
                try {
                        Validaciones.validaDNI(dniPropietario);
                    } catch (Exception e) {
                        valido = false;
                        System.out.println("El formato del DNI no es correcto");
                    }
                //Si las validaciones son correctas, instanciamos el veh�culo.
                    if (valido) {
                        v1 = new Vehiculo(marca, matricula, numKilometros, fechaMatriculacion, descripcion, precio, nombrePropietario, dniPropietario);
                        System.out.println ("El veh�culo ha sido creado");
                    }
                    break;
                }   
            case 2:
                //si elige la opci�n 2 se visualizar� la matr�cula en caso de que el objeto veh�culo se haya creado
                if (v1 != null) {
                        System.out.println("La matr�cula del veh�culo es: " + v1.getMatricula());
                    } else {
                        System.out.println("No existe el veh�culo.");
                    }
                    break;

                case 3:
                    //si elige esta opci�n, se mostrar�n el n�mero de Km del veh�culo
                    if (v1 != null) {
                        System.out.println("El n�mero de kil�mtros del Veh�culo es: " + v1.getKm());
                    } else {
                        System.out.println("No existe el veh�culo.");
                    }

                    break;
                case 4:
                    //si elige esta opci�n, se actualizar�n el n�mero de Km mediante el m�todo de la calse Veh�culo, kmActuales
                    if (v1 != null) {
                        System.out.println("Introduce el nuevo n�mero de kil�metros");
                        int kmNuevos = scn.nextInt();
                        if (kmNuevos > 0) {
                            v1.kmActuales(kmNuevos);
                        }
                    } else {
                        System.out.println("No existe el veh�culo.");
                    }
                    break;

                case 5:
                    //si elige esta opci�n, se mostrar�n los a�os que tiene el veh�culo
                    if (v1 != null) {
                        System.out.println("El veh�culo tiene: " + v1.getAnios() + " a�os");
                    } else {
                        System.out.println("No existe el veh�culo.");
                    }
                case 6:
                    //si elige esta opci�n se mostrar� el propietario del veh�culo y el DNI
                    if (v1 != null) {
                        System.out.println("El propietario del veh�culo es: " + v1.getNombre() + ", con DNI " + v1.getDni());
                    } else {
                        System.out.println("No existe el veh�culo.");
                    }
                case 7:
                    //si elige esta opci�n, se mostrar� la descripci�n del veh�culo
                    if (v1 != null) {
                        System.out.println("La descripci�n del veh�culo es: " + v1.getDescripcion());
                    } else {
                        System.out.println("No existe el veh�culo.");
                    }
                case 8:
                    //si elige esta opci�n, se mostrar� el precio del veh�culo
                    if (v1 != null) {
                        System.out.println("El precio del veh�culo es: " + v1.getPrecio() + "?");
                    } else {
                        System.out.println("No existe el veh�culo.");
                    }
                case 9: {
                    //si elige esta opci�n, saldr� del programa
                    System.out.println("Hasta pronto");
                    break;
                }
            }
        } while (opcion != 9);//este bucle se repetir� siempre y cuando la opcion que se elija sea diferente a 9
    }
        
    
    
}
