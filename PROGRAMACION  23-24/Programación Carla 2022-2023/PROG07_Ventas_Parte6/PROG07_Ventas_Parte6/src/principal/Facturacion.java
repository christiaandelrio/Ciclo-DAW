package principal;

import funcións.Database;
import funcións.Factura;
import funcións.FacturacionBuilder;
import static funcións.Level.Verbosity.ERR;
import funcións.Venta;
import funcións.VerboseException;
import java.time.LocalDate;


class Facturacion {
    static void start() {
        LocalDate date;
        Factura[] lista;
        FacturaPrinter fprinter=new FacturaPrinter();
        int op=0;
        
        do {
            System.out.println(String.format("%24s\n%24s","Menú de Facturación","--------------"));
            System.out.println("1.- Emisión de Facturas");
            System.out.println("2.- Listado de Facturas");
            System.out.println("3.- Amosar Factura");
            System.out.println("4.- Voltar");
            try {
                System.out.print("Elixe opcion: ");
                op=Integer.parseInt(Input.getLine());
                switch(op) {
                    case 1: 
                        date=Input.getDate("Data a Facturar [hoxe]: ", '*');
                        if (date==null) date=LocalDate.now();
                        final LocalDate sdata=date;
                        Venta[] vlist=Database.tventas.search((venta)->venta.getData().equals(sdata));
                        if (vlist.length==0) System.out.println("Non existen ventas a facturar o día "+sdata);
                        else {
                            FacturacionBuilder fbuild=new FacturacionBuilder();
                            for(Venta v:vlist) fbuild.addVenta(v);
                            Factura[] flist=fbuild.build();
                            for(Factura f:flist) Database.tfacturas.store(f);
                            System.out.printf("Facturadas %d ventas. Total %d facturas\n",vlist.length,flist.length);
                        }
                        Input.waitEnter();
                        break;
                    case 2: 
                        date=Input.getDate("Data da Factura [Todas]: ", '*');
                        if (date==null) lista=Database.tfacturas.all();
                        else {
                            final LocalDate sdate=date;
                            lista=Database.tfacturas.search((f)->f.getDataFactura().equals(sdate));
                        }
                        if (lista.length == 0) System.out.println("Non se atoparon facturas");
                        else {
                            for(Factura f:lista) System.out.println(f);
                        }
                        Input.waitEnter();
                        break;
                    case 3:
                        String code=Input.getString("Codigo da Factura: ", '*');
                        if (code!="") {
                            Factura f=Database.tfacturas.find(code);
                            if (f==null) System.out.println("Non existe a factura "+code);
                            else         System.out.println(fprinter.print(f));
                        }
                        Input.waitEnter();
                }
            } catch(NumberFormatException e) {
                System.out.println("Debes introducir un valor numérico");
            } catch(CancelException e) {
                System.out.println("\nOperación cancelada\n");
            } catch(VerboseException e) {
                System.out.println(e.getMessage(ERR));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while(op!=4);
    }
}
