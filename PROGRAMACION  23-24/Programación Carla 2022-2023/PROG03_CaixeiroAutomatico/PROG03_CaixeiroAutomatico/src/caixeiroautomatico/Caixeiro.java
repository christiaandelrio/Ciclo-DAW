/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroautomatico;

import java.util.Scanner;

/**
 *
 * @author xavi
 */
class Caixeiro {
    private final int MAX_AUTORIZADOS=6;
    
    private String[] autorizados=new String[MAX_AUTORIZADOS];
    
    public Caixeiro(String[] autorizados) {
        this.autorizados=autorizados;
    }
    
    public boolean detecta_diñeiro() {
        boolean response;
        // espera(10);
        response=Inputs.pregunta("Puxeches diñeiro: ").equals("si");
        if (response) espera(2);
        return response;
    }
    
    public void expulsa_diñeiro(float importe) {
        abre_comporta();
        System.out.println("Recolle os teus "+importe+"€");
    }
    
    public void abre_comporta() {
        System.out.println("Comporta de cartos aberta");
    }
    
    public float pecha_comporta() {
        return Inputs.leefloat("Diñeiro introducido: ");
    }
    
      
    public void expulsa_tarxeta() {
        System.out.println("Non olvides recoller a tarxeta.");
        System.out.println("Gracias por su atención.");
    }
    
    /**
     * Lee a inforamación da tarxeta introducida na ranura
     * @return obexecto Tarxeta coa información da tarxeta ou null si a tarxeta é errónea
     */
    public Tarxeta leer_informacion_tarxeta() {
        return null;
    }

    void start() {
        while(true) {
            Tarxeta t=leer_informacion_tarxeta();
            if (t == null) {
                System.out.println("Tarxeta non válida");
                expulsa_tarxeta();
            } else {
                String uid=identifica(t);
                if (uid==null) {
                    System.out.println("Datos incorrectos");
                    expulsa_tarxeta();
                } else {
                    System.out.println("Hola "+t.getCliente());
                    System.out.println("O saldo actual é de "+t.getSaldo()+"€");
                    switch(t.tipo) {
                        case MOEDEIRO:
                            procesa_tarxeta_moedeiro(t);
                            break;
                        default:
                            procesa_tarxeta_bancaria(t);
                            break;
                    }
                }
            }
        }
    }

    private void procesa_tarxeta_moedeiro(Tarxeta t) {
        float importe;
        
        if (Inputs.pregunta("Desexas recargar ? ").equals("si")) {
            abre_comporta();
            detecta_diñeiro();
            importe=pecha_comporta();
            if (importe > 0) {
                if (Inputs.pregunta("Se van a ingresar "+importe+"€. Está vostede seguro?").equals("si")) {
                    t.ingreso(importe);
                } else {
                    expulsa_diñeiro(importe);
                }
            }
        }
        expulsa_tarxeta();
    }

    private void procesa_tarxeta_bancaria(Tarxeta t) {
        
        if (Inputs.pregunta("Desexas retirar cartos ? ").equals("si")) {
            int importe=Inputs.leeIntMaxMin("Importe a Retirar: ",5,(int)t.getSaldo());
            int numautorizacion=t.solicitaRetirada(importe);
            if (numautorizacion > 0) {
                expulsa_diñeiro(importe);
                espera(15);
                if (detecta_diñeiro()) {
                    pecha_comporta();
                    t.cancelaRetirada(numautorizacion);
                    System.out.println("Novo saldo "+t.getSaldo()+"€");
                } else {
                    t.confirmaRetirada(numautorizacion);
                }
                        
            } else System.out.println("Imposible realizar a operación (código "+numautorizacion+")");
        }    
        expulsa_tarxeta();
    }
    
        
    /**
     * Identifia o usuario da tarxeta pedindo o PIN
     * @param t - Tarxeta
     * @return 
     */
    private String identifica(Tarxeta t) {
        Scanner scn=new Scanner(System.in);
        String uid;
        int intentos=0;
        
        do {
            int pin=Inputs.leeIntMaxMin("PIN: ", 0, 9999);
            uid=t.getUid(pin);
            if (uid == null) {
                System.out.println("Pin erróneo");
                intentos=intentos+1;
            } 
        } while((uid==null) && (intentos<3));
        return uid;
    }
   
    
    private void espera(int sgs) {
        
    }
}
 