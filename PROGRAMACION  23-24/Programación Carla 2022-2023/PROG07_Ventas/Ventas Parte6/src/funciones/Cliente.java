package funciones;


import java.time.LocalDate;
import java.util.Objects;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yo
 */
public class Cliente implements Comparable<Cliente>{
    private final String dni;
    private final String nome;
    private final String apelidos;
    private String direccion;
    private String telefono;
    private LocalDate nacimiento;
    private String localidade;
    private String email;
    private int codigoPostal;
    
    //Constructor(recibe como parámetros dni, nombre y apellidos)
    protected Cliente(String dni, String nome, String apelidos) {
        this.dni = dni;
        this.nome = nome;
        this.apelidos = apelidos;
        this.direccion = null;
        this.telefono = null;
        this.nacimiento = null;
        this.localidade = null;
        this.email = null;
        this.codigoPostal = 0;
    }

    public String getDni() {
        return dni;
    }

    public String getNome() {
        return nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getEmail() {
        return email;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    //Los clientes son considerados iguales si sus DNI coinciden
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Cliente other) {
       if(other==null){
           return 1;
       }
       //Se comparan los dos Strings
        return (apelidos+", "+nome).compareTo(other.apelidos+", "+other.nome);
    }
    
    @Override
    public String toString(){
       return apelidos + ", " + nome + " (" +dni + ")"; 
    }
}
