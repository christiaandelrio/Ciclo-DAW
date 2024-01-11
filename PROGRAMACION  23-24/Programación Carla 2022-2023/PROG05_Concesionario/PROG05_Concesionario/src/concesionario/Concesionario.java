/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario;

/**
 *
 * @author xavi
 */
public class Concesionario {
    private final Cliente[] clientes;
    private final Venta[] ventas;
    private final Vehiculo[] vehiculos;
    
    private int nclientes;
    private int nventas;
    
    public Concesionario(int max_vehiculos,int max_clientes,int max_ventas) {
        this.vehiculos=new Vehiculo[max_vehiculos];
        this.clientes=new Cliente[max_clientes];
        this.ventas=new Venta[max_ventas];
        this.nclientes=0;
        this.nventas=0;
    }

    // true, si consigue engadir o vehiculo
    // false en outro caso.
    public boolean addVehiculo(Vehiculo v) {
        boolean res=false;
        
        int idx=0;
        while ((idx<vehiculos.length) && (vehiculos[idx]!=null)) idx++;
        
        if (idx<vehiculos.length) {
            vehiculos[idx]=v;
            res=true;
        }
        return res;
    }
    
    public Vehiculo[] getVehiculos() {
        return vehiculos;
    }
    
    public boolean delVehiculo(Vehiculo v) {
        boolean ok=false;
        
        for(int idx=0;idx<vehiculos.length;idx++) {
            if (vehiculos[idx].getMatrícula().equals(v.getMatrícula())) {
                vehiculos[idx]=null;
                ok=true;
            }
        }
        return ok;
    }

    public Vehiculo getVehiculo(String matricula) {
        for(int idx=0;idx<vehiculos.length;idx++) {
            if ( (vehiculos[idx]!=null) && vehiculos[idx].getMatrícula().equals(matricula)) return vehiculos[idx];
        }
        return null;
    }
    
    public Cliente getCliente(String dni) {
        for(int idx=0;idx<nclientes;idx++)
            if (clientes[idx].getDni().equals(dni)) return clientes[idx];
        return null;
    }

    public boolean addVenta(Venta venta) {
        boolean ok=false;
        if (nventas<ventas.length) {
            ok=delVehiculo(venta.getVehiculo());
            if (ok) {
                ventas[nventas]=venta;
                nventas++;
            }
        }
        return ok;
    }

    boolean addCliente(Cliente cliente) {
        boolean ok=false;
        if (nclientes<clientes.length) {
            clientes[nclientes]=cliente;
            nclientes++;
            ok=true;
        }
        return ok;  
    }
    
    
}
