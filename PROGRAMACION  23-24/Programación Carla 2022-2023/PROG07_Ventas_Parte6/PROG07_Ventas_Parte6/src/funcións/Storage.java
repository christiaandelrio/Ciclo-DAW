package funcións;

import java.util.function.Predicate;

public interface Storage<K,V> {
    public V store(V data) throws VerboseException;
    public V delete(K key) throws VerboseException;
    public V find(K key) throws VerboseException;
    public V[] remove(Predicate<V> filter) throws VerboseException;
    public V[] search(Predicate<V> filter) throws VerboseException;
    public V[] all() throws VerboseException;
}
