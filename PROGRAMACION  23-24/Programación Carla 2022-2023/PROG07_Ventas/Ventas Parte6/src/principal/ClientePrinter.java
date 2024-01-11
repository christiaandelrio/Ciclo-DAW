/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import funciones.Cliente;
import static funciones.DataBase.*;
import funciones.Printer;
import funciones.VerboseException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author yo
 */
public class ClientePrinter implements Printer<Cliente>{

    @Override
    public String[] toCSV(Collection<Cliente> list) {
        ArrayList<String> csv=new ArrayList<>();
        csv.add("dni,nombre,apellidos,fecha de nacimiento,dirección,teléfono,localidad,e-mail,código postal");
        list.forEach((c) -> csv.add(toRow(c)));
        /* Alternativamente....
            for(Cliente c:list) csv.add(toRow(c));
        */
        return csv.toArray(new String[0]);
    }

    @Override
    public String toRow(Cliente c) {
        StringBuilder row=new StringBuilder(c.getDni()).append(",")
                .append(clean(c.getNome())).append(",")
                .append(clean(c.getApelidos())).append(",")
                .append(c.getNacimiento()).append(",")
                .append(clean((String)mapNULL(c.getDireccion()))).append(",")
                .append(c.getTelefono()).append(",")
                .append(clean((String)mapNULL(c.getLocalidade()))).append(",")
                .append(c.getEmail()).append(",")
                .append(c.getCodigoPostal());
        return row.toString();
    }

    @Override
    public String print(Cliente c) {
        StringBuilder strcliente=new StringBuilder(String.format("\n%-35s %s, %s",c.getDni(),c.getApelidos(),c.getNome()))
                .append(String.format("\n%-35s Teléfono: %s",c.getEmail(),c.getTelefono()))
                .append(String.format("\n%s (%s) %s\n",mapNULL(c.getDireccion()),c.getCodigoPostal(),mapNULL(c.getLocalidade())));
        return strcliente.toString();
    }
    
    public String print(String dni) throws VerboseException {
        Cliente cl=totalClientes.find(dni);
        if (cl==null) throw new VerboseException("O cliente con dni "+dni+" non existe");
        return print(cl);
    }
    
}
