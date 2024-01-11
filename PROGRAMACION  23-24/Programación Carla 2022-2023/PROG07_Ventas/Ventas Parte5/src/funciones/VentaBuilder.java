/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import static funciones.DataBase.*;
import java.time.LocalDate;

/**
 *
 * @author yo
 */
public class VentaBuilder extends BuilderAdapter<Venta>{
    //Hereda de la clase abstracta Builder para los objetos Venta
    //Atributos propios de la Clase Venta
    public LocalDate data;
    public String codigoArtigo;
    public String dniCliente;
    public int unidades;
    public double prezo;
    
    //Atributos propios de esta clase
    public Cliente cliente;
    public Artigo artigo;
    public int stock;
    
    //Constructor
    public VentaBuilder(String msgbase) {
        super("Error creando venta");
        this.data=LocalDate.now();
        this.codigoArtigo = "";
        this.dniCliente = "";
        this.unidades = 1;
        this.prezo = 0;
    }
    //Se sobreescribe el m�todo build() de la clase abstracta BuildAdapter
    @Override
    public Venta build() throws Exception {
        reset();
        checkData();
        checkArtigo();
        checkCliente();
        checkUnidades();
        checkPrezo();
        notifyErrors();
        Venta v=new Venta(data,codigoArtigo,dniCliente,unidades,prezo);
        return v;
    }
    //Se implementan los m�todos para comprobar que la informaci�n es correcta
    public boolean setData(LocalDate data) {
        this.data = data;
        return checkData();
    }

    public boolean setPrezo(double prezo) {
        this.prezo = prezo;
        return checkPrezo();
    }
    
    public boolean setCodigoArtigo(String codigoArtigo) {
        this.codigoArtigo = codigoArtigo;
        return checkArtigo();
    }

    public boolean setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
        return checkCliente();
    }

    public boolean setUnidades(int unidades) {
        this.unidades = unidades;
        return checkUnidades();
    }

    
    private boolean checkData() {
        int erridx=0;
        if (data.compareTo(LocalDate.now())>0) {
            erridx=error("Data err�nea","Non se poden facer ventas con data futura");
        }
        return erridx==0;
    }
    
    private boolean checkPrezo() {
        int erridx=0;
        if (prezo<=0) erridx=error("Prezo err�neo","O prezo debe ser maior ou igual a 0");
        return erridx==0;
    }
    
    //Completar VentaBuilder para que no permita hacer  ventas de clientes que no existan, ni art�culos que bo existan o que no tengan suficiente stock
    private boolean checkArtigo() {
        int erridx=0;
        try {
            artigo=totalArtigos.find(codigoArtigo);
            if (artigo==null) {
                erridx=error("Art�culo err�neo","No se encuentra el art�culo en la base de datos");
            } else if (prezo==0){
                prezo=artigo.getPrezo();
            }
        } catch (VerboseException ex) {
            error=ex;
            return false;
        }
        return erridx==0;
    }
    
    private boolean checkCliente() {
        int erridx=0;
        try {
            cliente=totalClientes.find(dniCliente);
            if (cliente==null) {
                erridx=error("Cliente err�neo","Non se atopa o Cliente na base de datos");
            }
        } catch (VerboseException ex) {
            error=ex;
            return false;
        }
        return erridx==0;
    }
    
    private boolean checkUnidades() {
        int erridx=0;
        stock=stocks.getStock(codigoArtigo);
        if (unidades<=0){
            erridx=error("N�mero de unidades err�neo","El n�mero de unidades debe ser mayor que 0");
        }
        else if (unidades>=stock){
            erridx=error("N�mero de unidades err�neo","EL stock es insuficiente");
        }
        return erridx==0;
    }
}
