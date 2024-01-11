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
public class CaixeiroAutomaticoApplication {
    private static int NUMBANCOS=2;
    private static int NUMTARXETAS=2;
    public static Banco[] bancos;
    public static Tarxeta[] tarxetas;


    public static void creaTarxetas() {
        tarxetas=new Tarxeta[NUMTARXETAS];
        
        tarxetas[0]=new Tarxeta(Tarxeta.Tipo.MOEDEIRO,"100","ES12018212345678910111");
    }

    public static void creaEntidades() {
        bancos=new Banco[NUMBANCOS];
        bancos[0]=new Banco("0182","Banco de Santander");
        bancos[1]=new Banco("0048","Banco de Bilbao Vizcaya");
    }
    
    public static Banco getBanco(String num) {
        int idx;
        for(idx=0;idx<NUMBANCOS && !bancos[idx].getCode().equals(num);idx++);
        if (idx<NUMBANCOS) return bancos[idx];
        return null;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] autorizados={ "0182","0049" };
        
        creaEntidades();
        Caixeiro caixeiro=new Caixeiro(autorizados);
        caixeiro.start();
    }
    
}
