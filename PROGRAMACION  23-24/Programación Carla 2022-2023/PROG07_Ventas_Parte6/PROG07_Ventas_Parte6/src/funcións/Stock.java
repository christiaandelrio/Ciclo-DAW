package funcións;

public interface Stock<K,V extends Number> {
    V getStock(K codigo);
    void addStock(K codigo,V units) throws VerboseException;
    void setStock(K codigo,V units) throws VerboseException;
}
