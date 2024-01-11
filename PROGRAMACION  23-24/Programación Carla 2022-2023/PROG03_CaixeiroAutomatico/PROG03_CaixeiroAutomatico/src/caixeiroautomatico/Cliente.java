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
class Cliente {
    private final int uid;
    private final int pin;
    private String nome;
    private String apelidos;

    public Cliente(String nome,String apelidos,int uid, int pin) {
        this.nome=nome;
        this.apelidos=apelidos;
        this.uid = uid;
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public int getUid() {
        return uid;
    }
    
    @Override
    public String toString() {
        return nome+" "+apelidos;
    }
}
