/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG05_Ejerc1;

import java.time.LocalDate;
import java.util.Scanner;
import static PROG05_Ejerc1_util.Utilidades.*;
import PROG05_Ejerc1_util.Validaciones;

/**
 * Clase principal que contiene el método main que se encarga de mostrar un menú
 * y ejecutar una serie de métodos auxiliares para la gestión de un vehículo
 * (crear, mostra datos, etc.).
 *
 * @author Sergio García López
 *
 */
public class Principal {

    /**
     * Método principal que ejecuta el programa de gestión de un vehículo.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Vehiculo vehiculo = null; //Declaramos un vehículo

        int opcion; //Variable del menú de opciones a elegir por el usuario
        do {
            //Opciones a mostrar en el menú
            String[] opcionesMenu = {"\n1. Nuevo Vehiculo.", "2. Ver Matrícula. ", "3. Ver Número de Kilómetros. ", "4. Actualizar Kilómetros. ", "5. Ver años de antigüedad.", "6. Mostrar propietario.", "7. Mostrar descripción.", "8. Mostrar Precio.", "9. Salir.\n"};
            mostarMenu(opcionesMenu); //Método que muestra el menú de opciones al usuario definida en la clase Utilidades

            opcion = scanInt("Introduzca la opción: "); //Escanear la opción elegida por el usuario
            //scan.nextLine();

            switch (opcion) {
                case 1:
                    //Nuevo vehículo:
                    vehiculo = crearVehiculo(); //Función definida más abajo que se encarga de crear un vehículo
                    if (vehiculo != null) {
                        System.out.println("\nVehículo creado: " + vehiculo.toString());
                    } else {
                        System.out.println("\nNo se ha podido crear el vehículo (Error al introducir los datos)");
                    }
                    break;
                case 2:
                    //Ver Matrícula: Mostrará la matrícula del vehículo por pantalla.
                    if (vehiculo == null) {
                        System.out.println("No se ha creado todavía ningún vehículo.");
                    } else {
                        System.out.print("\nLa matrícula del vehículo es: " + vehiculo.getMatricula() + "\n");
                    }
                    break;
                case 3:
                    //Ver Número de Kilómetros: Mostrará el número de kilómetros por pantalla.
                    if (vehiculo == null) {
                        System.out.println("No se ha creado todavía ningún vehículo.");
                    } else {
                        System.out.print("\nEl número de kilómetros del vehículo es: " + vehiculo.getNumKm() + "\n");
                    }
                    break;
                case 4:
                    //Actualiza Kilómetros: Permitirá actualizar el número de kilómetros del vehículo. Habrá que tener en cuenta que solo se podrán sumar kilómetros.
                    if (vehiculo == null) {
                        System.out.println("No se ha creado todavía ningún vehículo.");
                    } else {
                        int numeroKm = scanInt("\nIndique el número de kilómetros a añadir: ");
                        //Validación de que el número de kms introducidos sea mayor que cero.
                        if (numeroKm > 0) {
                            vehiculo.actualizar_kms(numeroKm);
                        }else{
                            System.out.println("El número de km a añadir debe ser mayor que cero.");
                        }
                    }
                    break;
                case 5:
                    //Ver años de antigüedad: Mostrará por pantalla el número de años del vehículo desde que se matriculó, no la fecha de matriculación.
                    if (vehiculo == null) {
                        System.out.println("No se ha creado todavía ningún vehículo.");
                    } else {
                        System.out.print("\nAntigüedad del vehículo: " + vehiculo.get_Anios() + " años \n");
                    }
                    break;
                case 6:
                    //Mostrar propietario: Mostrará por pantalla el nombre del propietario del vehículo junto a su dni.
                    if (vehiculo == null) {
                        System.out.println("No se ha creado todavía ningún vehículo.");
                    } else {
                        System.out.print("\nEl propietario del vehículo es " + vehiculo.getNombrePropietario() + " con DNI " + vehiculo.getDNIPropietario() + "\n");
                    }
                    break;
                case 7:
                    //Mostrar Descripción: Mostrará una descripción del vehículo, incluyendo su matrícula y el número de kilómetros que tiene.
                    if (vehiculo == null) {
                        System.out.println("No se ha creado todavía ningún vehículo.");
                    } else {
                        System.out.print("La descripción del vehículo es: " + vehiculo.getDescripcion() + " con matrícula " + vehiculo.getMatricula() + " y número de km " + vehiculo.getNumKm() + "\n");
                    }
                    break;
                case 8:
                    //Mostrar Pecio: se mostrará el precio del vehículo.
                    if (vehiculo == null) {
                        System.out.println("No se ha creado todavía ningún vehículo.");
                    } else {
                        System.out.print("\n El precio del vehículo es: " + vehiculo.getPrecio() + "\n");
                    }
                    break;
                case 9:
                    //Salir.
                    System.out.println("Programa finalizado");
                    break;
            }
        } while (opcion != 9); //El menú se ejecutará hasta que se marque la opción 9 por parte del usuario.
    }

    /**
     * Método para crear un nuevo vehículo, solicitando datos al usuario, validando parte de los mismos.
     *
     * @return Objeto Vehiculo creado si los datos son válidos, null si hay algún error.
     */
    public static Vehiculo crearVehiculo() {
        
        //Variable que si es true permite crear un vehículo y si el false no.
        boolean todoOK = true;

        //Solicitamos los datos para crear un vehículo: String marca, String matricula, String descripcion, String nombrePropietario, String DNIPropietario, int numKm, int dia, int mes, int anio, double precio
        String marca = scanString("Introduce la marca del vehículo: "); //scanStrin() el un método definido en la clase Utilidades
        String matricula = scanString("Introduce matricula del vehículo: ");
        String descripcion = scanString("Introduce descripción del vehículo: ");
        String nombrePropietario = scanString("Introduce el nombre del propietario del vehículo: ");

        /*Solicitamos DNI comprobando que es correcto.
        Si no se cumple algunas de las condiciones se deberá mostrar el correspondiente mensaje de error. En ese caso habrá se mostrará de nuevo el menú principal.*/
        String DNIPropietario = scanString("Introduce el DNI del propietario del vehículo: ");

        //Validación del DNI usando el método validaDNI definido en la clase Validaciones. En el caso de que no cumpla lanza una excepción que se captura en el catch mostrando un mensaje.
        try {
            Validaciones.validaDNI(DNIPropietario);
        } catch (Exception e) {
            todoOK = false;
            System.out.println("\nEl DNI del propietario del vehículo no tiene un formato correcto. No se creará el vehículo.\n");
        }

        //Solicitamos el número de km comprobando que el valor introducido no es negativo:
        int numKm = 0;
        do {
            numKm = scanInt("Introduce el número de kilómetros del vehículo: "); //El método scanInt() está definido en la clase Utilidades.
        } while (numKm < 0); //Mientras el usuario introduzcca un valor negativo se le seguirán solicitando los kms.

        //Creamos la fecha usando LocalDate y comprobando que la fecha introducida es anterior a la actual.
        LocalDate fechaIntroducida = null;
        do {
            System.out.println("\nIntroduce una fecha de matriculación anterior a la fecha actual: ");
            int dia = scanInt("Introduce el día de matriculación del vehículo: ");
            int mes = scanInt("Introduce el mes de matriculación del vehículo: ");
            int anio = scanInt("Introduce el año de matriculación del vehículo: ");
            fechaIntroducida = LocalDate.of(anio, mes, dia);
        } while (!Validaciones.compararFechaAnteriorActual(fechaIntroducida)); //Mientras la fecha introducida no sea anterior a la actual se le seguirá solicitando al usuario.

        Double precio = scanDouble("Introduce el precio del vehículo: "); //El método scanDouble() está definido en la clase Utilidades.

        //Si todos los datos son correctos creamos el vehículo instanciando con el constructor de la clase Vehículo.
        if (todoOK) {
            return new Vehiculo(marca, matricula, descripcion, nombrePropietario, DNIPropietario, numKm, fechaIntroducida, precio);
        }
        //Si los datos no son correctos no creamos el vehículo haciendo que este sea null.
        return null;
    }
}
