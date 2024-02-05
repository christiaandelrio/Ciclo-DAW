/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Principal;

import static Auxiliar.Utilidades.*;
import Auxiliar.Validaciones;
import java.time.LocalDate;

/**
 * Clase que representa la aplicación principal de gestión de un concesionario.
 * Permite realizar operaciones como agregar vehículos, listarlos, buscarlos,
 * etc. El programa se ejecuta mediante un menú interactivo.
 *
 * @author Sergio García López
 */
public class Principal {

    /**
     * Método principal que inicia la ejecución de la aplicación. Muestra un
     * menú interactivo y realiza operaciones según la opción elegida por el
     * usuario.
     *
     */
    public static void main(String[] args) {
        //Scanner scan = new Scanner(System.in);
        Concesionario concesionario = new Concesionario();
        int opcion; //Variable del menú de opciones a elegir por el usuario
        do {
            //Opciones a mostrar en el menú
            String[] opcionesMenu = {"\n1. Nuevo Vehiculo.", "2. Listar Vehículos. ", "3. Buscar Vehículo. ", "4. Modificar kms Vehículo. ", "5. Eliminar vehículo. ", "6. Salir.\n"};
            mostarMenu(opcionesMenu); //Método que muestra el menú de opciones al usuario definida en la clase Utilidades

            opcion = scanInt("Introduzca la opción: "); //Escanear la opción elegida por el usuario
            //scan.nextLine();

            switch (opcion) {
                case 1:
                    Vehiculo vehiculo = crearVehiculo(); //Función definida más abajo para crear un vehículo
                    if (vehiculo != null) {
                        switch (concesionario.insertarVehiculo(vehiculo)) {
                            case -1:
                                System.out.println("\nEl concesionario está lleno");
                                break;
                            case -2:
                                System.out.println("\nEl vehículo ya existe en el concesionario (matrícula repetida).");
                                break;
                            case 0:
                                System.out.println("\nVehículo creado e introducido satisfactoriamente");
                                break;
                            default:
                                break;
                        }
                    } else {
                        System.out.println("No se ha creado el vehículo");
                    }
                    break;
                case 2:
                    //Listar vehículos:
                    concesionario.listaVehiculos();
                    break;
                case 3:
                    //Buscar vehículo:
                    String matricula = scanString("Introduce una mátricula: ");
                    if (concesionario.buscaVehiculo(matricula) == null) {
                        System.out.println("No se ha encontrado el vehículo");
                    } else {
                        System.out.println(concesionario.buscaVehiculo(matricula));
                    }
                    break;
                case 4:
                    //Modificar kms Vehículo
                    String matr = scanString("Introduce una mátricula para modificar los kms: ");
                    int kms = scanInt("Introduce el número de kilómetros: ");
                    if (concesionario.modificarKmsVehiculo(matr, kms) == false) {
                        System.out.println("No se han podido actualizar los kms");
                    } else {
                        System.out.println("Kilómetros actualizados correctamente");
                    }
                    break;
                case 5:
                    //Eliminar vehículo.
                    String mat = scanString("Introduce una matrícula para eliminar un vehículo: ");
                    concesionario.eliminarVehiculo(mat);
                    break;
                case 6:
                    //Salir.
                    System.out.println("Programa finalizado.");
                    break;
            }
        } while (opcion != 6); //El menú se ejecutará hasta que se marque la opción 6 por parte del usuario.
    }

    /**
     * Crea un nuevo objeto de tipo Vehiculo con los datos proporcionados por el
     * usuario. Solicita información como marca, matrícula, descripción,
     * propietario, etc.
     *
     * @return Un objeto Vehiculo creado con la información proporcionada por el
     * usuario. Devuelve null si no se pudo crear el vehículo.
     */
    public static Vehiculo crearVehiculo() {

        //Variable que si es true permite crear un vehículo y si el false no.
        boolean todoOK = true;

        //----- Marca ----//
        //Solicitamos los datos para crear un vehículo: String marca, String matricula, String descripcion, String nombrePropietario, String DNIPropietario, int numKm, int dia, int mes, int anio, double precio
        String marca = scanString("Introduce la marca del vehículo: "); //scanString() es un método definido en la clase Utilidades

        //----- Matrícula ----//
        String matricula;
        do {
            matricula = scanString("Introduce la matricula del vehículo: ");
        } while (!Validaciones.validaMatricula(matricula)); //Se solicita la matrícula mientras no se cumpla el formato de matrícula establecido en la clase Validaciones.

        //----- Descripción ----//
        String descripcion = scanString("Introduce descripción del vehículo: ");

        //----- Nombre y apellidos ----//
        String nombreApellidosPropietario;
        do {
            nombreApellidosPropietario = scanString("Introduce nombre y dos apellidos (con menos de 40 caracteres): ");
        } while (!Validaciones.validaNombreApellidos(nombreApellidosPropietario)); //Se solicita el nombre y apellidos mientras no se cumpla con el formato.

        //----- DNI Propietario ----//
        String DNIPropietario;
        do {
            DNIPropietario = scanString("Introduce el DNI del propietario del vehículo: ");
        } while (!Validaciones.validaDNI(DNIPropietario)); //Se solicita el DNI hasta que cumpla la validación establecida en la clase Validaciones. 

        //----- Número de kilómetros ----//
        int numKm = 0;
        do {
            numKm = scanInt("Introduce el número de kilómetros del vehículo: "); //El método scanInt() está definido en la clase Utilidades.
        } while (numKm < 0); //Mientras el usuario introduzca un valor negativo se le seguirán solicitando los kms.

        //----- Fecha ----//
        LocalDate fechaIntroducida = null;
        do {
            System.out.println("\nIntroduce una fecha de matriculación anterior a la fecha actual: ");
            int dia = scanInt("Introduce el día de matriculación del vehículo: ");
            int mes = scanInt("Introduce el mes de matriculación del vehículo: ");
            int anio = scanInt("Introduce el año de matriculación del vehículo: ");
            fechaIntroducida = LocalDate.of(anio, mes, dia);
        } while (!Validaciones.FechaAnteriorAActual(fechaIntroducida)); //Mientras la fecha introducida no sea anterior a la actual se le seguirá solicitando al usuario.

        //----- Precio ----//
        Double precio = 0.0;
        do {
            precio = scanDouble("Introduce el precio del vehículo: "); //El método scanDouble() está definido en la clase Utilidades.
        } while (precio < 0); //Mientras el usuario introduzca un valor negativo se le pedirá el precio.

        //----- Crear vehículo ----//
        //Si todos los datos son correctos creamos el vehículo instanciando con el constructor de la clase Vehículo y lo introducimos en el array de Vehiculos
        if (todoOK) {
            return new Vehiculo(marca, matricula, descripcion, nombreApellidosPropietario, DNIPropietario, numKm, fechaIntroducida, precio);
        } else {
            System.out.println("\nNo se ha podido crear el nuevo vehículo\n");
        }

        //Si los datos no son correctos no creamos el vehículo devolviendo null.
        return null;

    }
}
