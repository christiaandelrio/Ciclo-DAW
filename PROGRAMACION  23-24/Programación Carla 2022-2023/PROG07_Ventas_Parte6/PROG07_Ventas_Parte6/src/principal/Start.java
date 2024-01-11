package principal;

import static funcións.Level.Verbosity.DEBUG;
import funcións.VerboseException;


public class Start {
    static void start() {
        int op=0;
        do {
            System.out.println(String.format("%24s\n%24s","Menú Principal","--------------"));
            System.out.println("1.- Xestión de Clientes");
            System.out.println("2.- Xestión de Artigos");
            System.out.println("3.- Xestión de Ventas");
            System.out.println("4.- Facturación ");
            System.out.println("5.- Sair");
            try {
                System.out.print("Elixe opcion: ");
                op=Integer.parseInt(Input.getLine());
                switch(op) {
                    case 1: Clientes.start(); break;
                    case 2: Artigos.start(); break;
                    case 3: Ventas.start(); break;
                    case 4: Facturacion.start(); break;
                }
            } catch(NumberFormatException e) {}
        } while(op!=5);
    }
    

    public static void main(String[] args) throws VerboseException, Exception {
        VerboseException.setLevel(DEBUG);
        Start.start();
    }
    
}
