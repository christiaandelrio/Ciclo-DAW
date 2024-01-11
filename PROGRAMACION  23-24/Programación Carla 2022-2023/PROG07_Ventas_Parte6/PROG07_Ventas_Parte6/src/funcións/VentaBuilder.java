package funcións;

import java.time.LocalDate;

public class VentaBuilder extends BuilderAdapter<Venta> {//implements Builder<Venta>{
    public LocalDate data;
    public String codigoArtigo;
    public String dniCliente;
    public int unidades;
    public double prezo;
    
    public Artigo artigo;
    public Cliente cliente;
    public int stock;
    
    //private VerboseException error;

    public VentaBuilder() {
        super("Erro creando Venta");
        this.data=LocalDate.now();
        this.codigoArtigo = "";
        this.dniCliente = "";
        this.unidades = 1;
        this.prezo = 0;
    }
   
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
            erridx=error("Data errónea","Non se poden facer ventas con data futura");
        }
        return erridx==0;
    }
    
    private boolean checkPrezo() {
        int erridx=0;
        if (prezo<=0) erridx=error("Prezo erróneo","O prezo debe ser maior ou igual a 0");
        return erridx==0;
    }
    
    private boolean checkArtigo() {
        int erridx=0;
        try {
            artigo=Database.tartigos.find(codigoArtigo);
            if (artigo==null) {
                erridx=error("Artigo erróneo","Non se atopa o Artigo na base de datos");
            } else if (prezo==0) prezo=artigo.getPrezo();
        } catch (VerboseException ex) {
            error=ex;
            return false;
        }
        return erridx==0;
    }
    
    private boolean checkCliente() {
        int erridx=0;
        try {
            cliente=Database.tclientes.find(dniCliente);
            if (cliente==null) {
                erridx=error("Cliente erróneo","Non se atopa o Cliente na base de datos");
            };
        } catch (VerboseException ex) {
            error=ex;
            return false;
        }
        return erridx==0;
    }
    
    private boolean checkUnidades() {
        int erridx=0;
        stock=Database.stock.getStock(codigoArtigo);
        if (unidades<=0) erridx=error("Número de unidades erróneo","O número de unidades debe ser maior a 0");
        else if (unidades>=stock) erridx=error("Número de unidades erróneo","O stock é insuficiente");
        return erridx==0;
    }
    
    /*
    
    private void notifyErrors() throws VerboseException {
        if (error!=null) throw error;
    } 
    
    public void reset() {
        error=null;
    }
    
    private int error(String msg,String description) {
        if (error==null) error=new VerboseException("Erro creando Venta");
        int erridx=error.addMensaxe(msg);
        error.addMensaxe(ERR,erridx,description);    
        return erridx;
    } 
    
    private int error(String msg) {
        if (error==null) error=new VerboseException("Erro creando Venta");
        int erridx=error.addMensaxe(msg);
        return erridx;
    }*/
}
