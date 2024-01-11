package principal;

import funcións.Cliente;
import funcións.Database;
import funcións.Printer;
import funcións.VerboseException;
import java.util.ArrayList;
import java.util.Collection;



/**
    private final String dni;
    private final String nome;
    private final String apelidos;
    private String direccion;
    private String telefono;
    private LocalDate datanacemento;
    private String localidade;
    private String email;
    private int codigopostal;
 */
public class ClientePrinter implements Printer<Cliente> {

    @Override
    public String[] toCSV(Collection<Cliente> list) {
        ArrayList<String> csv=new ArrayList<>();
        csv.add("dni,nome,apelidos,data nacemento,dirección,teléfono,localidade,email,codigo postal");
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
                .append(c.getDatanacemento()).append(",")
                .append(clean((String)mapNULL(c.getDireccion()))).append(",")
                .append(c.getTelefono()).append(",")
                .append(clean((String)mapNULL(c.getLocalidade()))).append(",")
                .append(c.getEmail()).append(",")
                .append(c.getCodigopostal());
        return row.toString();
    }

    @Override
    public String print(Cliente c) {
        StringBuilder strcliente=new StringBuilder(String.format("\n%-35s %s, %s",c.getDni(),c.getApelidos(),c.getNome()))
                .append(String.format("\n%-35s Teléfono: %s",c.getEmail(),c.getTelefono()))
                .append(String.format("\n%s (%s) %s\n",mapNULL(c.getDireccion()),c.getCodigopostal(),mapNULL(c.getLocalidade())));
        return strcliente.toString();
    }
    
    public String print(String dni) throws VerboseException {
        Cliente cl=Database.tclientes.find(dni);
        if (cl==null) throw new VerboseException("O cliente con dni "+dni+" non existe");
        return print(cl);
    }
}
