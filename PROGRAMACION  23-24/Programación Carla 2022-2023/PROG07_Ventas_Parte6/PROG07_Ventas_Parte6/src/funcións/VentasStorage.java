package funcións;

import java.util.ArrayList;
import java.util.function.Predicate;

public class VentasStorage implements Storage<Object,Venta> {
    private final ArrayList<Venta> store=new ArrayList<>();
    
    @Override
    public Venta store(Venta data) throws VerboseException {
        if (store.add(data)) {
            Database.stock.addStock(data.getCodigoArtigo(), -data.getUnidades());
            return data;
        }
        return null;
    }

    @Override
    public Venta delete(Object key) throws VerboseException {
        throw new UnsupportedOperationException("Non é posible eliminar unha venta por chave");
    }

    @Override
    public Venta find(Object key) throws VerboseException {
        throw new UnsupportedOperationException("Non é posible localizar unha Venta por chave");
    }

    @Override
    public Venta[] remove(Predicate<Venta> filter) throws VerboseException {
        Venta[] toremove=search(filter);
        for(Venta a: toremove) {
            store.remove(a);
        }
        return toremove;
    }

    @Override
    public Venta[] search(Predicate<Venta> filter) throws VerboseException {
        return store.stream().filter(filter).toArray(Venta[]::new);
        /*** Alternativamente....
        ArrayList<Venta> result=new ArrayList<>();
        for(Venta v: store) {
            if (filter.test(v)) result.add(v);
        }
        return result.toArray(new Venta[0]);
        */    
    }

    @Override
    public Venta[] all() throws VerboseException {
        return store.toArray(new Venta[0]);
    }
}
