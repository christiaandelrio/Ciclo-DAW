package principal;

import funcións.Artigo;
import funcións.Printer;
import java.util.ArrayList;
import java.util.Collection;


/**
 *  private final String codigo;
    private final String denominacion;
    private String descricion;
    private double precio;
 */
public class ArtigoPrinter implements Printer<Artigo>{

    @Override
    public String[] toCSV(Collection<Artigo> list) {
        ArrayList<String> csv=new ArrayList<>();
        csv.add("código,denominacion,descrición,prezo");
        list.forEach((a) -> csv.add(toRow(a)));
        /* Alternativamente....
            for(Artigo a:list) csv.add(toRow(a));
        */
        return csv.toArray(new String[0]);
    }

    @Override
    public String toRow(Artigo a) {
        StringBuilder row=new StringBuilder(a.getCodigo()).append(",")
                .append(clean(a.getDenominacion())).append(",")
                .append(clean((String)mapNULL(a.getDescricion()))).append(",")
                .append(String.format("%.4f",a.getPrezo()));
        return row.toString();
    }

    @Override
    public String print(Artigo a) {
        return String.format("%s: %s\n\t%s %.2f€",a.getCodigo(),a.getDenominacion(),mapNULL(a.getDescricion()),a.getPrezo());
    }
}
