package Othello;

import game.ClickAction;
import game.GameException;
import game.Piece;
import game.Result;
import game.State;
import game.Table;
import game.TableGame;

public class OthelloResult implements Result {
    static final int TOTAL=0;
    static final int UP=1;
    static final int DOWN=2;
    static final int LEFT=3;
    static final int RIGHT=4;
    static final int LEFTTOP=5;
    static final int RIGHTBOTTOM=6;
    static final int RIGHTTOP=7;
    static final int LEFTBOTTOM=8;
    
    
    private final Table table;
    private final Piece[] piece=new Piece[2];
    private Piece currentpiece;

    private final int[] reversed=new int[9];    // Pezas que se revirarían en cada dirección e total reviradas
    private final int[] count=new int[2];     // Numero de pezas que ten no taboleiro cada xogador
    private final int totreversed[][][]=new int[8][8][2];  // Cantas fichas revira cada xogador en cada casiña...
    private int idxplayer;

    // Para comprobar cantas reviraría e a validez da xogada
    public OthelloResult(TableGame game,ClickAction action) {
        this.table=game.getTable();
        piece[0]=game.getPlayer(0).getPiece();
        piece[1]=game.getPlayer(1).getPiece();
        currentpiece=action.getPlayer().getPiece();
        if (piece[0]==currentpiece) idxplayer=0;
        else                        idxplayer=1;
        scanReversed(idxplayer,action.getFila(),action.getColumna());
    }
    
    // Para comprobar o fin do xogo e si o xogador actual ten xogada.
    public OthelloResult(TableGame game) {
        this.table=game.getTable();
        this.piece[0]=game.getPlayer(0).getPiece();
        this.piece[1]=game.getPlayer(1).getPiece();
        scanTable();
    }
    
    public int[][][] getPossibleMoves() {
        return totreversed;
    }
    
    @Override
    public State getState() {
        State st=State.RUNNING;  // O xogo remata cando non existe xogada....
        if (!hasMove(0) && !hasMove(1)) {
            st=State.ENDED;
        }
        return st;
    }
    
    public int[] getReversed() {
        return reversed;
    }

    public int[] getCount() {
        return count;
    }
    
    public boolean hasMove(int idxplayer) {
        for(int f=0;f<table.getHeight();f++)
            for(int c=0;c<table.getWidth();c++)
                if (totreversed[f][c][idxplayer]!=0) return true;
        return false;
    }
    
    private void scanTable() {
        count[0]=0;
        count[1]=1;
        for(int f=0;f<table.getHeight();f++)
            for(int c=0;c<table.getWidth();c++) {
                try {
                    Piece p=table.get(f,c);
                    if (p==null) {
                        scanReversed(0,f,c);
                        scanReversed(1,f,c);
                    } else {  // Contamos as fichas de cada cor
                        if (p==this.piece[0]) count[0]++;
                        else                  count[1]++;
                        totreversed[f][c][0]=0;
                        totreversed[f][c][1]=0;
                    }
                } catch (GameException ex) {
                    System.out.println("ERROR: "+ex.getMessage());
                }
        }
    }
    
    private int scanReversed(int idxplayer,int fila,int columna) {
        // Contamos as que "reviramos" con esta Action
        // Up
        if (table.isIn(fila,columna)) {
            reversed[UP]=count(idxplayer,fila,columna,-1,0);
            reversed[TOTAL]=reversed[UP];
            // Dn
            reversed[DOWN]=count(idxplayer,fila,columna,1,0);
            reversed[TOTAL]+=reversed[DOWN];
            // Left
            reversed[LEFT]=count(idxplayer,fila,columna,0,-1);
            reversed[TOTAL]+=reversed[LEFT];
            // Right
            reversed[RIGHT]=count(idxplayer,fila,columna,0,1);
            reversed[TOTAL]+=reversed[RIGHT];
            // LeftTop
            reversed[LEFTTOP]=count(idxplayer,fila,columna,-1,-1);
            reversed[TOTAL]+=reversed[LEFTTOP];
            // RightBottom
            reversed[RIGHTBOTTOM]=count(idxplayer,fila,columna,1,1);
            reversed[TOTAL]+=reversed[RIGHTBOTTOM];
            // RightTop
            reversed[RIGHTTOP]=count(idxplayer,fila,columna,-1,1);
            reversed[TOTAL]+=reversed[RIGHTTOP];
            // LeftBottom
            reversed[LEFTBOTTOM]=count(idxplayer,fila,columna,1,-1);
            reversed[TOTAL]+=reversed[LEFTBOTTOM];
            totreversed[fila][columna][idxplayer]=reversed[TOTAL];
            return reversed[TOTAL];
        }
        return 0;
    }
        
    private int count(int idxplayer,int fila,int columna,int dy,int dx) {
        try {
            int f=fila+dy;
            int c=columna+dx;
            int cnt=0;

            while (table.isIn(f,c) && (table.get(f,c)!=null) && (table.get(f,c)!=piece[idxplayer])) {
                f=f+dy;
                c=c+dx;
                cnt++;
            }
            if (table.isIn(f,c) && (table.get(f,c)!=null)) return cnt;
            
        } catch (GameException ex) {
        }
        return 0;
    }
}
