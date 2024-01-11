/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;


import static funciones.DataBase.*;
import java.util.ArrayList;
import java.util.function.Predicate;


/**
 *
 * @author yo
 */
public class VentasStorage implements Storage<Object,Venta>{
    //Creamos un array asociativo de objetos Venta
    private final ArrayList<Venta>store=new ArrayList<>();
    
    @Override
    public Venta store(Venta objeto) throws VerboseException {
         if (store.add(objeto)) {
            stocks.addStock(objeto.getCodigoArtigo(), -objeto.getUnidades());
            return objeto;
        }
        return null;
    }

    @Override
    public Venta delete(Object atributo) throws VerboseException {
        throw new UnsupportedOperationException("No se puede eliminar la venta por clave de búsqueda " + atributo);
    }

    @Override
    public Venta[] remove(Predicate<Venta> filter) throws VerboseException {
        Venta[] ventasDelete=search(filter);
        for(Venta v:ventasDelete){
            store.remove(v);
        }
        return ventasDelete;
    }

    @Override
    public Venta[] search(Predicate<Venta> filter) throws VerboseException {
        return store.stream().filter(filter).toArray(Venta[]::new);
    }

    @Override
    public Venta find(Object atributo) throws VerboseException {
        throw new UnsupportedOperationException("No se puede eliminar la venta por clave de búsqueda " + atributo);
    }

    @Override
    public Venta[] all() {
        return store.toArray(new Venta[0]);
    }
    

}
