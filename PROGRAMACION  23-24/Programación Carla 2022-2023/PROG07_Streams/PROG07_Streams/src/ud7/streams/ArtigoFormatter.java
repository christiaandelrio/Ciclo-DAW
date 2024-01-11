package ud7.streams;

public class ArtigoFormatter implements Formatter<Artigo> {

    @Override
    public String format(Artigo object) {
        StringBuilder str=new StringBuilder("Codigo: ").append(object.getCodigo())
                .append("\nDenominación: ").append(object.getDenominacion())
                .append("\nPrezo: ").append(String.format("%.4f",object.getPrezo()));
        return str.toString();
    }
    
}
