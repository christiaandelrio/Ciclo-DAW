package funcións;

import java.util.TreeMap;
import java.util.function.Predicate;

public class ClientesStorage implements Storage<String,Cliente> {
    private final TreeMap<String,Cliente> store=new TreeMap<>();

    @Override
    public Cliente store(Cliente data) throws VerboseException {
        return store.put(data.getDni(),data);
    }

    @Override
    public Cliente delete(String key) throws VerboseException {
        return store.remove(key);
    }

    @Override
    public Cliente find(String key) throws VerboseException {
        return store.get(key);
    }

    @Override
    public Cliente[] remove(Predicate<Cliente> filter) throws VerboseException {
        Cliente[] toremove=search(filter);
        for(Cliente c: toremove) {
            delete(c.getDni());
        }
        return toremove;
    }

    @Override
    public Cliente[] search(Predicate<Cliente> filter) throws VerboseException {
        return store.values().stream().filter(filter).toArray(Cliente[]::new);
        /*** Alternativamente....
        Collection<Cliente> data=store.values();
        ArrayList<Cliente> result=new ArrayList<>();
        for(Cliente c: data) {
            if (filter.test(c)) result.add(c);
        }
        return result.toArray(new Client[0]);
        */
    }

    @Override
    public Cliente[] all() throws VerboseException {
        return store.values().toArray(new Cliente[0]);
    }
}
