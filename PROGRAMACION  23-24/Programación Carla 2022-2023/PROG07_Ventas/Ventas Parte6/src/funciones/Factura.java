/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import static funciones.DataBase.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author yo
 */
public class Factura {
    protected String codigo;
    //No pueden modificarse una vez establecidos, la fecha, el DNI del cliente y la colección ventas
    protected final LocalDate dataFactura;
    protected final String dniCliente;
    protected final Collection<Venta>ventas;
    protected double importe;
    protected double iva;
    protected double total;
    protected boolean cerrada;
    
    //Constructor
    protected Factura(LocalDate dataFactura, String dniCliente) {
        this.dataFactura = dataFactura;
        this.dniCliente = dniCliente;
        this.codigo = null;
        this.ventas = new ArrayList<>();//El contenido de la coleccion ventas puede modificarse una vez establecido
        this.importe = 0;
        this.iva = 0;
        this.total=0;
        this.cerrada = false;
    }
    
    //Métodos setter y getter
    public String getCodigo() {
        return codigo;
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

    public double getImporte() {
        return importe;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public boolean isCerrada() {
        return cerrada;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public void setCerrada(boolean cerrada) {
        this.cerrada = cerrada;
    }
    //Para añadir la venta comprobamos que la venta no esté previamente cerrada y que el DNI del cliente se corresponde con el de la factura
    public void addVenta(Venta v) throws VerboseException{
        if(cerrada==true){
            throw new VerboseException("Error añadiendo la venta","La venta está cerrada y no se puede añadir a ventas");
        } else if(!v.getDniCliente().equals(dniCliente)){
            throw new VerboseException("Error añadiento la venta","El cliente de la venta no se corresponde con el de la factura");
        }
        ventas.add(v);
    }
    
    void cierraFactura(int num) throws VerboseException {
        LocalDate date=LocalDate.now();
        do {
            codigo=String.format("F%2d%02d%04d",date.getYear()%100,date.getMonthValue(),num);
            num++;
        } while(totalFacturas.find(codigo)!=null);
        importe=0;
        iva=0;
        total=0; 
        for(Venta v: ventas) {
            Artigo a=totalArtigos.find(v.getCodigoArtigo());
            if (a==null) throw new VerboseException("Erro procesando factura","Non se atopa o artigo na base de datos");
            importe+=v.getUnidades()*a.getPrezo();
        }
        iva=importe*0.21;
        total=importe+iva;
        cerrada=true;
    }

    @Override
    public String toString(){
        return dataFactura + " " + codigo + " " + dniCliente + " " + total + " ?";
    }
}
