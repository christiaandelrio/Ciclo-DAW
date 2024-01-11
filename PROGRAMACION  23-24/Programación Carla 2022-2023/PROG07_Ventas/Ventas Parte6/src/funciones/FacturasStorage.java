/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import static funciones.DataBase.*;
/**
 *
 * @author yo
 */
public class FacturasStorage implements Storage<String,Factura>{
    private final ArrayList<Factura>store=new ArrayList<>();
    
    @Override
    public Factura store(Factura objeto) throws VerboseException {
        //Las facturas se recuperan por su posición en el store
        int posicion=store.size();
        objeto.cierraFactura(posicion);
        Collection<Venta>ventas=objeto.getVentas();
        for(Venta v:ventas){
            totalVentas.remove((venta)->(venta==v));
        }
        if (store.add(objeto)){
            return objeto;
        }
        return null;
    }

    @Override
    public Factura delete(String atributo) throws VerboseException {
        Factura f=find(atributo);
        if(f!=null){
            store.remove(f);
        }
        return f;
    }

    @Override
    public Factura[] remove(Predicate<Factura> filter) throws VerboseException {
        Factura[] facturasDelete=search(filter);
        for(Factura f:facturasDelete){
            store.remove(f);
        }
        return facturasDelete;
    }

    @Override
    public Factura[] search(Predicate<Factura> filter) throws VerboseException {
        return store.stream().filter(filter).toArray(Factura[]::new);
    }

    @Override
    public Factura find(String atributo) throws VerboseException {
        if(atributo!=null){
            for(Factura f:store){
                if(atributo.equals(f.getCodigo())){
                    return f;
                } 
            }
        }
        return null;
    }

    @Override
    public Factura[] all() {
        return store.toArray(new Factura[0]);
    }
    
}
