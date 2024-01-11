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
public class CCC {
    private Cliente cliente;
    private String numero;
    private double saldo;

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }
    
    /**
     * 
     * @return o Banco ao que pertence a CCC (polo número de CCC)
     */
    public Banco getBanco() {
        String code=numero.substring(4,8);
        return CaixeiroAutomaticoApplication.getBanco(code);
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    void retira(double importe) {
        this.saldo = this.saldo - importe;
    }
}
