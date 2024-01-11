/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroautomatico;

/**
 *
 * @author xavi
 */
class Tarxeta {
    enum Tipo { DEBITO, CREDITO, MOEDEIRO };
    final Tipo tipo;
    private String uid;  // UID cifrado co pin
    private String ccc;   // Numero de conta corrente asociada
    
    
    private CCC contacorrente=null;
    
    private int pin;
    private int autorizacion;
    
    public Tarxeta(Tipo t,String uid,String ccc) {
        tipo=t;
        this.uid=uid;
        this.ccc=ccc;
    }
    
    public void setPin(int pin) {
        this.pin=pin;
    }

    /**
     * Averiguar o banco a partir do número da tarxeta
     * Pedirlle a CCC ao banco
     * @return 
     */
    public CCC getCcc() {
        if (contacorrente == null) {
            String code=ccc.substring(4,8);
            Banco banco=CaixeiroAutomaticoApplication.getBanco(code);
            contacorrente=banco.getConta(code);
        }
        return contacorrente;
    }
    
    public double getSaldo() {
        return getCcc().getSaldo();
    }
    
    public String getCliente() {
        return getCcc().getCliente().toString();
    }
     
    /**
     * Descifra o uid co pin e o retorna
     * @param pin 
     * @return UID do usuario ou null si erro
     */
    String getUid(int pin) {
        this.pin=pin;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    void ingreso(float importe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    int solicitaRetirada(float importe) {
        Banco banco=getCcc().getBanco();
        int code=banco.autorizaRetirada(getCcc().getNumero(),getUid(pin),importe);
        this.autorizacion=code;
        return this.autorizacion;
    }
    
    
    void cancelaRetirada(int numautorizacion) {
        Banco banco=getCcc().getBanco();
        banco.cancelaRetirada(numautorizacion);
    }

    void confirmaRetirada(int numautorizacion) {
        Banco banco=getCcc().getBanco();
        banco.confirmaRetirada(numautorizacion);
    }
}
