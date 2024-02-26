/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ciclo.prog07_tarea;

/**
 * La clase Banco tiene como función gestionar cuentas bancarias (crearlas, modificarlas, mostrar info...) para ello está dotada de los métodos necesarios.
 * @author Sergio García López
 */
public class Banco {

    private final int NUM_MAX_CUENTAS = 100; //Constante con el número máximo de cuentas que se pueden crear.
    private CuentaBancaria[] cuentas; //Array que almacenará las cuentas bancarias que se creen.
    private int numeroCuentas; //Contador de las cuentas bancarias creadas.

    //Constructor de la clase Banco. Se encarga de instanciar el array cuentas y poner el contador de cuentas bancarias a cero.
    public Banco() {
        this.cuentas = new CuentaBancaria[NUM_MAX_CUENTAS];
        this.numeroCuentas = 0;
    }

    //abrirCuenta: recibe por parámetro un objeto CuentaBancaria y lo almacena en la estructura. Devuelve true o false indicando si la operación se realizó con éxito.
    public boolean abrirCuenta(CuentaBancaria cuentaBancaria) {
        //Primero comprobamos que queda espacio en el array:
        if (this.numeroCuentas >= NUM_MAX_CUENTAS) {
            System.out.println("El espacio está lleno. No se ha podido agregar la cuenta.");
            return false;
        }

        //Comprobamos si ya existe la cuenta con el IBAN:
        boolean existeCuenta = existeCuenta(cuentaBancaria.getIBAN());
        if (existeCuenta) {
            System.out.println("Ya existe la cuenta bancaria.");
            return false;
        }

        //Si todo va bien introducimos la cuenta en el array, incrementamos el contador y devolvemos true:
        this.cuentas[this.numeroCuentas] = cuentaBancaria;
        this.numeroCuentas++;
        return true;
    }

    //Método que sirve para decirnos si una cuenta existe o no por su IBAN, devuelve true o false.
    public boolean existeCuenta(String IBAN) {
        for (int i = 0; i < this.numeroCuentas; i++) {
            if (this.cuentas[i].getIBAN().equalsIgnoreCase(IBAN)) {
                return true;
            }
        }
        return false;
    }

    //Método que sirve para buscar una cuenta por su IBAN y que devuelve la cuenta bancaria:
    public CuentaBancaria buscarCuenta(String IBAN) {
        for (int i = 0; i < this.numeroCuentas; i++) {
            if (this.cuentas[i].getIBAN().equalsIgnoreCase(IBAN)) {
                return this.cuentas[i];
            }
        }
        return null;
    }

    //listadoCuentas: no recibe parámetro y devuelve un array donde cada elemento es una cadena que representa toda la información de una cuenta.
    public String[] listadoCuentas() {
        String[] listadoInfoCuentas = new String[this.numeroCuentas];
        for (int i = 0; i < this.numeroCuentas; i++) {
            listadoInfoCuentas[i] = this.cuentas[i].devolverInfoString();
        }
        return listadoInfoCuentas;
    }

    //listarCuentas: no recibe parámetro y devuelve un array donde cada elemento es una cadena. Sólo se devuelve de cada cuenta IBAN, titular y saldo.
    public String[] listarCuentas() {
        String[] listadoInfoCuentas = new String[this.numeroCuentas];
        for (int i = 0; i < this.numeroCuentas; i++) {
            listadoInfoCuentas[i] = ("Cuenta " + (i + 1) + ": " + "/ IBAN " + this.cuentas[i].getIBAN() + " /,  / Titular " + this.cuentas[i].getTitular().devolverInfoString() + " /,  / Saldo " + this.cuentas[i].getSaldo() + " /");
        }
        return listadoInfoCuentas;
    }

    //informacionCuenta: recibe un iban por parámetro y devuelve una cadena con la información de la cuenta o null si la cuenta no existe.
    public String informacionCuenta(String IBAN) {
        if (buscarCuenta(IBAN) != null) {
            return buscarCuenta(IBAN).devolverInfoString();
        }
        return null;
    }

    //ingresoCuenta: recibe un iban por parámetro y una cantidad e ingresa la cantidad en la cuenta. Devuelve true o false indicando si la operación se realizó con éxito o no.
    public boolean ingresoCuenta(String IBAN, double cantidad) {
        //Primero buscamos la cuenta (el método buscarCuenta devuelve una cuenta o null).
        CuentaBancaria cuenta = buscarCuenta(IBAN);
        //Si existe ingresamos el dinero
        if (cuenta != null) {
            Double saldoExistente = cuenta.getSaldo(); //Obtengo el saldo existente.
            cuenta.setSaldo(saldoExistente + Math.abs(cantidad)); //Sumo la cantidad al saldo existente (con Math.abs me aseguro de que siempre sea una cantidad positiva).
            return true;
        }
        //En caso contrario devolvemos false.
        return false;
    }

    //retiradaCuenta: recibe un iban por parámetro y una cantidad y trata de retirar la cantidad de la cuenta. Devuelve true o false indicando si la operación se realizó con éxito.
    public boolean retiradaCuenta(String IBAN, double cantidad) {
        //Primero vamos a comprobar que exista la cuenta.
        CuentaBancaria cuenta = buscarCuenta(IBAN);
        //Si la cuenta existe:
        if (cuenta != null) {
            //Obtengo el saldo de la cuenta
            double saldo = cuenta.getSaldo();

            //variable que va a servir para controlar si se puede o no retirar la cantidad.
            boolean sePuedeRetirar = false;

            //Comprobamos que la cantidad a sacar sea menor que el saldo. Si el saldo es mayor a la cantidad a retirar, podemos retirar.
            if (saldo > cantidad) {
                sePuedeRetirar = true;
                //En caso contrario comprobamos si la cuenta es de tipo Corriente Empresa por que en ese caso podemos retirar con límite máximo descubierto.
            } else if (cuenta instanceof CuentaCorrienteEmpresa) {
                //Creamos la cuentaCorrienteEmpresa parseando la cuenta. De esa forma tenemos acceso a los métodos de CuentaCorrienteEmpresa
                CuentaCorrienteEmpresa cuentaCorrienteEmpresa = (CuentaCorrienteEmpresa) cuenta;
                //Ahora vamos a comprobar que al retirar la cantidad el descubierto sea menor o igual al máximo admisible. En caso afirmativo podemos retirar la cantidad.
                if (Math.abs(cuenta.getSaldo() - cantidad) <= cuentaCorrienteEmpresa.getMaximoDescubierto()) {
                    sePuedeRetirar = true;
                }
            }

            //Si todo ok retiramos la cantidad de dinero
            if (sePuedeRetirar) {
                cuenta.setSaldo(saldo - cantidad);
            }
            return sePuedeRetirar;
        }
        return false;
    }

    // obtenerSaldo: Recibe un iban por parámetro y devuelve el saldo de la cuenta si existe. En caso contrario devuelve -1.
    public double obtenerSaldo(String IBAN) {
        //Primero buscamos la cuenta (el método buscarCuenta devuelve una cuenta o null).
        CuentaBancaria cuenta = buscarCuenta(IBAN);
        //Si existe la cuenta devolvemos su saldo.
        if (cuenta != null) {
            return cuenta.getSaldo();
        }
        //En caso contrario devolvemos null.
        return -1;
    }

}
