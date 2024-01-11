package principal;

import funcións.Artigo;
import funcións.Database;
import static funcións.Level.Verbosity.ERR;
import funcións.VerboseException;


class Artigos {
    static void start() {
        ArtigoPrinter aprinter=new ArtigoPrinter();
        int op=0;
        do {
            System.out.println(String.format("%24s\n%24s","Menú de Artigos","--------------"));
            System.out.println("1.- Listar Artigos");
            System.out.println("2.- Engadir Artigo");
            System.out.println("3.- Mantemento de Artigos");
            System.out.println("4.- Modificar Stock");
            System.out.println("5.- Voltar");
            try {
                System.out.print("Elixe opcion: ");
                op=Integer.parseInt(Input.getLine());
                switch(op) {
                    case 1: 
                        Artigo[] list=Database.tartigos.all();
                        if (list.length==0) System.out.println("Non existen artigos");
                        else {
                            System.out.println(String.format("%24s\n%24s","Listado de Artigos","------------------"));
                            for(Artigo a:list) System.out.println(a+" "+Database.stock.getStock(a.getCodigo())+" unidades");
                        }
                        Input.waitEnter();
                        break;
                    case 2: 
                        Artigo a=formArtigo(null);
                        Artigo exist=Database.tartigos.find(a.getCodigo());
                        if (exist!=null) {
                            System.out.println(aprinter.print(exist));
                            System.out.println("\n O Artigo xa existe na base de datos");
                        } else {
                            System.out.println(aprinter.print(a));
                            if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                                Database.tartigos.store(a);
                                System.out.println("\nArtigo Engadido");
                            }
                        }
                        break;
                    case 3: 
                        System.out.print("Buscar por (C)ódigo ou por (D)escrición? ");
                        String info=Input.getLine().toUpperCase();
                        if (!"".equals(info)) {
                            if (info.charAt(0)=='D') {
                                final String desc=Input.getString("Descrición: ", '*');
                                Artigo[] lista=Database.tartigos.search((artigo)->{
                                    return artigo.getDescricion().contains(desc);
                                });
                                if (lista.length==0) {
                                    System.out.println("Non existen artigos con descrición similar a '"+desc+"'");
                                    break;
                                }
                                for(Artigo art:lista) System.out.println(art);
                            }
                            String codigo=Input.getString("Código: ",'*');
                            Artigo art=Database.tartigos.find(codigo);
                            if (art==null) {
                                System.out.println("O artigo "+codigo+" non existe");
                                Input.waitEnter();
                            }
                            else {
                                System.out.println(aprinter.print(art));
                                System.out.print("(E)ditar, (B)orrar (C)ontinuar : ");
                                info=Input.getLine().toUpperCase();
                                if (!"".equals(info)) {
                                    switch(info.charAt(0)) {
                                        case 'E':
                                            art=formArtigo(art);
                                            
                                            System.out.println(aprinter.print(art));
                                            if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                                                Database.tartigos.store(art);
                                                System.out.println("Artigo modificado");
                                            }
                                            break;
                                        case 'B':
                                            if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                                                Database.tartigos.delete(art.getCodigo());
                                                System.out.println("Artigo eliminado");
                                            }
                                            break;
                                    } 
                                }
                            }
                        }
                        break;
                    case 4:
                        String codigo=Input.getString("Código: ",'*');
                        Artigo art=Database.tartigos.find(codigo);
                        if (art==null) {
                            System.out.println("O Artigo "+codigo+" non se atopa na base de datos");
                            Input.waitEnter();
                        }
                        else {
                            System.out.println(aprinter.print(art));
                            Integer stock=Input.getInt("Stock: ", 1, 99999999, '*');
                            if (stock!=null) {
                                if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                                    Database.stock.setStock(art.getCodigo(),stock);
                                    System.out.println("Stock establecido a "+stock+" unidades");
                                }
                            }
                        }
                        break;
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
        } while(op!=5);
    }
    
    static Artigo formArtigo(Artigo a) throws Exception {
        String str;
        String codigo="";
        String denominacion="";
        String descricion="";
        double prezo=0;
        
        if (a!=null) {
            codigo=a.getCodigo();
            denominacion=a.getDenominacion();
            descricion=a.getDescricion();
            prezo=a.getPrezo();
        }
        
        System.out.printf("%24s\n%24s\n","Alta/Edición de Artigo","--------------------");
        if (a==null) { // Alta
            str=Input.getString("Codigo ["+codigo+"]:",'*');
            if (str!=null) codigo=str;
        } else System.out.println("Codigo: "+codigo);
        str=Input.getString("Denominación ["+denominacion+"]: ", '*');
        if (str!=null) denominacion=str;
        str=Input.getString("Descrición ["+descricion+"]: ", '*');
        if (str!=null) descricion=str;
        Double cant=Input.getDouble("Prezo ["+String.format("%.4f",prezo)+"]: ",0.01,999999,'*');
        if (cant!=null) prezo=cant;
        Artigo artigo=new Artigo(codigo,denominacion,prezo);
        artigo.setDescricion(descricion);
        return artigo;
    }
    
}
