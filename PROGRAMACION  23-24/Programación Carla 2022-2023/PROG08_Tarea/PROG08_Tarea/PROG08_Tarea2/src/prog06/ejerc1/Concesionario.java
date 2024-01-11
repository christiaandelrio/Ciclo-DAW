/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog06.ejerc1;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author
 */
public class Concesionario {

    final static int TAM_CONCESIONARIO = 50;
    //TreeSet no admite duplicados
    TreeSet<Vehiculo> concesionario;

    public Concesionario() {
        concesionario = new TreeSet();
    }

    public String buscaVehiculo(String matricula) {
        //Creamos un iterador
        Iterator<Vehiculo> it = concesionario.iterator();

        while (it.hasNext()) {
            Vehiculo veh = it.next();
            if (veh.getMatricula().equals(matricula)) {
                return veh.getMarca() + " " + veh.getMatricula() + " " + veh.getPrecio();
            }
        }

        return null;
    }

    public int insertarVehiculo(String marca, String matricula, int numkms, LocalDate fecha_mat, String descripcion, int precio, String propietario, String dni_propietario) {
        
        if (this.buscaVehiculo(matricula) != null) {
            return -2;
        } else {
            concesionario.add(new Vehiculo(marca, matricula, numkms, fecha_mat, descripcion, precio, propietario, dni_propietario));
        }
        return 0;
    }

    public void listaVehiculos() {
        
        concesionario.forEach(veh -> {
            System.out.println("Vehículo:" + veh.getMarca() + " Matrícula: " + veh.getMatricula() + " Precio: " + veh.getPrecio() + " Descripción: " + veh.getDescripcion());
        });
    }

    public boolean actualiza_kmVeh(String matricula, int kms) {
        
        Iterator<Vehiculo> it = concesionario.iterator();

        while (it.hasNext()) {
            Vehiculo veh = it.next();
            if (veh.getMatricula().equals(matricula)) {
                veh.act_kms(kms);
                return true;
            }
        }
        return false;
    }
    
    public boolean eliminaVehiculo(String matricula){
        //Creamos un iterador para recorrer el conjunto
        Iterator<Vehiculo> it=concesionario.iterator();
        
        while(it.hasNext()){
            Vehiculo del=it.next();
            //Si la matr�cula del objeto coincide con la matricula dada, borramos el objeto
            if(del.getMatricula().equals(matricula)){
                concesionario.remove(del);
                return true;
            }
        }
        return false;
    }
}
