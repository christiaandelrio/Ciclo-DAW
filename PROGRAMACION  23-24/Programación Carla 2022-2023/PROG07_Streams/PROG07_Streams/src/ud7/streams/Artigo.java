package ud7.streams;

import java.util.Objects;

public class Artigo implements Comparable<Artigo> {
    private final String codigo;
    private final String denominacion;
    private double prezo;

    public Artigo(String codigo, String denominacion, double prezo) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.prezo = prezo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public double getPrezo() {
        return prezo;
    }

    public void setPrezo(double prezo) {
        this.prezo = prezo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.codigo);
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
        return this.codigo.equals(other.codigo);
    }

    @Override
    public int compareTo(Artigo t) {
        return denominacion.compareTo(t.denominacion);
    }
    
    @Override
    public String toString() {
        return codigo+": "+denominacion+" ("+String.format("%4f",prezo)+"€)";
    }
}
