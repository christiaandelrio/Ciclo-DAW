package principal;

import funcións.Artigo;
import funcións.Cliente;
import funcións.Database;
import funcións.Printer;
import funcións.Venta;
import funcións.VerboseException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *  private final LocalDate data;
    private final String codigoArtigo;
    private final String dniCliente;
    private final int unidades;
    private final double prezo;
 */
public class VentaPrinter implements Printer<Venta> {

    @Override
    public String[] toCSV(Collection<Venta> list) {
        ArrayList<String> csv=new ArrayList<>();
        csv.add("data,código artigo,dni cliente,unidades,prezo");
        list.forEach((c) -> csv.add(toRow(c)));
        /* Alternativamente....
            for(Cliente c:list) csv.add(toRow(c));
        */
        return csv.toArray(new String[0]);
    }

    @Override
    public String toRow(Venta v) {
        StringBuilder row=new StringBuilder(v.getData().toString()).append(",")
                .append(v.getCodigoArtigo()).append(",")
                .append(v.getDniCliente()).append(",")
                .append(v.getUnidades()).append(",")
                .append(v.getPrezo());
        return row.toString();
    }

    @Override
    public String print(Venta v) throws VerboseException {
        Artigo art=Database.tartigos.find(v.getCodigoArtigo());
        Cliente cl=Database.tclientes.find(v.getDniCliente());
        return String.format("%s\n%-20s %d %.2f€",cl,art,v.getUnidades(),v.getUnidades()*v.getPrezo());
    }
}
