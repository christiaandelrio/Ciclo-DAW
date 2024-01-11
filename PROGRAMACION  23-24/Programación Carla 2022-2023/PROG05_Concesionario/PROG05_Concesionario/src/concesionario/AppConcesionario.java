/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario;

import Inputs.InputMethod;
import java.util.Calendar;

/**
 *
 * @author xavi
 */
public class AppConcesionario {
    public static final int MAX_VEHICULOS=50;
    public static final int MAX_CLIENTES=50;
    public static final int MAX_VENTAS=50;
    
    private static void altaVehiculo() {
        Vehiculo v=formVehiculo();
        if (v!=null) {
            
        } else System.out.println("A matricula non é válida");
    }
    
    private static Vehiculo formVehiculo() {
        Vehiculo v=null;
        
        InputMethod.showSuliñado("Datos do Novo Vehiculo");
        String matricula=InputMethod.getString("Matricula: ");
        
        // Comprobamos validez Matricula.
        if (Vehiculo.matriculaValida(matricula)) {
            String marca=InputMethod.getString("Marca: ");
            String descricion=InputMethod.getString("Descrición: ");
            int precio=InputMethod.getInt("Precio: ");
            v=new Vehiculo(matricula,marca,descricion,precio);
        } 
        return v;
    }    
    
    private static Cliente formCliente() {
        Cliente cliente=null;

        InputMethod.showSuliñado("Datos do Novo Cliente");
        String dni=InputMethod.getString("DNI: ");
        if (Cliente.dniValido(dni)) {
            String nome=InputMethod.getString("Nome e Apelidos: ");
            if (Cliente.nomeValido(nome)) cliente=new Cliente(dni,nome);
        }
        return cliente;
    }
    
    
    private static void listadoVehiculos(Concesionario c) {
        InputMethod.showSuliñado("Listado de Vehiculos");
        Vehiculo[] lv=c.getVehiculos();
        
        for(int idx=0;idx<lv.length;idx++) {
            if (lv[idx]!=null) System.out.println(lv[idx]);
        }
        InputMethod.getString("\nPulsa unha tecla para continuar...");
    }

    private static Vehiculo busquedaVehiculo(Concesionario c) {
        InputMethod.showSuliñado("Búsqueda de Vehiculo");
        String matricula=InputMethod.getString("Matricula: ");
        return c.getVehiculo(matricula);
    }

    private static void ventaVehiculo(Concesionario concesionario) {
        InputMethod.showSuliñado("Venta de Vehiculo");
        String dni=InputMethod.getString("DNI: ");
        Cliente cliente=concesionario.getCliente(dni);
        if (cliente==null) cliente=formCliente();
        if (cliente!=null) {
            if (concesionario.addCliente(cliente)) {
                String matricula=InputMethod.getString("Matricula: ");
                Vehiculo v=concesionario.getVehiculo(matricula);
                if (v!=null) {
                    Venta venta=new Venta(Calendar.getInstance(),cliente,v);
                    concesionario.addVenta(venta);
                }
            }
        }
    }

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int op;
        Vehiculo v;
        String[] menu={ "1.- Nuevo Vehiculo","2.- Listar Vehiculos",
                        "3.- Buscar Vehiculo", "4.- Modificar Kilómetros",
                        "5.- Vender Vehiculo",
                        "6.- Saír" };
        
        Concesionario c=new Concesionario(MAX_VEHICULOS,MAX_CLIENTES,MAX_VENTAS);
        do {
            op=InputMethod.getOpcion("Concesionario ACME",menu,"123456");
            switch(op) {
                case 1: 
                    v=formVehiculo();
                    if (v!=null) {
                        c.addVehiculo(v);
                    } else System.out.println("Matricula Errónea");
                    break;
                case 2: listadoVehiculos(c); break;
                case 3: 
                    v=busquedaVehiculo(c); 
                    if (v!=null) System.out.println(v);
                    else System.out.println("O vehículo non existe");
                    break;
                case 4: 
                    v=busquedaVehiculo(c);
                    if (v!=null) {
                        System.out.println(v);
                        int kms=InputMethod.getInt("Kilometros: ");
                        v.setKilómetros(kms);
                    }
                    break;
                case 5:
                    ventaVehiculo(c);
            }
        } while(op!=6);
    }
      
}
