package Buscaminas;

import game.ConsoleTablePlayer;
import game.ConsoleTableUI;
import game.GameException;
import game.Player;
import game.TableGame;
import game.Ui;


public class BuscaminasGame extends TableGame {
    public static int ROWS=20;
    public static int COLS=15;
    public static int NBOMBS=42;
    
    public BuscaminasGame(String nick,Ui ui,int nfilas, int ncols,int nbombs) {
        super(nfilas, ncols);
        this.gamers=new Player[]{ new ConsoleTablePlayer(nick,null) };
        this.engine=new BuscaminasEngine(nbombs);
        this.ui=ui;
    }

    public static void main(String[] args) {
        try {
            BuscaminasGame game=new BuscaminasGame("Xogador",new ConsoleTableUI(),ROWS,COLS,NBOMBS);
            game.start();
        } catch (GameException ex) {
           System.out.println("ERROR: "+ex.getMessage());
        }
    }
}


   /* public BuscaminasConsole(String nick) throws GameException {
        this.players=new Player[1];
        
        this.players[0]=new PlayerConsole(nick,null);

        this.engine=new GameBuscaminasEngine(ROWS,COLS,NBOMBS);
        this.userinterface=new UiTableConsole();
    }
    
    @Override
    public void result() throws GameException {
        switch(engine.getState()) {
            case WIN:
                userinterface.showMessage("Noraboa!!!","descubriches todas as minas");
                break;
            case LOST:
                userinterface.showMessage("BOOOOM!!!","Estas Morto!");
                break;
        }
    }   
    
    public static void main(String[] args) throws GameException {
        Game game=new BuscaminasConsole("Xogador");
        game.start();
    }  */  
