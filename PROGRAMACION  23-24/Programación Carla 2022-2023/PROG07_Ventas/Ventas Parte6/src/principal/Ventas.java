/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import static funciones.DataBase.*;
import static funciones.Level.Verbosity.*;
import funciones.Venta;
import funciones.VentaBuilder;
import funciones.VerboseException;
import java.time.LocalDate;
import java.util.Arrays;

/**
 *
 * @author yo
 */
class Ventas {
    static void start() {
        VentaPrinter vprinter=new VentaPrinter();
        int op=0;
        do {
            System.out.println("Menú de Ventas\n--------------");
            System.out.println("1.- Listado de Ventas");
            System.out.println("2.- Realizar Venta");
            System.out.println("3.- Regresar");
            try {
                System.out.print("Elige una opción: ");
                op=Integer.parseInt(Input.getLine());
                switch(op) {
                    case 1: 
                        Venta[] list=totalVentas.all();
                        if (list.length==0) System.out.println("No existen ventas");
                        else {
                            Arrays.sort(list);
                            System.out.println("Listado de Ventas\n------------------");
                            for(Venta v:list) System.out.println(v);
                        }
                        Input.waitEnter();
                        break;
                    case 2: 
                        Venta v=formVenta();
                        System.out.println(vprinter.print(v));
                        if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                           totalVentas.store(v);
                            System.out.println("\nVenta añadida");
                        }
                        break;
                }
            } catch(NumberFormatException e) {
                System.out.println("Debes introducir un valor numérico");
            
            } catch(VerboseException e) {
                System.out.println(e.getMessage(ERR));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while(op!=3);
    }
    static Venta formVenta() throws Exception {
        String str;
        ArtigoPrinter aprinter=new ArtigoPrinter();
        ClientePrinter cprinter=new ClientePrinter();
        VentaBuilder vbuilder=new VentaBuilder();
        vbuilder.setData(LocalDate.now());
        System.out.println("Nova Venta a " + vbuilder.data +"--------------------");
        do {
            try {
                vbuilder.reset();
                str=Input.getString("Código Artículo ["+vbuilder.codigoArtigo+"]:",'*');
                if (str!=null) vbuilder.setCodigoArtigo(str);
                vbuilder.notifyErrors();
                System.out.println(aprinter.print(vbuilder.artigo));
                
                str=Input.getString("DNI Cliente ["+vbuilder.dniCliente+"]: ", '*');
                if (str!=null) vbuilder.setDniCliente(str);
                vbuilder.notifyErrors();
                System.out.println(cprinter.print(vbuilder.cliente));
                
                Integer un=Input.getInt("Unidades ["+vbuilder.unidades+"]: ",1,999999,'*');
                if (un!=null) vbuilder.unidades=un;
                
                Double pr=Input.getDouble("Precio ["+vbuilder.prezo+"]: ",0.01,999999,'*');
                if (pr!=null) vbuilder.prezo=pr;
                
                return vbuilder.build();
            } catch(VerboseException e) {
                System.out.println(e.getMessage(ERR));
            }
        } while(true);
    }
}
