/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import funciones.Artigo;
import funciones.Cliente;
import static funciones.DataBase.*;
import funciones.Printer;
import funciones.Venta;
import funciones.VerboseException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author yo
 */
public class VentaPrinter implements Printer<Venta> {

    @Override
    public String[] toCSV(Collection<Venta> list) {
        ArrayList<String> csv=new ArrayList<>();
        csv.add("fecha,código artigo,dni cliente,unidades,precio");
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
        Artigo art=totalArtigos.find(v.getCodigoArtigo());
        Cliente cl=totalClientes.find(v.getDniCliente());
        return String.format("%s\n%-20s %d %.2f?",cl,art,v.getUnidades(),v.getUnidades()*v.getPrezo());
    }
    
}
