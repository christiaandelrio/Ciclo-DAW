/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import funciones.Cliente;
import funciones.ClienteBuilder;
import static funciones.DataBase.*;
import static funciones.Level.Verbosity.*;
import funciones.VerboseException;
import java.time.LocalDate;
import java.util.Arrays;


/**
 *
 * @author yo
 */
class Clientes {

    static void start() throws Exception {
        ClientePrinter cprinter=new ClientePrinter();
        int op=0;
        do {
            System.out.println("Menú de Clientes\n--------------");
            System.out.println("1.- Listar Clientes");
            System.out.println("2.- Añadir Cliente");
            System.out.println("3.- Mantenimiento de Clientes");
            System.out.println("4.- Regresar");
            try {
                System.out.print("Elixe opcion: ");
                op=Integer.parseInt(Input.getLine());
                switch(op) {
                    case 1: 
                        Cliente[] list=totalClientes.all();
                        if (list.length==0) System.out.println("Non existen clientes");
                        else {
                            System.out.println(String.format("%24s\n%24s","Listado de Clientes","------------------"));
                            Arrays.sort(list);
                            for(Cliente c:list) System.out.println(c);
                        }
                        Input.waitEnter();
                        break;
                    case 2: 
                        Cliente c=formCliente(null);
                        Cliente exist=totalClientes.find(c.getDni());
                        if (exist!=null) {
                            System.out.println(cprinter.print(exist));
                            System.out.println("\n O cliente xa existe na base de datos");
                        } else {
                            System.out.println(cprinter.print(c));
                            if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                                totalClientes.store(c);
                                System.out.println("\nCliente Engadido");
                            }
                        }
                        break;
                    case 3: 
                        System.out.print("Buscar por (A)pelidos ou por (D)ni? ");
                        String info=Input.getLine().toUpperCase();
                        if (!"".equals(info)) {
                            if (info.charAt(0)=='A') {
                                final String apelidos=Input.getString("Apelidos: ", '*');
                                Cliente[] lista=totalClientes.search((cliente)->{
                                    return cliente.getApelidos().contains(apelidos);
                                });
                                if (lista.length==0) {
                                    System.out.println("Non existen clientes con apelidos similares a ese");
                                    break;
                                }
                                for(Cliente cl:lista) System.out.println(cl);
                            }
                            String dni=Input.getString("DNI: ",'*');
                            Cliente cliente=totalClientes.find(dni);
                            if (cliente==null) {
                                System.out.println("O cliente "+dni+" non existe");
                                Input.waitEnter();
                            }
                            else {
                                System.out.println(cprinter.print(cliente));
                                System.out.print("(E)ditar, (B)orrar (C)ontinuar : ");
                                info=Input.getLine().toUpperCase();
                                if (!"".equals(info)) {
                                    switch(info.charAt(0)) {
                                        case 'E':
                                            cliente=formCliente(cliente);
                                            
                                            System.out.println(cprinter.print(cliente));
                                            if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                                               totalClientes.store(cliente);
                                                System.out.println("Cliente Modificado");
                                            }
                                            break;
                                        case 'B':
                                            if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                                                totalClientes.delete(cliente.getDni());
                                                System.out.println("Cliente eliminado");
                                            }
                                            break;
                                    } 
                                }
                            }
                        }
                        break;
                }
            } catch(NumberFormatException e) {
                System.out.println("Debes introducir un valor numérico");
            
            } catch(VerboseException e) {
                System.out.println(e.getMessage(ERR));
          
            }
        } while(op!=4);
    }
    
    static Cliente formCliente(Cliente c) throws Exception {
        String str;
        ClienteBuilder cbuilder=new ClienteBuilder(c);
        System.out.println(String.format("%24s\n%24s","Alta/Edición de Cliente","--------------------"));
        do {
            try {
                cbuilder.reset();
                if (c==null) {
                    str=Input.getString("DNI ["+cbuilder.dni+"]:",'*');
                    if (str!=null) cbuilder.dni=str;
                } else System.out.println("DNI: "+c.getDni());
                str=Input.getString("Nome ["+cbuilder.nome+"]: ", '*');
                if (str!=null) cbuilder.nome=str;
                str=Input.getString("Apelidos ["+cbuilder.apelidos+"]: ", '*');
                if (str!=null) cbuilder.apelidos=str;
                str=Input.getString("Teléfono ["+cbuilder.telefono+"]: ", '*');
                if (str!=null) cbuilder.telefono=str;
                LocalDate date=Input.getDate("Data de nacemento ["+cbuilder.datanacemento+"]: ", '*');
                if (date!=null) cbuilder.datanacemento=date;
                str=Input.getString("Dirección ["+cbuilder.direccion+"]: ", '*');
                if (str!=null) cbuilder.direccion=str;
                str=Input.getString("Localidade ["+cbuilder.localidade+"]: ", '*');
                if (str!=null) cbuilder.localidade=str;
                str=Input.getString("Email ["+cbuilder.email+"]: ", '*');
                if (str!=null) cbuilder.email=str;
                Integer cp=Input.getInt("Codigo postal ["+cbuilder.codigopostal+"]: ",1000,52999,'*');
                if (cp!=null) cbuilder.codigopostal=cp;
                return cbuilder.build();
            } catch(VerboseException e) {
                System.out.println(e.getMessage(ERR));
            }
        } while(true);
    }
}
