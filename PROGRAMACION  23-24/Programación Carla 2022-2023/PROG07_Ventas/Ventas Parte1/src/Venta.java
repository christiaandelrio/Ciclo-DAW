
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yo
 */
public class Venta implements Comparable<Venta>{
    private final LocalDate data;
    private final String codigoArtigo;
    private final String dniCliente;
    private final int unidades;
    private final double prezo;
    
    //Constructor

    protected Venta(LocalDate data, String codigoArtigo, String dniCliente, int unidades, double prezo) {
        this.data = data;
        this.codigoArtigo = codigoArtigo;
        this.dniCliente = dniCliente;
        this.unidades = unidades;
        this.prezo = prezo;
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
    public String toString(){
       return data.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ", " + dniCliente + ", "+ codigoArtigo + ", " + unidades; 
    }

    @Override
    public int compareTo(Venta v) {
        return data.compareTo(v.data);
    }
}
