/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal;

/**
 *
 * @author Carla Portela Ubeira
 * En esta clase se guardaran las instancias de la clase Vehiculo
 */
public class Concesionario {
    
    //Atributos
    private static Cliente[] clientes;//Array de instancias de clase Cliente
    private static Venta[] ventas;//Array de instancias de clase Venta
    private static Vehiculo[] vehiculos;//Array de instancias de la clase Vehiculo
    
    private static int numClientes;
    private static int numVentas;
    
    //Constructor
    public Concesionario(int max_vehiculos, int max_clientes, int max_ventas) {
        this.vehiculos= new Vehiculo[max_vehiculos];
        this.clientes = new Cliente[max_clientes];
        this.ventas = new Venta[max_ventas];
        this.numClientes = 0;
        this.numVentas = 0;
    }
    //Devuelve true, si consigue añadir el vehículo
    public static boolean addVehiculo(Vehiculo v) {
        boolean retorno = false;
        int idx = 0;
        //Antes de añadir un vehículo comprobamos si no se sobrepasa el límite máximo de elementos en el concesionario y si existe algún index vacio
        while((idx < vehiculos.length) && (vehiculos[idx] == null)){
            if (idx < vehiculos.length) {
                vehiculos[idx] = v;
                idx++;
                System.out.println("Se ha añadido el Nuevo Vehñiculo");
                retorno = true;
            } else {
            System.out.println("Se ha excedido el número máximo de coches\nNo se ha podido añadir el Nuevo Vehículo");
            }
        }
        return retorno;
    }
    
    public Vehiculo[] getVehiculos() {
        return vehiculos;
    }
    
    public boolean delVehiculo(Vehiculo vehiculo){
      //Recorremos el array vehiculos
        boolean ok = false;
        for(int idx=0;idx<vehiculos.length;idx++){
        if((vehiculos[idx]!=null)&&(vehiculos[idx].getMatricula().equals(vehiculo.getMatricula()))){  
            vehiculos[idx] = null;
            ok = true;
            System.out.println("El Vehículo ha sido eliminado");
            } 
        }
        return ok;
    }
    
    public Vehiculo getVehiculo(String matricula) {
        //Recorremos el array vehiculos
        for(int idx=0;idx<vehiculos.length;idx++){
            if((vehiculos[idx]!=null) && (vehiculos[idx].getMatricula().equals(matricula))){
            return vehiculos[idx];
            }
        }
        return null;
    }

    public Cliente getCliente(String dni) {
        for(int idx=0;idx<clientes.length;idx++){
            if((clientes[idx]!=null) && (clientes[idx].getDni().equals(dni))){
            return clientes[idx];
            }
        }
        return null;
    }

    public boolean addVenta(Venta venta) {
        boolean ok = false;
        int max = ventas.length;
        if(numVentas < max) {
            ok = delVehiculo(venta.getVehiculo());
            if(ok){
                ventas[numVentas] = venta;
                numVentas++;
                System.out.println("Se ha añadido una nueva venta");
                System.out.println("Se ha eliminado el vehículo " +venta.getVehiculo() +" del concesionario");
            }
        }
        return ok;
    }

    public boolean addCliente(Cliente cliente) {
       boolean ok = false;
        int max = clientes.length;
        if(numClientes < max) {
                clientes[numClientes] = cliente;
                numClientes++;
                ok = true;
                System.out.println("Se ha añadido una Nueva Cliente");
            }
        return ok; 
    }
}
