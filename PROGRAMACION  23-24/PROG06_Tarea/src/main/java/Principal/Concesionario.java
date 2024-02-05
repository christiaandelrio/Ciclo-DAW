/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

import java.time.LocalDate;

/**
 * Clase que representa un concesionario de vehículos. Permite realizar
 * operaciones como insertar, buscar, listar, modificar y eliminar vehículos.
 * Almacena los vehículos en un array.
 *
 * @author Sergio García López
 */
public class Concesionario {

    private static final int MAX_VEHICULOS = 50; //Número máximo de vehículos almacenables
    private Vehiculo[] vehiculos; //Array que almacena los vehículos
    private int contadorVehiculos; //Contador de vehículos creados

    /**
     * Constructor de la clase Concesionario. Inicializa el array de vehículos y
     * el contador.
     */
    public Concesionario() {
        vehiculos = new Vehiculo[MAX_VEHICULOS];
        contadorVehiculos = 0;
    }

    /**
     * Busca un vehículo en el concesionario por su matrícula. Devuelve una
     * cadena con los datos del vehículo si se encuentra, o null si el vehículo
     * no existe en el concesionario.
     *
     * @param matricula Matrícula del vehículo a buscar.
     * @return Cadena con los datos del vehículo o null si no se encuentra.
     */

    /*buscaVehículo: Recibe como parámetro una matrícula, buscar el vehículo en el concesionario
    y devuelve una cadena con los datos del vehículo o null si el vehículo buscado no existe.*/
    public String buscaVehiculo(String matricula) {
        if (contadorVehiculos == 0) {
            return null;
        }

        for (int i = 0; i < contadorVehiculos; i++) {
            if (vehiculos[i].getMatricula().equals(matricula)) {
                return vehiculos[i].devuelveDatos();
            }
        }

        return null;
    }

    /**
     * Inserta un vehículo en el concesionario. Devuelve 0 si se hizo con éxito,
     * -1 si el concesionario está lleno y -2 si la matrícula ya existe.
     *
     * @param vehiculo Objeto Vehiculo a insertar en el concesionario.
     * @return 0 si se insertó con éxito, -1 si el concesionario está lleno, -2
     * si la matrícula ya existe.
     */
    public int insertarVehiculo(Vehiculo vehiculo) {

        //El enunciado dice que si está vacío devuelva -2. En mi opinión tendría más sentido verificar que está lleno y es lo que he considerado.
        if (contadorVehiculos == MAX_VEHICULOS) {
            return -1;
        }

        if (buscaVehiculo(vehiculo.getMatricula()) != null) {
            return -2;
        } else {
            vehiculos[contadorVehiculos] = vehiculo;
            contadorVehiculos++;
            return 0;
        }

    }

    /**
     * Lista por pantalla los datos de todos los vehículos del concesionario.
     */
    public void listaVehiculos() {
        if (contadorVehiculos > 0) {
            for (int i = 0; i < contadorVehiculos; i++) {
                System.out.print("Vehículo " + (i + 1) + ": ");
                vehiculos[i].mostrarDatos();
            }
        } else {
            System.out.println("\nNo se han creado vehículos por el momento.");
        }
    }

    /**
     * Modifica el número de kilómetros de un vehículo en el concesionario.
     * Devuelve true si se hizo con éxito y false en caso contrario.
     *
     * @param matricula Matrícula del vehículo a modificar.
     * @param kms Nuevos kilómetros del vehículo.
     * @return true si se modificó con éxito, false si no se encontró el
     * vehículo.
     */
    public boolean modificarKmsVehiculo(String matricula, int kms) {

        if (contadorVehiculos == 0) {
            return false;
        }

        for (int i = 0; i < contadorVehiculos; i++) {
            if (vehiculos[i].getMatricula().equalsIgnoreCase(matricula)) {
                vehiculos[i].setNumKm(kms);
                return true;
            }
        }

        return false;
    }

    /**
     * Elimina un vehículo del concesionario por su matrícula. Mueve los
     * vehículos siguientes para llenar el espacio.
     *
     * @param matricula Matrícula del vehículo a eliminar.
     * @return true si se eliminó con éxito, false si no se encontró el
     * vehículo.
     */
    public boolean eliminarVehiculo(String matricula) {
        if (contadorVehiculos == 0) {
            System.out.println("El concesionario está vacío.");
            return false;
        }

        for (int i = 0; i < contadorVehiculos; i++) {
            if (vehiculos[i].getMatricula().equals(matricula)) {
                // Mover los vehículos siguientes para llenar el espacio
                for (int j = i; j < contadorVehiculos - 1; j++) {
                    vehiculos[j] = vehiculos[j + 1];
                }

                vehiculos[contadorVehiculos - 1] = null; // Eliminar la última referencia duplicada
                contadorVehiculos--;

                System.out.println("Vehículo eliminado con éxito.");
                return true;
            }
        }

        System.out.println("No se encontró ningún vehículo con la matrícula especificada.");
        return false;
    }

}
