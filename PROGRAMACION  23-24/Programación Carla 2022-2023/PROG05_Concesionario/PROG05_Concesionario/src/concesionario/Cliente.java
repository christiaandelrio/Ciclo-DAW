/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario;

/**
 *
 * @author xavi
 */
class Cliente {
    private String dni;
    private String nome;

    public Cliente(String dni, String nome) {
        this.dni = dni;
        this.nome = nome;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    static boolean dniValido(String dni) {
        return dni.matches("[0-9]{8}[A-Z]");
    }

    static boolean nomeValido(String nome) {
        String[] partes=nome.split("\\s+");
        return partes.length>=3;
    }
        
}
