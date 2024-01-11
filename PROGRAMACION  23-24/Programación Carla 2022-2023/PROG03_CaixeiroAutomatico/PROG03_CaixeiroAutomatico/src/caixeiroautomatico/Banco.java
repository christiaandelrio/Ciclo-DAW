package caixeiroautomatico;

/**
 *
 * @author xavi
 */
public class Banco {
    private final int MAX_CTAS=100;
    private final int MAX_AUTH=100;
    
    private final String code;
    private final String nome;
    private final CCC[] contas;
    private final Autorizacion[] autorizacions;
    
    private int num_contas;
   
    public Banco(String code,String nome) {
        this.num_contas=0;
        this.code=code;
        this.nome=nome;
        this.contas=new CCC[MAX_CTAS];
        this.autorizacions=new Autorizacion[MAX_AUTH];
    }
    
    public String getCode() {
        return code;
    }
    
    public String getNome() {
        return nome;
    }

    int buscaPosicionAutorizacion() {
        int idx;
        for(idx=0;idx<MAX_AUTH && autorizacions[idx]!=null && autorizacions[idx].isValid();idx++);
        return idx;
    }
    
    
    public int autoriza(String ccc,double importe) {
        int idx,id=0;
        
        int pos=buscaPosicionAutorizacion();
        if (pos==MAX_AUTH) return -10;
        CCC conta=getConta(ccc);
        if (conta!=null) {
            if (conta.getSaldo()>=importe) {
                Autorizacion auth=new Autorizacion(ccc,importe);
                autorizacions[pos]=auth;
                id=auth.getId();
            }
        }
        return id;
    }
    
    public boolean addCCC(CCC ccc) {
        if (num_contas==MAX_CTAS) return false;
        contas[num_contas]=ccc;
        num_contas=num_contas+1;
        return true;
    }
    
    
    int autorizaRetirada(String numero, String uid, float importe) {
        return autoriza(numero,importe);
    }

    void cancelaRetirada(int numautorizacion) {
        int pos;
        for(pos=0;pos<MAX_AUTH && (autorizacions[pos]==null || autorizacions[pos].getId()!=numautorizacion);pos++);
        if (pos<MAX_AUTH) autorizacions[pos].anula();
    }

    void confirmaRetirada(int numautorizacion) {
        int pos;
        String ccc;
        double importe;
        
        for(pos=0;pos<MAX_AUTH && (autorizacions[pos]==null || autorizacions[pos].getId()!=numautorizacion);pos++);
        ccc=autorizacions[pos].getCcc();
        importe=autorizacions[pos].getImporte();
        retirada(ccc,importe);
        if (pos<MAX_AUTH) autorizacions[pos].anula();
    }
    
    
    private void retirada(String ccc,double importe) {
        CCC conta=getConta(ccc);
        if (conta!=null) conta.retira(importe);
    }
    
    
    public CCC getConta(String ccc) {
        CCC conta=null;
        int idx;
        
        for(idx=0;(idx<this.num_contas || contas[idx].getNumero().equals(ccc));idx++);
        if (idx<this.num_contas) conta=contas[idx];
        return conta;
    }
    
    public static String bankNames(String code) {
        if (code.equals("0182")) return "Banco de Santander";
        else if (code.equals("0049")) return "Banco de Bilbao-Vizcaya";
        return null;
    }
}
