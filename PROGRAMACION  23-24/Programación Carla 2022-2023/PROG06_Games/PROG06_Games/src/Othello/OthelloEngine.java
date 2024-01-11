package Othello;

import static Othello.OthelloResult.*;
import game.ClickAction;
import game.GameAction;
import game.GameEngineAdapter;
import game.GameException;
import game.Piece;
import game.State;
import game.Table;
import game.TableGame;

/**
 *
 * @author xavi
 */
public class OthelloEngine extends GameEngineAdapter {
    private int[] reversed;
    private Table table;

    
    public OthelloEngine() {
        super(2);
    }

    @Override
    public void reset() {
        try {
            Piece p0=game.getPlayer(0).getPiece();
            Piece p1=game.getPlayer(1).getPiece();
            table=((TableGame) game).getTable();
            table.put(3,3,p0);
            table.put(4,4,p0);
            table.put(3,4,p1);
            table.put(4,3,p1);
            status=State.RUNNING;
        } catch (GameException ex) {
        }
    }

    @Override
    public boolean isValid(GameAction move) {
        OthelloResult result=new OthelloResult((TableGame)game,(ClickAction)move);
        reversed=result.getReversed();
        return reversed[TOTAL]>0;
    }

    @Override
    public State doMove(GameAction move) {
        int idxplayer;
        
        ClickAction taction=(ClickAction) move;
        Piece piece=taction.getPlayer().getPiece();
        if (taction.getPlayer() == game.getPlayer(0)) idxplayer=0;
        else                                          idxplayer=1;
        int fila=taction.getFila();
        int columna=taction.getColumna();
        
        try {
            table.put(fila,columna,piece);
            // Reviramos as fichas
            // Up
            for(int idx=1;idx<=reversed[UP];idx++) replace(fila-idx,columna,piece);
            // Dn
            for(int idx=1;idx<=reversed[DOWN];idx++) replace(fila+idx, columna, piece);
            // Left
            for(int idx=1;idx<=reversed[LEFT];idx++) replace(fila, columna-idx, piece);
            // Dn
            for(int idx=1;idx<=reversed[RIGHT];idx++) replace(fila, columna+idx, piece);
            // Dn
            for(int idx=1;idx<=reversed[LEFTTOP];idx++) replace(fila-idx, columna-idx, piece);
            // Dn
            for(int idx=1;idx<=reversed[RIGHTBOTTOM];idx++) replace(fila+idx, columna+idx, piece);
            // Dn
            for(int idx=1;idx<=reversed[RIGHTTOP];idx++) replace(fila-idx, columna+idx, piece);
            // Dn
            for(int idx=1;idx<=reversed[LEFTBOTTOM];idx++) replace(fila+idx, columna-idx, piece);
        } catch (GameException ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
        OthelloResult result=new OthelloResult((TableGame)game);
        State state=result.getState();
        // Si o outro xogador non ten xogada, o saltamos.
        if (state==State.RUNNING) {
            if (!(result.hasMove(1-idxplayer))) game.nextTurn();
        } else {
            int[] count=result.getCount();
            if (count[idxplayer]>count[1-idxplayer]) state=State.WIN;
            else if (count[idxplayer]<count[1-idxplayer]) state=State.LOSE;
            else state=State.TIE;
        }
        return state;
    }

    private void replace(int fila,int columna,Piece piece) throws GameException {
        table.remove(fila,columna);
        table.put(fila,columna,piece);
    }
    
    /*@Override
    public Result getMoveResult(GameAction move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
