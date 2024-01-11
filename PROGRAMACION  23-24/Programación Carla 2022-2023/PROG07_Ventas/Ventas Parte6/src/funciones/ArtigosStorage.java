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
public class ArtigosStorage implements Storage<String, Artigo>{
    //Creamos un array asociativo de objetos Artigo
    private final TreeMap<String, Artigo>store=new TreeMap<>();

    @Override
    public Artigo store(Artigo objeto) throws VerboseException {
        //La key que identifica a los objetos Artigo es el código
       return store.put(objeto.getCodigo(),objeto);
    }

    @Override
    public Artigo delete(String atributo) throws VerboseException {
        return store.remove(atributo);
    }

    @Override
    public Artigo[] remove(Predicate<Artigo> filter) throws VerboseException {
        //Creamos un array de objetos Artigo a borrar que se correspondan con el filtro llamando al método search
        Artigo[] artigosDelete=search(filter);
        //Recorremos el array de objetos Artigo a borrar
        for(Artigo a:artigosDelete){
            //Los borramos llamando al metodo delete según su atributo(código);
            delete(a.getCodigo());
        }
        return artigosDelete;
    }

    @Override
    public Artigo[] search(Predicate<Artigo> filter) throws VerboseException {
        return store.values().stream().filter(filter).toArray(Artigo[]::new);
    }

    @Override
    public Artigo find(String atributo) throws VerboseException {
        return store.get(atributo);
    }

    @Override
    public Artigo[] all() {
        return store.values().toArray(new Artigo[0]);
    }
    
}
