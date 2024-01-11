/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import funciones.Artigo;
import static funciones.DataBase.*;
import static funciones.Level.Verbosity.*;
import funciones.VerboseException;

/**
 *
 * @author yo
 */
class Artigos {

    static void start() {
        ArtigoPrinter aprinter=new ArtigoPrinter();
        int op=0;
        do {
            System.out.println("Menú de Artigos\n--------------");
            System.out.println("1.- Listar Artículos");
            System.out.println("2.- Añadir Artículo");
            System.out.println("3.- Mantenimiento de Artículos");
            System.out.println("4.- Modificar Stock");
            System.out.println("5.- Regresar");
            try {
                System.out.print("Elige una opcion: ");
                op=Integer.parseInt(Input.getLine());
                switch(op) {
                    case 1: 
                        Artigo[] list=totalArtigos.all();
                        if (list.length==0) System.out.println("No existen artículos");
                        else {
                            System.out.println("Listado de Artigos\n------------------");
                            for(Artigo a:list) System.out.println(a+" "+stocks.getStock(a.getCodigo())+" unidades");
                        }
                        Input.waitEnter();
                        break;
                    case 2: 
                        Artigo a=formArtigo(null);
                        Artigo exist=totalArtigos.find(a.getCodigo());
                        if (exist!=null) {
                            System.out.println(aprinter.print(exist));
                            System.out.println("\nEL artículo ya existe en la base de datos");
                        } else {
                            System.out.println(aprinter.print(a));
                            if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                                totalArtigos.store(a);
                                System.out.println("\nArtículo añadido");
                            }
                        }
                        break;
                    case 3: 
                        System.out.print("Buscar por (C)ódigo ou por (D)escripción? ");
                        String info=Input.getLine().toUpperCase();
                        if (!"".equals(info)) {
                            if (info.charAt(0)=='D') {
                                final String desc=Input.getString("Descripción: ", '*');
                                Artigo[] lista=totalArtigos.search((artigo)->{
                                    return artigo.getDescripcion().contains(desc);
                                });
                                if (lista.length==0) {
                                    System.out.println("No existen artículos con descrición similar a '"+desc+"'");
                                    break;
                                }
                                for(Artigo art:lista) System.out.println(art);
                            }
                            String codigo=Input.getString("Código: ",'*');
                            Artigo art=totalArtigos.find(codigo);
                            if (art==null) {
                                System.out.println("El artículo "+codigo+" no existe");
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
                                                totalArtigos.store(art);
                                                System.out.println("Artículo modificado");
                                            }
                                            break;
                                        case 'B':
                                            if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                                                totalArtigos.delete(art.getCodigo());
                                                System.out.println("Artículo eliminado");
                                            }
                                            break;
                                    } 
                                }
                            }
                        }
                        break;
                    case 4:
                        String codigo=Input.getString("Código: ",'*');
                        Artigo art=totalArtigos.find(codigo);
                        if (art==null) {
                            System.out.println("El artículo "+codigo+" no se encuentra en la base de datos");
                            Input.waitEnter();
                        }
                        else {
                            System.out.println(aprinter.print(art));
                            Integer stock=Input.getInt("Stock: ", 1, 99999999, '*');
                            if (stock!=null) {
                                if (Input.confirm("Estás seguro? (S/N): ",'s')) {
                                    stocks.setStock(art.getCodigo(),stock);
                                    System.out.println("Stock establecido a "+stock+" unidades");
                                }
                            }
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
        } while(op!=5);
    }
    
    static Artigo formArtigo(Artigo a) throws Exception {
        String str;
        String codigo="";
        String denominacion="";
        String descripcion="";
        double prezo=0;
        
        if (a!=null) {
            codigo=a.getCodigo();
            denominacion=a.getDenominacion();
            descripcion=a.getDescripcion();
            prezo=a.getPrezo();
        }
        
        System.out.println("Alta/Edición de Artículo\n--------------------");
        if (a==null) { // Alta
            str=Input.getString("Codigo ["+codigo+"]:",'*');
            if (str!=null) codigo=str;
        } else System.out.println("Codigo: "+codigo);
        str=Input.getString("Denominación ["+denominacion+"]: ", '*');
        if (str!=null) denominacion=str;
        str=Input.getString("Descripción ["+descripcion+"]: ", '*');
        if (str!=null) descripcion=str;
        Double cant=Input.getDouble("Precio ["+String.format("%.4f",prezo)+"]: ",0.01,999999,'*');
        if (cant!=null) prezo=cant;
        Artigo artigo=new Artigo(codigo,denominacion,descripcion);
        artigo.setPrezo(prezo);
        return artigo;
    }
}
