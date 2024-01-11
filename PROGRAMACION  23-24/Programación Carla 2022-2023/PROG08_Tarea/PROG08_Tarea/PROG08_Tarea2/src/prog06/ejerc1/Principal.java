/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog06.ejerc1;

import java.time.LocalDate;
import java.util.Scanner;
import prog06.ejerc1.util.Validar;

/**
 *
 * @author
 */
public class Principal {

    static Scanner sca = new Scanner(System.in);

    /*
    Muestra el menú en pantalla.
     */
    public static int mostrarMenu() {

        System.out.println("GESTIÓN DE VEHÍCULOS DE UN CONCESIONARIO");

        System.out.println("1.Nuevo Vehículo.");
        System.out.println("2.Listar Vehículos.");
        System.out.println("3.Buscar Vehículo.");
        System.out.println("4.Modificar Kilómetros de Vehículo.");
        System.out.println("5.Salir.");

        int opt = sca.nextInt();
        sca.nextLine(); //consumimos el salto de línea sino al leer el siguiente error tenemos error.
        return opt;
    }

    public static void main(String args[]) {
        int opt;

        String marca, matricula, descripcion, propietario, dni_propietario;
        int numkms, precio;
        int dia_mat, mes_mat, anio_mat;
        LocalDate fecha_mat;

        //En principio la referencia al Vehículo apuntará a null.
        Concesionario concesionario = new Concesionario();

        do {
            opt = mostrarMenu();

            switch (opt) {

                //Crear nuevo vehículo. Si ya existe alguno, desaparecerá su referencia al crear el nuevo.
                case 1:
                    System.out.println("Nuevo Vehículo");
                    System.out.println("Introduce la marca del vehículo");
                    marca = sca.nextLine();
                    System.out.println("Introduce la matrícula del vehículo");
                    matricula = sca.nextLine();
                    do {
                        if (!Validar.validaMatricula(matricula)) {
                            System.out.println("El formato de la matrícula no es correcto. Debe ser NNNNLLL. Introdúcela otra vez.");
                            matricula = sca.nextLine();
                        }
                    } while (!Validar.validaMatricula(matricula));
                    System.out.println("Introduce la descripción del vehículo");
                    descripcion = sca.nextLine();
                    System.out.println("Introduce el número de kilómetros del vehículo");
                    numkms = sca.nextInt();
                    sca.nextLine();
                    System.out.println("Introduce el precio del vehículo");
                    precio = sca.nextInt();
                    sca.nextLine();
                    System.out.println("Introduce el propietario del vehículo");
                    propietario = sca.nextLine();

                    do {
                        if (!Validar.validaNombre(propietario)) {
                            System.out.println("El nombre del propietario no puede exceder de 40 caracteres y deben contener al menos dos espacio en blanco. INtrodúcelo otra vez");
                            propietario = sca.nextLine();
                        }
                    } while (!Validar.validaNombre(propietario));

                    System.out.println("Introduce el dni propietario del vehículo");
                    dni_propietario = sca.nextLine();
                    do {
                        if (!Validar.validaDNI(dni_propietario)) {
                            System.out.println("El formato del DNI no es correcto. Debe ser NNNNNNNNL. Introdúcelo otra vez.");
                            dni_propietario = sca.nextLine();
                        }
                    } while (!Validar.validaDNI(dni_propietario));

                    System.out.println("Introduce el dia de matriculacion");
                    dia_mat = sca.nextInt();
                    sca.nextLine();

                    System.out.println("Introduce el mes de matriculacion");
                    mes_mat = sca.nextInt();
                    sca.nextLine();

                    System.out.println("Introduce el año de matriculacion");
                    anio_mat = sca.nextInt();
                    sca.nextLine();

                    fecha_mat = LocalDate.of(anio_mat, mes_mat, dia_mat);

                    //Llegados a este punto, hay que insertar el vehículo
                    int result = concesionario.insertarVehiculo(marca, matricula, numkms, fecha_mat, descripcion, precio, propietario, dni_propietario);
                    switch (result) {
                        case 0:
                            System.out.println("El vehículo ha sido creado en el concesionario");
                            break;
                        case -1:
                            System.out.println("El concesionario está completo.");
                            break;
                        case -2:
                            System.out.println("El vehículo ya existe.");
                            break;
                        default:
                            break;
                    }

                    break;

                case 2:
                    System.out.println("Listado de Vehículos");
                    concesionario.listaVehiculos();
                    ;
                    break;

                case 3:
                    System.out.println("Búsqueda de Vehículo por Matrícula");
                    System.out.println("Introduce la matrícula");
                    String mat_a_buscar = sca.nextLine();
                    String veh = concesionario.buscaVehiculo(mat_a_buscar);

                    if (veh == null) {
                        System.out.println("No existe vehículo con la matrícula introducia");
                    } else {
                        System.out.println(veh);
                    }
                    break;
                case 4:
                    System.out.println("Modificar kilómetros de Vehículo");
                    System.out.println("Introduce la matrícula");
                    String mat_a_busc = sca.nextLine();
                    System.out.println("Introduce los kilómetros");
                    int kms = sca.nextInt();
                    sca.nextLine();
                   
                    if (concesionario.actualiza_kmVeh(mat_a_busc, kms))
                        System.out.println("Se han actualizado los kilómetros del vehículo con matrícula " + mat_a_busc);
                    break;
                case 5: {
                    System.out.println("Eso es todo. BYE");
                    break;
                }
                  
            }
        } while (opt != 5);
    }

}
