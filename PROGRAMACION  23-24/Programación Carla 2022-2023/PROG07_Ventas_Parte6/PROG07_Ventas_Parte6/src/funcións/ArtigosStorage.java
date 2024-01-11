package funcións;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.function.Predicate;

public class ArtigosStorage implements Storage<String,Artigo> {
    private final TreeMap<String,Artigo> store=new TreeMap<>();
            
    @Override
    public Artigo store(Artigo data) throws VerboseException {
        return store.put(data.getCodigo(),data);
    }

    @Override
    public Artigo delete(String key) throws VerboseException {
        return store.remove(key);
    }

    @Override
    public Artigo find(String key) throws VerboseException {
        return store.get(key);
    }

    @Override
    public Artigo[] remove(Predicate<Artigo> filter) throws VerboseException {
        Artigo[] toremove=search(filter);
        for(Artigo a: toremove) {
            delete(a.getCodigo());
        }
        return toremove;
    }

    @Override
    public Artigo[] search(Predicate<Artigo> filter) throws VerboseException {
        return store.values().stream().filter(filter).toArray(Artigo[]::new);
        /*** Alternativamente....
        Collection<Artigo> data=store.values();
        ArrayList<Artigo> result=new ArrayList<>();
        for(Artigo a: data) {
            if (filter.test(a)) result.add(a);
        }
        return result.toArray(new Artigo[0]);
        */
    } 

    @Override
    public Artigo[] all() throws VerboseException {
        return store.values().toArray(new Artigo[0]);
    }
}
