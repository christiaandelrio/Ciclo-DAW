/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author yo
 */
public class FacturacionBuilder implements Builder<Factura[]>{
    private final HashMap<String,ArrayList<Venta>> ventas=new HashMap<>();

    public void addVenta(Venta v) {
        String dni=v.getDniCliente();
        ArrayList<Venta> lista=ventas.get(dni);
        if (lista==null) {
            lista=new ArrayList<>();
            ventas.put(dni,lista);
        }
        lista.add(v);
    }
    
    @Override
    public Factura[] build() throws Exception {
        ArrayList<Factura> facturas=new ArrayList<>();
        Set<String> clientes=ventas.keySet();
        for(String key: clientes) {
            ArrayList<Venta> lista=ventas.get(key);
            Factura f=new Factura(LocalDate.now(),key);
            for(Venta venta:lista) f.addVenta(venta);
            facturas.add(f);
         }
        return facturas.toArray(new Factura[0]);
    }
}
