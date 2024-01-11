/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import java.util.TreeMap;
import java.util.function.Predicate;

/**
 *
 * @author yo
 */
public class ClientesStorage implements Storage<String,Cliente>{
    //Creamos un array asociativo de objetos Cliente
    private final TreeMap<String,Cliente> store=new TreeMap<>();
    
    @Override
    public Cliente store(Cliente objeto) throws VerboseException {
        //La key que identifica a los clientes es el DNI
        return store.put(objeto.getDni(), objeto);
    }

    @Override
    public Cliente delete(String atributo) throws VerboseException {
        return store.remove(atributo);
    }

    @Override
    public Cliente[] remove(Predicate<Cliente> filter) throws VerboseException {
        Cliente[] clientesDelete=search(filter);
        for(Cliente c: clientesDelete) {
            delete(c.getDni());
        }
        return clientesDelete;
    }

    @Override
    public Cliente[] search(Predicate<Cliente> filter) throws VerboseException {
        return store.values().stream().filter(filter).toArray(Cliente[]::new);
    }

    @Override
    public Cliente find(String atributo) throws VerboseException {
        return store.get(atributo);
    }

    @Override
    public Cliente[] all() {
        return store.values().toArray(new Cliente[0]);
    }
    
}
