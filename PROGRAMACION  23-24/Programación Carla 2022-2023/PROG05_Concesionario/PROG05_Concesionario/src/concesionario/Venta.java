/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario;

import java.util.Calendar;


/**
 *
 * @author xavi
 */
class Venta {
    private Calendar data;
    private Cliente cliente;
    private Vehiculo vehiculo;
    
    
    public Venta(Calendar data, Cliente cliente, Vehiculo vehiculo) {
        this.data = data;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    
}
