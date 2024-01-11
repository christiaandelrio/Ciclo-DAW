package principal;

import funcións.Artigo;
import funcións.Database;
import funcións.Factura;
import funcións.Printer;
import funcións.Venta;
import funcións.VerboseException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


/**
    private String codigo;
    final LocalDate dataFactura;
    final String dniCliente;
    double importe;
    double ive;
    double total;
     
    private final LocalDate data;
    private final String codigoArtigo;
    private final String dniCliente;
    private final int unidades;
 */
public class FacturaPrinter implements Printer<Factura> {

    @Override
    public String[] toCSV(Collection<Factura> list) {
        ArrayList<String> csv=new ArrayList<>();
        csv.add("código factura,data factura,dni cliente,importe,ive,total,data venta,código artigo,unidades,prezo,total");
        for(Factura f: list) {
            String[] rows=toRow(f).split("\n");
            csv.addAll(Arrays.asList(rows));
        }
        return csv.toArray(new String[0]);    
    }

    @Override
    public String toRow(Factura f) {
        StringBuilder row=new StringBuilder();
        String code=f.getCodigo();
        StringBuilder header=new StringBuilder((String)mapNULL(code)).append(",")
                .append(f.getDataFactura().toString()).append(",")
                .append(f.getDniCliente()).append(",")
                .append(f.getImporte()).append(",")
                .append(f.getIve()).append(",")
                .append(f.getTotal()).append(",");

        for(Venta v:f.getVentas()) {
            row=new StringBuilder(header);
            row.append(v.getData().toString()).append(",")
                .append(v.getCodigoArtigo()).append(",")
                .append(v.getUnidades()).append(",")
                .append(v.getPrezo()).append(",")
                .append(v.getUnidades()*v.getPrezo()).append("\n");
        }
        return row.toString();
    }

    @Override
    public String print(Factura f) throws VerboseException {
        VerboseException error=null;
        String code=f.getCodigo();
        if (code==null) code="Factura sen Pechar";
        StringBuilder strfac=new StringBuilder(String.format("Factura : %s     Data de Factura: %s\n",code,
                f.getDataFactura().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))))
            .append(new ClientePrinter().print(f.getDniCliente()))
            .append(String.format("\n%50s\n%50s\n","Detalle de Factura","------------------"))
            .append(String.format("Data         Codigo            Descrición                       Unidades   Prezo      Total\n"));
        for(Venta v:f.getVentas()) {
            Artigo art=Database.tartigos.find(v.getCodigoArtigo());
            if (art==null) {
                if (error==null) error=new VerboseException("Erro formateando a factura");
                error.addMensaxe("O artigo "+v.getCodigoArtigo()+" non existe");
            } else {
                strfac.append(String.format("%-10s   %-15s   %-30s   %8d   %6.2f€   %6.2f€\n",
                        v.getData(), v.getCodigoArtigo(), art.getDenominacion(),
                        v.getUnidades(),v.getPrezo(),v.getUnidades()*v.getPrezo()));
            }
        }
        strfac.append(String.format("\n%72s%-12s %6.2f€\n%72s%-12s %6.2f€\n%72s%-12s %6.2f€\n",
               "","Importe:",f.getImporte(),"","IVE:",f.getIve(),"","Total:",f.getTotal()));
        if (error!=null) throw error;
        return strfac.toString();
    }
}
