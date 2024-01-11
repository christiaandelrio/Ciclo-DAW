package funcións;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class FacturasStorage implements Storage<String,Factura> {
    private final ArrayList<Factura> store=new ArrayList<>();
    
    @Override
    public Factura store(Factura data) throws VerboseException {
        int pos=store.size();
        data.pechaFactura(pos);
        Collection<Venta> ventas=data.getVentas();
        for(Venta v:ventas) Database.tventas.remove((venta)->(venta==v));
        if (store.add(data)) return data;
        return null;
    }

    @Override
    public Factura delete(String key) throws VerboseException {
        Factura f=find(key);
        if (f!=null) store.remove(f);
        return f;
    }

    @Override
    public Factura find(String key) throws VerboseException {
        if (key!=null) {
            for(Factura f: store) {
                if (key.equals(f.getCodigo())) return f;
            }
            /** Alternativamente...
            return store.stream()
                    .filter((f)->key.equals(f.getCodigo()))
                    .limit(1).findFirst().get();
             */
        }
        return null;
    }
 
    @Override
    public Factura[] remove(Predicate<Factura> filter) throws VerboseException {
        Factura[] toremove=search(filter);
        for(Factura a: toremove) {
            store.remove(a);
        }
        return toremove;
    }

    @Override
    public Factura[] search(Predicate<Factura> filter) throws VerboseException {
        return store.stream().filter(filter).toArray(Factura[]::new);
        /*** Alternativamente....
        ArrayList<Factura> result=new ArrayList<>();
        for(Factura v: store) {
            if (filter.test(v)) result.add(v);
        }
        return result.toArray(new Factura[0]);
        */    
    }   

    @Override
    public Factura[] all() throws VerboseException {
        return store.toArray(new Factura[0]);
    }
}
