/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ciclo.prog07_tarea;

import static ciclo.prog07_tarea.Utilidades.*;
import java.util.InputMismatchException;

/**
 *
 * @author Sergio García López
 */
public class Principal {

    public static void main(String[] args) {
        //Creamos el banco donde se almacenarán todas las cuentas:
        Banco banco = new Banco();

        //Declaramos todos los atributos que vamos a emplear para crear las cuentas:
        Persona titular;
        double saldo, tipoInteres, comisionMantenimiento, maximoDescubierto, tipoInteresDescubierto, comisionFijaDescubierto, cantidad;
        String IBAN, listaEntidades, nombreTitular, apellidosTitular, DNITitular;

        String[] listaCuentas;

        //Declaramos la cuenta, que más adelante instanciamos tras solicitar los datos que correspondan.
        CuentaBancaria cuenta = null;

        //Atributos para las opciones de los menús.
        int opcion, tipoCuenta;

        try {

            do {

                //Opciones del menú principal
                String[] opcionesMenuPpal = {
                    "1. Abrir una nueva cuenta.",
                    "2. Ver un listado de las cuentas disponibles (código de cuenta, titular y saldo actual).",
                    "3. Obtener los datos de una cuenta concreta.",
                    "4. Realizar un ingreso en una cuenta.",
                    "5. Retirar efectivo de una cuenta.",
                    "6. Consultar el saldo actual de una cuenta.",
                    "7. Salir de la aplicación.\n"};

                //Mostramos el menú con el método mostrarMenu creado en Utilidades.
                mostrarMenu(opcionesMenuPpal);

                //Solicitamos la opción elegida al ususario.
                opcion = scanInt("Introduzca una opción: ");

                switch (opcion) {
                    case 1:
                        //Abrir una nueva cuenta.;

                        //Necesitamos Persona titular, double saldo, String IBAN para crear una CuentaBancaria:
                        //Vamos a crear el titular (objeto de la clase Persona) para ello necesitamos String nombre, String apellidos, String DNI
                        nombreTitular = scanString("Introduzca el nombre del titular de la cuenta: "); //scanString es un método definido en la clase Utilidades.
                        apellidosTitular = scanString("Introduzca los apellidos del titular de la cuenta: ");
                        DNITitular = scanString("Introduzca el DNI del titular de la cuenta: ");
                        
                        //Con los datos solicitados instanciamos el objeto de la clase Persona que es el titular de la cuenta.
                        titular = new Persona(nombreTitular, apellidosTitular, DNITitular);

                        //Solicitar el IBAN validándolo (si se introduce con un formato no válido se vuelve a solicitar). El método validarIBAN está definido más abajo:
                        do {
                            IBAN = scanString("Introduzca un IBAN válido: ");
                        } while (validarIBAN(IBAN) == false);

                        //Solicitar saldo:
                        saldo = scanDouble("Introduzca el saldo de la cuenta: "); //scanDouble es un método definido en la clase Utilidades.

                        //Ahora tenemos que elegir un tipo de cuenta (CuentaAhorro, CuentaCorrientePersonal o CuentaCorrienteEmpresa) ya que en función de ello se solicitan unos datos adicionales u otros.
                        //Menú de opciones para elegir el tipo de cuenta.
                        String[] opcionesMenu = {
                            "1. Cuenta de ahorro.",
                            "2. Cuenta corriente personal.",
                            "3. Cuenta corriente empresa."};
                        mostrarMenu(opcionesMenu);

                        //Escanear la opción elegida por el usuario.
                        tipoCuenta = scanInt("Introduzca la opción elegida: ");  // scanInt es un método definido en la clase Utilidades.

                        switch (tipoCuenta) {
                            case 1:
                                //Vamos a crear una CuentaAhorro para ello necesitamos double tipoInteres, Persona titular, double saldo, String IBAN. Sólo tenemos que solicitar a mayores el tipoInterés
                                tipoInteres = scanDouble("Introduzca el tipo de interés de la cuenta de ahorro: ");
                                //Ya tenemos todos los datos para crear una cuenta de ahorro.
                                cuenta = new CuentaAhorro(tipoInteres, titular, saldo, IBAN);
                                //Mostramos los datos de la cuenta creada.
                                System.out.println("Cuenta creada: " + cuenta.devolverInfoString());
                                break;
                            case 2:
                                //Vamos a crear una CuentaCorrientePersonal para ello necesitamos double comisionMantenimiento, String listaEntidades, Persona titular, double saldo, String IBAN.
                                //Sólo hay que solicitar a mayores la comisión de mantenimiento y la lista de Entidades.
                                comisionMantenimiento = scanDouble("Introduzca la comisión de mantenimiento de la cuenta corriente personal: ");
                                listaEntidades = scanString("Introduzca la lista de entidades autorizadas: ");
                                //Ya tenemos todos los datos para crear la cuenta corriente personal.
                                cuenta = new CuentaCorrientePersonal(comisionMantenimiento, listaEntidades, titular, saldo, IBAN);
                                //Mostramos los datos de la nueva cuenta creada.
                                System.out.println("Cuenta creada: " + cuenta.devolverInfoString());
                                break;
                            case 3:
                                //Vamos a crear una CuentaCorrienteEmpresa para ello vamos a necesitar double maximoDescubierto, double tipoInteresDescubierto, double comisionFijaDescubierto, String listaEntidades, Persona titular, double saldo, String IBAN;
                                //Hay que solicitar a mayores el maximoDescubierto, el tipoInteresDescubierto, comisionFijaDescubierto y listaEntidades.
                                maximoDescubierto = scanDouble("Introduzca el máximo descubierto de la cuenta corriente de empresa: ");
                                tipoInteresDescubierto = scanDouble("Introduzca el tipo de interés por descubierto de la cuenta: ");
                                comisionFijaDescubierto = scanDouble("Introduzca la comisión fija por descubierto de la cuenta: ");
                                listaEntidades = scanString("Introduzca la lista de entidades autorizadas: ");
                                //Ya tenemos todo para crear la CiuentaCorrienteEmpresa:
                                cuenta = new CuentaCorrienteEmpresa(maximoDescubierto, tipoInteresDescubierto, comisionFijaDescubierto, listaEntidades, titular, saldo, IBAN);
                                //Mostramos la información de la nueva cuenta creada: 
                                System.out.println("Cuenta creada: " + cuenta.devolverInfoString());
                                break;
                        }
                        //Una vez creada la cuenta hay que introducirla en el banco:
                        if (banco.abrirCuenta(cuenta)) {
                            System.out.println("Cuenta introducida correctamente.");
                        } else {
                            System.out.println("No se ha podido introducir la cuenta.");
                        }
                        break;
                    case 2:
                        //Ver un listado de las cuentas disponibles (código de cuenta, titular y saldo actual).
                        listaCuentas = banco.listarCuentas();
                        //Si no hay cuentas mostramos mensaje y si las hay mostramos la información.
                        if (listaCuentas.length == 0) {
                            System.out.println("No hay cuentas creadas por el momento.");
                        } else {
                            for (String infoCuenta : listaCuentas) {
                                System.out.println(infoCuenta);
                            }
                        }
                        break;
                    case 3:
                        //Obtener los datos de una cuenta concreta. 
                        //Escaneamos el IBAN
                        IBAN = scanString("Introduzca el IBAN para buscar la cuenta bancaria: ");
                        //Buscamos la cuenta.
                        cuenta = banco.buscarCuenta(IBAN);
                        if (cuenta == null) {
                            System.out.println("La cuenta que busca no existe."); // Si no exite mostramos el mensaje correspondiente.
                        } else {
                            System.out.println("Información de la cuenta: " + cuenta.devolverInfoString()); // Si existe mostramos la info de la cuenta.
                        }
                        break;
                    case 4:
                        //Realizar un ingreso en una cuenta.
                        //Solicitamos y escaneamos IBAN
                        IBAN = scanString("Introduzca el IBAN para buscar la cuenta bancaria: ");
                        //Buscamos la cuenta
                        cuenta = banco.buscarCuenta(IBAN);
                        if (cuenta == null) {
                            //Mostramos mensaje si la cuenta no existe.
                            System.out.println("La cuenta que busca no existe.");
                        } else {
                            //Solicitamos la cantidad a introducir:
                            cantidad = scanDouble("Introduzca una cantidad a ingresar en cuenta: ");
                            //Hacemos el ingreso.
                            if (banco.ingresoCuenta(IBAN, cantidad)) {
                                System.out.println("Ingreso realizado con éxito");
                            } else {
                                System.out.println("No se ha podido realizar el ingreso");
                            }
                        }
                        break;
                    case 5:
                        //Realizar un ingreso en una cuenta.
                        //Solicitamos y escaneamos IBAN
                        IBAN = scanString("Introduzca el IBAN para buscar la cuenta bancaria: ");
                        //Buscamos la cuenta
                        cuenta = banco.buscarCuenta(IBAN);
                        if (cuenta == null) {
                            //Mostramos mensaje si la cuenta no existe.
                            System.out.println("La cuenta que busca no existe.");
                        } else {
                            //Solicitamos la cantidad a introducir:
                            cantidad = scanDouble("Introduzca una cantidad a retirar de la cuenta: ");
                            //Hacemos la retirada y mostramos el mensaje que corresponda.
                            if (banco.retiradaCuenta(IBAN, cantidad)) {
                                System.out.println("Cantidad retirada satisfactoriamente.\nSu saldo actual es " + cuenta.getSaldo());
                            } else {
                                System.out.println("No se ha podido retirar la cantidad.");
                            }
                        }
                        break;
                    case 6:
                        //"Consultar el saldo actual de una cuenta.
                        //Solicitamos y escaneamos IBAN
                        IBAN = scanString("Introduzca el IBAN para buscar la cuenta bancaria: ");
                        //Obtenemos el saldo
                        saldo = banco.obtenerSaldo(IBAN);
                        if(saldo == -1){
                            //Mensaje si la cuenta no exite.
                            System.out.println("La cuenta no existe.");
                        }else{
                            //Mostramos saldo si la cuenta existe.
                            System.out.println("El saldo es: " + saldo);
                        }
                        break;
                    case 7:
                        System.out.println("Ha salido de la aplicación.\n");
                        break;
                }
            } while (opcion != 7);

        } catch (InputMismatchException e) {
            //Manejamos con una excepción cuando el usuario introduce un tipo de dato que no se ajusta al solicitado, mostrando un mensaje de error.
            System.out.println("El formato introducido no es válido.");
        } catch (Exception e) {
            //Captura de cualquier error no esperado.
            System.out.println(e.getMessage());
        }

    }

    //Método para validad un IBAN mediante una expresión regular.
    public static boolean validarIBAN(String IBAN) {
        return IBAN.matches("^ES[0-9]{20}$"); //Devolvemos true o false en función de si el DNI introducido se ajusta o no al patrón
    }
}
