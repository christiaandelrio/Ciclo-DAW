/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import java.util.function.Predicate;

/**
 *
 * @author yo
 */
//V objeto
//K key o atributo
public interface Storage<K,V> {
    //Métodos de la interfaz a implementar
    public V store(V objeto)throws VerboseException;
    public V delete(K atributo)throws VerboseException;
    public V[] remove (Predicate<V> filter) throws VerboseException;
    public V[] search (Predicate<V> filter) throws VerboseException;
    public V find(K atributo) throws VerboseException;
    public V[] all();
}
