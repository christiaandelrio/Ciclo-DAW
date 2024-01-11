package CatroenRaia;

import game.GameAction;
import game.GameException;
import game.Piece;
import game.Player;
import game.State;
import game.Table;
import game.TableGame;
import game.TableGameAction;

/**
 *
 * @author xavi
 */
public class AutomaticCatroRaiaPlayer extends Player {

    public AutomaticCatroRaiaPlayer(String nick, Piece piece) {
        super(nick,piece);
        this.automatic=true;
    }
    
    @Override
    public GameAction getPlay() throws GameException {
        TableGame tgame=(TableGame) game;
        Table table=tgame.getTable();
        int turno=tgame.getTurn();
        TableGameAction myplay=new TableGameAction(this,0,0);
        TableGameAction otherplay=new TableGameAction(tgame.getPlayer(1-turno),0,0);
             
        int width=table.getWidth();
        int[] points=new int[width];
        for(int idx=0;idx<width;idx++) {
            if (table.get(0,idx)==null) {
                int f=0;
                while(f<table.getHeight() && table.get(f,idx)==null) f++;
                myplay.setFila(f-1);
                myplay.setColumna(idx);
                otherplay.setFila(f-1);
                otherplay.setColumna(idx);
                points[idx]=examine(table,myplay,otherplay);
            } else points[idx]=-100; // Non se pode xogar
        }
        // Xogaremos na casiña con máis puntos.
        int col=0;
        for(int idx=1;idx<width;idx++) {
            if (points[idx]>points[col]) col=idx;
        }
        TableGameAction move=new TableGameAction(this,0,col);
        return move;
    }
    
    private int examine(Table table,TableGameAction myplay,TableGameAction otherplay) {
        TableGame tgame=(TableGame)game;
        int points=1;
        // Gaño si xogo aqui
        CatroEnRaiaResult myresult=new CatroEnRaiaResult(tgame,myplay);
        if (myresult.getState()==State.WIN) return 10;
        // O contrario gaña si xogara aqui
        CatroEnRaiaResult oresult=new CatroEnRaiaResult(tgame,otherplay);
        if (oresult.getState()==State.WIN) return 10;
        // O Contrario poñería máis de 2 en liña si xoga aquí.
        if (oresult.getCount()>2) points+=5;
        // Miramos que ocurriría si xogara aqui
        int fila=otherplay.getFila()-1;
        if (fila>=0) {
            otherplay.setFila(fila);
            oresult=new CatroEnRaiaResult(tgame,otherplay);
            // Si o contrario fai varias en liña logo, mellor non xogar aquí. 
            // menos canto máis en liña faga, mellor en outro lado.
            points-=oresult.getCount();
        }
        return points;
    }
    
}
