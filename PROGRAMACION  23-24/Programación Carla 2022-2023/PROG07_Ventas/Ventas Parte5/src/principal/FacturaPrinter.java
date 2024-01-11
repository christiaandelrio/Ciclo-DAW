/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import funciones.Artigo;
import static funciones.DataBase.*;
import funciones.Factura;
import funciones.Printer;
import funciones.Venta;
import funciones.VerboseException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author yo
 */
public class FacturaPrinter implements Printer<Factura>{

    @Override
    public String[] toCSV(Collection<Factura> list) {
        ArrayList<String> csv=new ArrayList<>();
        csv.add("código de factura,fecha de factura,dni cliente,importe,iva,total,fecha de venta,código artículo,unidades,precio,total");
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
                .append(f.getIva()).append(",")
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
            Artigo art=totalArtigos.find(v.getCodigoArtigo());
            if (art==null) {
                if (error==null) error=new VerboseException("Erro formateando a factura");
                error.addMensaxe("O artigo "+v.getCodigoArtigo()+" non existe");
            } else {
                strfac.append(String.format("%-10s   %-15s   %-30s   %8d   %6.2f?   %6.2f?\n",
                        v.getData(), v.getCodigoArtigo(), art.getDenominacion(),
                        v.getUnidades(),v.getPrezo(),v.getUnidades()*v.getPrezo()));
            }
        }
        strfac.append(String.format("\n%72s%-12s %6.2f?\n%72s%-12s %6.2f?\n%72s%-12s %6.2f?\n",
               "","Importe:",f.getImporte(),"","IVE:",f.getIva(),"","Total:",f.getTotal()));
        if (error!=null) throw error;
        return strfac.toString();
    }
    
}
