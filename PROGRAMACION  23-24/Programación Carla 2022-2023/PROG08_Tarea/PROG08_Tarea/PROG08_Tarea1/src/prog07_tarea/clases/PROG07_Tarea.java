/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog07_tarea.clases;

import java.util.GregorianCalendar;
import prog07_tarea.util.Util_ES;

//---------------------------------------------------------------        
//                 CLASE AplicacionCuentaBancaria
//---------------------------------------------------------------    
// Ejemplo de uso de la clase CuentaBancaria.
//---------------------------------------------------------------    
public class PROG07_Tarea {

    /*
        Solictado datos de cuenta bancaria por teclado. Dependiendo de la cuenta a abrir, solicita unos datos y otros
        Devuelve un objeto de tipo CuentaBancaria que representa la cuenta a abrir.
    */
    public static CuentaBancaria obtenDatos() throws Exception {
        String nombre, apellidos;
        GregorianCalendar fechaNacim;
        Double saldo;
        int tipoCuenta;

        CuentaBancaria cuenta = null;

        System.out.printf("ABRIR NUEVA CUENTA\n");
        System.out.printf("------------------\n");

        // Nombre del titular
        System.out.printf("Nombre del titular:  ");
        nombre = Util_ES.lecturaTeclado();

        // Apellidos del titular
        System.out.printf("Apellidos del titular:  ");
        apellidos = Util_ES.lecturaTeclado();

        /* Debug nombre="Pepe Luis";
        apellidos= "Bandera Hidalgo"; */

 /*DNI del titular*/
        boolean dniValido = false;
        String stringdni = "";
        do {

            System.out.print("DNI del titular (formato NNNNNNNNL): ");
            try {
                stringdni = Util_ES.lecturaTeclado();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            if (stringdni.matches("^[0-9]{8}[A-Z]{1}$")) {
                dniValido = true;
            } else {
                System.out.println("Error en el formato del DNI. Debe ser NNNNNNNNL. Inténtalo otra vez.");
            }

        } while (!dniValido);

        // Saldo inicial de la cuenta
        saldo = -1.0;
        /* Debug: saldo=100.0; */
        do {
            try {
                System.out.printf("Saldo inicial:  ");
                String stringCantidad = Util_ES.lecturaTeclado();
                saldo = Double.parseDouble(stringCantidad);
            } catch (Exception ex) {
                System.err.println("Cantidad no válida.");
                System.err.println(ex.getMessage());
            }
        } while (saldo < 0);

        // Tipo de cuenta
        String tipo = null;
        do {
            System.out.printf("Tipo de cuenta (1= Cuenta de ahorro, 2=Cuenta corriente personal, 3=Cuenta corriente de empresa):  ");
            tipo = Util_ES.lecturaTeclado();
        } while (!tipo.equals("1") && !tipo.equals("2") && !tipo.equals("3"));
        tipoCuenta = Integer.parseInt(tipo);

        // Número de cuenta
        String ccc = null;
        boolean cuentaValida = false;
        while (!cuentaValida) {
            System.out.println("Número de cuenta: (debe ser una cuenta válida ES00000000000000000000)");
            ccc = Util_ES.lecturaTeclado();
            cuentaValida = CuentaBancaria.validarCuenta(ccc);
            if (!cuentaValida) {
                System.err.println("Cuenta no válida.");
            }

        }

        String stringCantidad = "";
        Persona titular = null;
        switch (tipoCuenta) {
            case 1: // Cuenta de Ahorro

                Double tipoInteres = 0.0;

                System.out.printf("Cuenta de Ahorro: \n");
                System.out.printf("Tipo de interés: ");
                try {
                    stringCantidad = Util_ES.lecturaTeclado();
                    tipoInteres = Double.parseDouble(stringCantidad);
                } catch (Exception ex) {
                    System.err.println("Cantidad no válida.");
                    System.err.println(ex.getMessage());
                    throw ex;
                }

                // Creación del objeto CuentaAhorro
                titular = new Persona(nombre, apellidos, stringdni);
                cuenta = new CuentaAhorro(titular, ccc, tipoInteres);
                /* Debug: cuenta= new CuentaAhorro (titular, ccc, 2.3); */
                break;

            case 2: // Cuenta Corriente Personal

                Double comisionMantenimiento = 0.0;
                System.out.printf("Cuenta Corriente Personal: \n");
                System.out.printf("Comisión de mantenimiento de cuenta: ");
                try {
                    stringCantidad = Util_ES.lecturaTeclado();
                    comisionMantenimiento = Double.parseDouble(stringCantidad);
                } catch (Exception ex) {
                    System.err.println("Cantidad no válida.");
                    System.err.println(ex.getMessage());
                    throw ex;
                }

                // Creación del objeto Cuenta Corriente Personal
                titular = new Persona(nombre, apellidos, stringdni);
                cuenta = new CuentaCorrientePersonal(titular, ccc, comisionMantenimiento);
                /* Debug: cuenta= new CuentaCorrientePersonal (titular, ccc, 10.0);                 */
                break;

            case 3: // Cuenta Corriente de Empresa

                Double comisionFijaDescubierto = 0.0;
                Double tipoInteresDescubierto = 0.0;
                Double maximoDescubierto = 0.0;

                System.out.printf("Cuenta Corriente de Empresa: \n");
                System.out.printf("Comisión fija por cada descubierto: ");
                try {
                    stringCantidad = Util_ES.lecturaTeclado();
                    comisionFijaDescubierto = Double.parseDouble(stringCantidad);
                } catch (Exception ex) {
                    System.err.println("Cantidad no válida.");
                    System.err.println(ex.getMessage());
                    throw ex;
                }

                System.out.printf("Tipo de interés aplicado a los descubiertos: ");
                try {
                    stringCantidad = Util_ES.lecturaTeclado();
                    tipoInteresDescubierto = Double.parseDouble(stringCantidad);
                } catch (Exception ex) {
                    System.err.println("Cantidad no válida.");
                    System.err.println(ex.getMessage());
                    throw ex;
                }

                System.out.printf("Máximo descubierto permitido: ");
                try {
                    stringCantidad = Util_ES.lecturaTeclado();
                    maximoDescubierto = Double.parseDouble(stringCantidad);
                } catch (Exception ex) {
                    System.err.println("Cantidad no válida.");
                    System.err.println(ex.getMessage());
                    throw ex;
                }

                // Creación del objeto Cuenta Corriente de Empresa
                titular = new Persona(nombre, apellidos, stringdni);
                cuenta = new CuentaCorrienteEmpresa(titular, ccc, comisionFijaDescubierto, tipoInteresDescubierto, maximoDescubierto);
                /* Debug cuenta= new CuentaCorrienteEmpresa (titular, ccc, 50, 2, 500);                 */
                break;

            default:
                break;

        }

        if (cuenta != null) {
            cuenta.ingresar(saldo);
        }
        return cuenta;
    }

    //---------------------------------------------------------------        
    // MÉTODO main: PROGRAMA PRINCIPAL
    //---------------------------------------------------------------            
    public static void main(String[] args) throws Exception {

        Banco banco = new Banco();

        // PRESENTACIÓN
        // ------------
        System.out.printf("APLICACIÓN DE GESTIÓN DE CUENTAS BANCARIAS \n");
        System.out.printf("------------------------------------------\n\n");

        // -------------------------------------------------------------
        // MENÚ: ENTRADA DE DATOS, PROCESAMIENTO Y SALIDA DE RESULTADOS
        // -------------------------------------------------------------
        boolean fin = false;
        while (!fin) {

            // "Dibujo" del menú
            System.out.println("Seleccionar una opción:");
            System.out.println("[1] Abrir una nueva cuenta.");
            System.out.println("[2] Ver un listado de las cuentas disponibles.");
            System.out.println("[3] Obtener los datos de una cuenta.");
            System.out.println("[4] Realizar un ingreso en una cuenta.");
            System.out.println("[5] Retirar efectivo de una cuenta.");
            System.out.println("[6] Consultar saldo de una cuenta.");
            System.out.println("[7] Eliminar cuenta.");
            System.out.println("[0] Salir.");

            System.out.println("Escriba la selección: ");
            int selec = Util_ES.leeOpcion();

            // Gestión de la opción de menú
            switch (selec) {
                case 1: //Abrir nueva cuenta
                    if (banco.abrirCuenta(obtenDatos())) {
                        System.out.println("La cuenta ha sido añadida.");
                    } else {
                        System.out.println("Error al insertar la cuenta.");
                    }
                    break;
                case 2: //Listado de cuentas. Otra posibilidad sería que el banco no muestre datos por pantalla, sino que los devuelva en alguna estructructura
                        //y se muestren desde aquí.
                    String cuentas[]=banco.listadoCuentas();
                    for (String cuenta : cuentas) {
                        System.out.println(cuenta);
                    }
                    break;

                case 3: //Información de Cuenta.
                    // Número de cuenta
                    boolean cuentaValida = false;
                    String ccc = "";
                    while (!cuentaValida) {
                        System.out.println("Número de cuenta: (debe ser una cuenta válida)");
                        ccc = Util_ES.lecturaTeclado();
                        cuentaValida = CuentaBancaria.validarCuenta(ccc);
                        if (!cuentaValida) {
                            System.err.println("Cuenta no válida.");
                        }
                    }
                    String info = banco.informacionCuenta(ccc);
                    if (info != null) {
                        System.out.println(info);
                    } else {
                        System.out.println("No existe la cuenta");
                    }
                    break;
                case 4: //Ingreso en cuenta
                    System.out.printf("Ingreso de efectivo: \n");

                    // Número de cuenta
                    cuentaValida = false;
                    ccc = "";

                    while (!cuentaValida) {
                        System.out.println("Número de cuenta: (debe ser una cuenta válida)");
                        ccc = Util_ES.lecturaTeclado();
                        cuentaValida = CuentaBancaria.validarCuenta(ccc);
                        if (!cuentaValida) {
                            System.err.println("Cuenta no válida.");
                        }
                    }

                    System.out.printf("Cantidad que desea ingresar: ");
                    try {
                        // Cantidad
                        String stringCantidad = Util_ES.lecturaTeclado();
                        Double cantidad = Double.parseDouble(stringCantidad);

                        if (banco.ingresoCuenta(ccc, cantidad)) {
                            System.out.printf("Cantidad de %.2f euros ingresada en la cuenta %s.\n", cantidad, ccc);
                        } else {
                            System.out.println("La cuenta indicadA no existe");
                        }
                    } catch (Exception ex) {
                        System.err.println("Cantidad no válida.");
                        System.err.println(ex.getMessage());
                    }

                    break;
                case 5: //RETIRADA DE EFECTIVO

                    ccc = null;
                    CuentaBancaria cuenta = null;

                    System.out.printf("Retirada de efectivo: \n");

                    // Número de cuenta
                    cuentaValida = false;
                    while (!cuentaValida) {
                        System.out.println("Número de cuenta: (debe ser una cuenta válida)");
                        ccc = Util_ES.lecturaTeclado();
                        cuentaValida = CuentaBancaria.validarCuenta(ccc);
                        if (!cuentaValida) {
                            System.err.println("Cuenta no válida.");
                        }
                    }

                    System.out.printf("Cantidad que desea retirar: ");
                    try {
                        // Cantidad
                        String stringCantidad = Util_ES.lecturaTeclado();
                        Double cantidad = Double.parseDouble(stringCantidad);
                        if (banco.retiradaCuenta(ccc, cantidad)) {
                            System.out.printf("Cantidad de %.2f euros retirada de la cuenta %s.\n", cantidad, ccc);
                        } else {
                            System.out.println("La cuenta indicado no existe o no tiene efectivo");
                        }
                    } catch (Exception ex) {
                        System.err.println("Cantidad no válida.");
                        System.err.println(ex.getMessage());
                    }

                    break;
                case 6: //Consulta de Saldo.
                    ccc = null;
                    cuenta = null;

                    System.out.printf("Consulta de saldo: \n");

                    // Número de cuenta
                    cuentaValida = false;
                    while (!cuentaValida) {
                        System.out.println("Número de cuenta: (debe ser una cuenta válida)");
                        ccc = Util_ES.lecturaTeclado();
                        cuentaValida = CuentaBancaria.validarCuenta(ccc);
                        if (!cuentaValida) {
                            System.err.println("Cuenta no válida.");
                        }
                    }
                    double saldo=banco.obtenerSaldo(ccc);
                    if (saldo==-1)
                        System.out.println ("La cuenta no existe");
                    else
                        System.out.printf("El saldo actual de la cuenta %s es %.2f.\n", ccc, saldo);
                    
                    break;
                case 7: //Eliminar Cuenta.
                    ccc = null;
                    cuenta = null;

                    System.out.printf("Consulta de saldo: \n");

                    // Número de cuenta
                    cuentaValida = false;
                    while (!cuentaValida) {
                        System.out.println("Número de cuenta: (debe ser una cuenta válida)");
                        ccc = Util_ES.lecturaTeclado();
                        cuentaValida = CuentaBancaria.validarCuenta(ccc);
                        if (!cuentaValida) {
                            System.err.println("Cuenta no válida.");
                        }
                    }
                    if (!banco.eliminarCuenta(ccc))
                        System.out.println ("La cuenta no existe o su saldo no es 0.");
                    else
                        System.out.printf("La cuenta %s ha sido eliminada\n", ccc);
                    
                    break;    
                case 0:
                    System.out.printf("Fin de la aplicación...\n");
                    fin = true;
                    break;
                default:
                    System.out.println("Selecciona una opción válida.");
            }

            System.out.println("Pulsa alguna tecla para continuar.\n");
            Util_ES.pulsacionTecla();

        }

    }
}

/**
 * Ejemplos de CCC para probar
 *
 * 8605 9125 19 2613850743 7107 4715 75 3842620046 6113 1552 54 5826265494 2402
 * 4739 14 8234148159 4725 9872 69 2768313769 2738 6082 46 6444242567 6651 0642
 * 31 1434863609 2569 9898 50 6573131541 4834 5538 37 8693471927 2072 2346 41
 * 9370377643
 */
