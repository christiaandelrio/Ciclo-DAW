package funcións;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Cliente implements Comparable<Cliente>{
    private final String dni;
    private final String nome;
    private final String apelidos;
    private String direccion;
    private String telefono;
    private LocalDate datanacemento;
    private String localidade;
    private String email;
    private int codigopostal;
    
    Cliente(String dni,String nome,String apelidos) {
        this.dni=dni;
        this.nome=nome;
        this.apelidos=apelidos;
    }

    public static Cliente[] fromCSV(String[] csv) throws Exception {
        ArrayList<Cliente> list=new ArrayList<>();
        try {
            ClienteBuilder builder=new ClienteBuilder();
            HashMap<String,String> map=new HashMap<>();
            String[] header=csv[0].split(",");
            for(int idx=1;idx<csv.length;idx++) {
                String[] line=csv[idx].split(",");
                
                for(int f=0;f<header.length;f++) map.put(header[f],line[f]);
                builder.dni=map.get("dni");
                builder.nome=map.get("nome");
                builder.apelidos=map.get("apelidos");
                builder.direccion=map.get("dirección");
                builder.telefono=map.get("teléfono");
                builder.datanacemento=LocalDate.parse(map.get("data nacemento"));
                builder.localidade=map.get("localidade");
                builder.email=map.get("email");
                builder.codigopostal=Integer.parseInt(map.get("codigo postal"));
                list.add(builder.build());
            }
        } catch(NumberFormatException e) {
            throw new VerboseException(e);
        } 
        return list.toArray(new Cliente[0]);
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

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getDatanacemento() {
        return datanacemento;
    }

    public void setDatanacemento(LocalDate datanacemento) {
        this.datanacemento = datanacemento;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(int codigopostal) {
        this.codigopostal = codigopostal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.dni);
        return hash;
    }

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
        if (other==null) return 1;
        return (apelidos+", "+nome).compareTo(other.apelidos+", "+other.nome);
    }

    @Override
    public String toString() {
        return apelidos+", "+nome+" ("+dni+")";
    }
    
}
