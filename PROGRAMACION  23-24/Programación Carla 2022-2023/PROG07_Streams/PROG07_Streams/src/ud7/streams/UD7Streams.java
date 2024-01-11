package ud7.streams;

import java.util.HashMap;
import java.util.Scanner;


public class UD7Streams {
    static final HashMap<String,Artigo> STORE=new HashMap<>();
    static final Scanner SCN=new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int op=0;
        do {
            System.out.println("1.- Engadir Artigo");
            System.out.println("2.- Listar Artigos por denominación");
            System.out.println("3.- Buscar Artigo por código");
            System.out.println("4.- Amosar o mais caro (Stream)");
            System.out.println("5.- Amosar o mais caro (Iterativo)");
            System.out.println("6.- Amosar o importe total (Stream)");
            System.out.println("7.- Amosar o importe total (Iterativo)");
            System.out.println("8.- Saír\n");
            try {
                System.out.print("Opcion: ");
                op=Integer.parseInt(SCN.nextLine());
                switch(op) {
                    case 1: addArtigo(); break;
                    case 2: listArtigos(); break;
                    case 3: findArtigo(); break;
                    case 4: showExpensiveST(); break;
                    case 5: showExpensiveIT(); break;
                    case 6: showTotalST(); break;
                    case 7: showTotalIT(); break;
                }
            } catch(Exception e) {
                System.out.println("ERROR: "+e.getMessage());
            }
        } while(op!=8);
    }

    private static void addArtigo() {
        System.out.print("Codigo: ");
        String codigo=SCN.nextLine();
        System.out.print("Denominacion: ");
        String denominacion=SCN.nextLine();
        System.out.print("Prezo: ");
        double prezo=Double.parseDouble(SCN.nextLine());
        STORE.put(codigo,new Artigo(codigo,denominacion,prezo));
    }
    
    private static void listArtigos() {
        STORE.values().stream().sorted().forEach((a)->System.out.println(a));
        /*
        ArrayList<Artigo> sorted=new ArrayList<Artigo>();
        sorted.addAll(STORE.values());
        Collections.sort(sorted);
        sorted.forEach((a)->System.out.println(a));
        */
    }
    
    private static void findArtigo() {
        System.out.print("Codigo: ");
        String codigo=SCN.nextLine();
        Artigo art=STORE.get(codigo);
        if (art==null) System.out.println("O artigo de código "+codigo+" non existe");
        else {
            System.out.println(new ArtigoBridge(art).format());
        }
    }
    
    private static void showExpensiveST() {
        /*
        STORE.values().stream().reduce(new BinaryOperator<>() {
            @Override
            public Artigo apply(Artigo t, Artigo u) {
                if (t.getPrezo()>=u.getPrezo()) return t;
                return u;
            }
        });
        */
        
        /*double max=STORE.values().stream().mapToDouble(a->a.getPrezo()).max().getAsDouble();
        System.out.println("Valor máximo: "+max);
        */
        
        /*Artigo art=STORE.values().stream().reduce((Artigo t, Artigo u) -> {
            if (t.getPrezo()>=u.getPrezo()) return t;
            return u;
        }).get(); */
        
        Artigo art=STORE.values().stream().max((a,b)->a.getPrezo()>=b.getPrezo()?1:-1).get();
        System.out.println("O artigo máis caro é: ");
        System.out.println(new ArtigoBridge(art).format());
    }
    
    private static void showExpensiveIT() {
        Artigo art=null;
        for(Artigo a:STORE.values()) {
            if (art==null) art=a;
            else {
                if (a.getPrezo()>art.getPrezo()) art=a;
            }
        }
        System.out.println("O artigo máis caro é: ");
        System.out.println(new ArtigoBridge(art).format());
    }
    
    private static void showTotalST() {
        Double total=STORE.values().stream().mapToDouble(a->a.getPrezo())
                    .reduce((p1,p2) -> p1+p2).getAsDouble();   
        System.out.println("Importe total: "+total);
    }
    
    private static void showTotalIT() {
        double total=0;
        // Xerado por Netbeans
        // total = STORE.values().stream().map((a) -> a.getPrezo()).reduce(total, (accumulator, _item) -> accumulator + _item);
        for(Artigo a:STORE.values()) total+=a.getPrezo();
        System.out.println("Importe total: "+total);
    }
}
