/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import static funciones.DataBase.*;
import funciones.Factura;
import funciones.FacturacionBuilder;
import static funciones.Level.Verbosity.*;
import funciones.Venta;
import funciones.VerboseException;
import java.time.LocalDate;

/**
 *
 * @author yo
 */
class Facturacion {

    static void start() {

        LocalDate date;
        Factura[] lista;
        FacturaPrinter fprinter=new FacturaPrinter();
        int op=0;
        
        do {
            System.out.println("Menú de Facturación\n--------------");
            System.out.println("1.- Emisión de Facturas");
            System.out.println("2.- Listado de Facturas");
            System.out.println("3.- Enseñar Factura");
            System.out.println("4.- Regresar");
            try {
                System.out.print("Elige una opción: ");
                op=Integer.parseInt(Input.getLine());
                switch(op) {
                    case 1: 
                        date=Input.getDate("Fecha a Facturar [hoy]: ", '*');
                        if (date==null) date=LocalDate.now();
                        final LocalDate sdata=date;
                        Venta[] vlist=totalVentas.search((venta)->venta.getData().equals(sdata));
                        if (vlist.length==0) System.out.println("No existen ventas a facturar el día "+sdata);
                        else {
                            FacturacionBuilder fbuild=new FacturacionBuilder();
                            for(Venta v:vlist) fbuild.addVenta(v);
                            Factura[] flist=fbuild.build();
                            for(Factura f:flist) totalFacturas.store(f);
                            System.out.printf("Facturadas %d ventas. Total %d facturas\n",vlist.length,flist.length);
                        }
                        Input.waitEnter();
                        break;
                    case 2: 
                        date=Input.getDate("Fecha de la Factura [Todas]: ", '*');
                        if (date==null){
                            lista=totalFacturas.all();
                        } else {
                            final LocalDate sdate=date;
                            lista=totalFacturas.search((f)->f.getDataFactura().equals(sdate));
                        }
                        if (lista.length == 0) System.out.println("No se encontraron facturas");
                        else {
                            for(Factura f:lista) System.out.println(f);
                        }
                        Input.waitEnter();
                        break;
                    case 3:
                        String code=Input.getString("Codigo da Factura: ", '*');
                        if (code!="") {
                            Factura f=totalFacturas.find(code);
                            if (f==null) System.out.println("No existe la factura "+code);
                            else         System.out.println(fprinter.print(f));
                        }
                        Input.waitEnter();
                }
            } catch(NumberFormatException e) {
                System.out.println("Debes introducir un valor numérico");
            
            } catch(VerboseException e) {
                System.out.println(e.getMessage(ERR));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while(op!=4);
    }
}
