package funcións;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Factura {
    private String codigo;
    final LocalDate dataFactura;
    final String dniCliente;
    final Collection<Venta> ventas;
    double importe;
    double ive;
    double total;
    private boolean pechada;
    
    Factura(LocalDate data,String dni) {
        this.dataFactura=data;
        this.dniCliente=dni;
        this.ventas=new ArrayList<>();
        this.pechada=false;
    }
    
    public static Factura[] fromCSV(String[] csv) throws Exception {
        HashMap<String,Factura> list=new HashMap<>();
        try {
            HashMap<String,String> map=new HashMap<>();
            String[] header=csv[0].split(",");
            for(int idx=1;idx<csv.length;idx++) {
                String[] line=csv[idx].split(",");
                for(int f=0;f<header.length;f++) map.put(header[f],line[f]);
                String codigo=map.get("código factura");
                if (!codigo.equals("")) {
                    Factura f=list.get(codigo);
                    if (f==null) {
                        f=new Factura(LocalDate.parse(map.get("data factura")),map.get("dni cliente"));
                        f.codigo=codigo;
                        f.importe=Double.parseDouble(map.get("importe"));
                        f.ive=Double.parseDouble(map.get("ive"));
                        f.total=Double.parseDouble(map.get("total"));
                        f.pechada=true;
                        list.put(codigo, f);
                    }
                    Venta v=new Venta(LocalDate.parse(map.get("data venta")),
                                    map.get("código artigo"),map.get("dni cliente"),
                                    Integer.parseInt(map.get("unidades")),Double.parseDouble(map.get("prezo")));
                    f.ventas.add(v);
                }
            }
        } catch(NumberFormatException e) {
            throw new VerboseException(e);
        } 
        return list.values().toArray(new Factura[0]);
    }
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getIve() {
        return ive;
    }

    public void setIve(double ive) {
        this.ive = ive;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isPechada() {
        return pechada;
    }

    public void setPechada(boolean pechada) {
        this.pechada = pechada;
    }

    public LocalDate getDataFactura() {
        return dataFactura;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public Collection<Venta> getVentas() {
        return ventas;
    }
    
    public void addVenta(Venta v) throws VerboseException {
        if (pechada) throw new VerboseException("Erro engadindo venta","A factura xa está pechada, non se poden engadir novas ventas");
        if (!v.getDniCliente().equals(dniCliente)) 
            throw new VerboseException("Erro engadindo venta","O cliente da venta non se corresponde coa factura");
        ventas.add(v);
    }
    
    void pechaFactura(int num) throws VerboseException {
        LocalDate date=LocalDate.now();
        do {
            codigo=String.format("F%2d%02d%04d",date.getYear()%100,date.getMonthValue(),num);
            num++;
        } while(Database.tfacturas.find(codigo)!=null);
        importe=0;
        ive=0;
        total=0; 
        for(Venta v: ventas) {
            Artigo a=Database.tartigos.find(v.getCodigoArtigo());
            if (a==null) throw new VerboseException("Erro procesando factura","Non se atopa o artigo na base de datos");
            importe+=v.getUnidades()*a.getPrezo();
        }
        ive=importe*0.21;
        total=importe+ive;
        pechada=true;
    }

    @Override
    public String toString() {
        return dataFactura + " "+ codigo +" "+dniCliente +  total + "€";
    }
}
