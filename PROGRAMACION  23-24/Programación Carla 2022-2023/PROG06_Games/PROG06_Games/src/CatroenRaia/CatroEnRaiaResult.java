package CatroenRaia;

import game.GameAction;
import game.GameException;
import game.Piece;
import game.Result;
import game.State;
import game.Table;
import game.TableGame;
import game.TableGameAction;

public class CatroEnRaiaResult implements Result {
    private final TableGame game;
    private final TableGameAction move;
    private State state;
    private int rh;
    private int rv;
    private int rde;
    private int rdd;

    public CatroEnRaiaResult(TableGame game,GameAction action) {
        this.game=game;
        this.move=(TableGameAction)action;
        this.state=State.RUNNING;
        count();
        if ((state==State.RUNNING) && isEnd()) state=State.TIE;
    }
    
    @Override
    public State getState() {
        return state;
    }
    
    public int getCount() {
        int max=0;
        if (rh>max) max=rh;
        if (rv>max) max=rv;
        if (rdd>max) max=rdd;
        if (rde>max) max=rde;
        return max;
    }
    
        // O xogo remata si a primeira fila está chea
    private boolean isEnd() {
        Table table=game.getTable();
        int width=table.getWidth();
        for(int idx=0;idx<width;idx++) 
            try {
                if (table.get(0, idx)==null) return false;
            } catch (GameException ex) {
            }
        return true;
    }
    
    private void count() {
        rh=0; rv=0; rdd=0; rde=0;
        rh=countDir(0,1)+countDir(0,-1)+1;
        if (rh>=4) state=State.WIN;
        else {
            rv=countDir(1,0)+1;
            if (rv>=4) state=State.WIN;
            else {
                rdd=countDir(1,1)+countDir(-1,-1)+1;
                if (rdd>=4) state=State.WIN;
                else {
                    rde=countDir(1,-1)+countDir(-1,1)+1;
                    if (rde>=4) state=State.WIN;
                }
            }
        }
    }
    
    private int countDir(int diry,int dirx) {
        int count=0;
        try {
            Table table=game.getTable();
            Piece piece=move.getPlayer().getPiece();
            int f=move.getFila()+diry;
            int c=move.getColumna()+dirx;
            while(table.isIn(f,c) && table.get(f,c)==piece) {
                f+=diry; c+=dirx;
                count++;
            }
        } catch (GameException ex) {
        }
        return count;
    }
    
}
