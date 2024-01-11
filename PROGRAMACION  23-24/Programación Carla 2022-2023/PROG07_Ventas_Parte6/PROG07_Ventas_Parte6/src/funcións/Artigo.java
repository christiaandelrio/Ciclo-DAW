package funcións;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Artigo implements Comparable<Artigo> {
    private final String codigo;
    private final String denominacion;
    private String descricion;
    private double prezo;
    
    public Artigo(String codigo,String denominacion,double precio) {
        this.codigo=codigo;
        this.denominacion=denominacion;
        this.prezo=precio;
    }
    
    public static Artigo[] fromCSV(String[] csv) throws VerboseException {
        HashMap<String,String> map=new HashMap<>();
        ArrayList<Artigo> list=new ArrayList<>();
        String[] header=csv[0].split(",");
        for(int idx=1;idx<csv.length;idx++) {
            String[] line=csv[idx].split(",");
            for(int f=0;f<header.length;f++) map.put(header[f],line[f]);
            Artigo a=new Artigo(map.get("código"),map.get("denominacion"),Double.parseDouble(map.get("prezo")));
            a.setDescricion(map.get("descrición"));
            list.add(a);
        }
        return list.toArray(new Artigo[0]);
    }

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public double getPrezo() {
        return prezo;
    }

    public void setPrezo(double prezo) {
        this.prezo = prezo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Artigo other = (Artigo) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return codigo + ": " + denominacion + "("+ prezo + "€)";
    }

    @Override
    public int compareTo(Artigo t) {
        return codigo.compareTo(t.codigo);
    }
    
    
}
