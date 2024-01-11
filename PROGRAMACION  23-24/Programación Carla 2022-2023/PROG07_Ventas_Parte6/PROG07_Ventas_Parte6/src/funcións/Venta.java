package funcións;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;

public class Venta implements Comparable<Venta> {
    private final LocalDate data;
    private final String codigoArtigo;
    private final String dniCliente;
    private final int unidades;
    private final double prezo;
    
    Venta(LocalDate data,String codigoArtigo,String dniCliente,int unidades,double prezo) {
        this.data=data;
        this.codigoArtigo=codigoArtigo;
        this.dniCliente=dniCliente;
        this.unidades=unidades;
        this.prezo=prezo;
    }
    
    public static Venta[] fromCSV(String[] csv) throws Exception {
        ArrayList<Venta> list=new ArrayList<>();
        try {
            VentaBuilder builder=new VentaBuilder();
            HashMap<String,String> map=new HashMap<>();
            String[] header=csv[0].split(",");
            for(int idx=1;idx<csv.length;idx++) {
                String[] line=csv[idx].split(",");
                for(int f=0;f<header.length;f++) map.put(header[f],line[f]);
                builder.data=LocalDate.parse(map.get("data"));
                builder.codigoArtigo=map.get("código artigo");
                builder.dniCliente=map.get("dni cliente");
                builder.unidades=Integer.parseInt(map.get("unidades"));
                builder.prezo=Double.parseDouble(map.get("prezo"));
                list.add(builder.build());
            }
        } catch(NumberFormatException e) {
            throw new VerboseException(e);
        } 
        return list.toArray(new Venta[0]);
    }


    public LocalDate getData() {
        return data;
    }

    public String getCodigoArtigo() {
        return codigoArtigo;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public int getUnidades() {
        return unidades;
    }

    public double getPrezo() {
        return prezo;
    }

    @Override
    public String toString() {
        return data.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))+","+dniCliente+","+codigoArtigo+","+unidades;
    }

    @Override
    public int compareTo(Venta t) {
        return data.compareTo(t.data);
    }
}
