/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import funciones.Artigo;
import funciones.Printer;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author yo
 */
public class ArtigoPrinter implements Printer<Artigo> {

    @Override
    public String[] toCSV(Collection<Artigo> list) {
        ArrayList<String> csv=new ArrayList<>();
        csv.add("código,denominacion,descripción,precio");
        list.forEach((a) -> csv.add(toRow(a)));
        //Imprime el array resultante
        return csv.toArray(new String[0]);
    }

    @Override
    public String toRow(Artigo a) {
        StringBuilder row=new StringBuilder(a.getCodigo()).append(",")
                .append(clean(a.getDenominacion())).append(",")
                .append(clean((String)mapNULL(a.getDescripcion()))).append(",")
                .append(String.format("%.4f",a.getPrezo()));
        return row.toString();
    }

    @Override
    public String print(Artigo a) {
        return a.getCodigo() + ":\n" + a.getDenominacion()+ "\t" + a.getDescripcion() +"\nPrecio: "+ a.getPrezo()+ " ?";
    }

    
    
}
