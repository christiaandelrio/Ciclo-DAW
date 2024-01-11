package funcións;

import java.util.HashMap;

public class ArtigoStock implements Stock<String,Integer> {
    private final HashMap<String,Integer> store=new HashMap<>();
    
    @Override
    public Integer getStock(String code) {
        Integer r=store.get(code);
        if (r==null) return 0;
        return r;
    }

    @Override
    public void addStock(String code,Integer units) throws VerboseException {
        int tot;
        Integer r=store.get(code);
        if (r==null) tot=units;
        else         tot=units+r;
        if (tot<0) throw new VerboseException("Erro en Stock","O Stock non pode ser menor de 0");
        store.put(code, tot);
    }
    
    @Override
    public void setStock(String code,Integer units) throws VerboseException {
        if (units<0) throw new VerboseException("Erro en Stock","O Stock non pode ser menor de 0");
        store.put(code,units);
    }
}
