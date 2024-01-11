
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yo
 */
public class Artigo implements Comparable<Artigo>{
    private final String codigo;
    private final String denominacion;
    private String descripcion;
    private double prezo;
    
    //Constructor(recibe como parámteros codigo, denominación, descripción y precio)

    public Artigo(String codigo, String denominacion, String descripcion) {
        this.codigo = codigo;
        this.denominacion = denominacion;
        this.descripcion = descripcion;
        this.prezo=0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrezo() {
        return prezo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    //Dos artículos se consideran iguales si sus códigos coinciden
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
    public String toString(){
        return codigo + ": " +denominacion + " (" + prezo +" ?)";  
    }
    
    @Override
    public int compareTo(Artigo a){
        return codigo.compareTo(a.codigo);
    }
    
}



