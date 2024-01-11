package Othello;

import game.ClickAction;
import game.GameAction;
import game.GameException;
import game.Piece;
import game.Player;
import game.TableGame;

/**
 *
 * @author xavi
 */
public class AutomaticOthelloPlayer extends Player {
    private int[][][] reversibles;
    private int myplayer;
    private int rows;
    private int cols;

    AutomaticOthelloPlayer(String nick, Piece piece) {
        super(nick,piece);
    }
    
    @Override
    public GameAction getPlay() throws GameException {
        TableGame tgame=(TableGame)game;
        OthelloResult result=new OthelloResult(tgame);
        reversibles=result.getPossibleMoves();
        if (game.getPlayer(0)==this) myplayer=0;
        else                         myplayer=1;
        rows=tgame.getTable().getHeight();
        cols=tgame.getTable().getWidth();
        GameAction m=corners();
        if (m==null) m=maxreversed();
        if (m==null) m=excluded();
        game.showMessage(this.toString(),"Xogada en "+m);
        return m;
    }
    
     private GameAction corners() {
        ClickAction ret=null;
        int max;
        int rowi=0;
        int rowf=rows-1;
        int coli=0;
        int colf=cols-1;
        // Percorre primeiro o rectangulo externo, (0,0)-(0,7)-(7,7),(7,0)
        // Logo  o rectángulo                      (1,1) - (1,6), (6,6) (6,1)
        // ...
        // Colle o sitio que más revira, excluíndo as casiñas que non me interesa
        // poñer ficha (exclude)
        while(rowi<rowf) {
            max=0;
            while(coli<colf) {
                // Dende Esquina 0,0
                if (!exclude(rowi,coli,rows,cols)) {
                    if (reversibles[rowi][coli][myplayer]>max) {
                        ret=new ClickAction(this,rowi,coli);
                        max=reversibles[rowi][coli][myplayer];
                    }
                }
       
                // Dende Esquina 0,7
                if (!exclude(rowi,colf,rows,cols)) {
                    if (reversibles[rowi][colf][myplayer]>max) {
                        ret=new ClickAction(this,rowi,colf);
                        max=reversibles[rowi][colf][myplayer];
                    }
                }
                
                // Dende Esquina 7,7
                if (!exclude(rowf,colf,rows,cols)) {
                    if (reversibles[rowf][colf][myplayer]>max) {
                        ret=new ClickAction(this,rowf,colf);
                        max=reversibles[rowf][colf][myplayer];
                    }
                }
                
                // Dende Esquina 7,0
                if (!exclude(rowf,coli,rows,cols)) {
                    if (reversibles[rowf][coli][myplayer]>max) {
                        ret=new ClickAction(this,rowf,coli);
                        max=reversibles[rowf][coli][myplayer];
                    }
                }
                coli++;
                colf--;
            }
            rowi++;
            rowf--;
            coli=rowi;
            colf=rowf;
        }
        return ret;
    }
    
     // Retorna a casiña onde máis revira, excluíndo as indicadas por exclude
    private GameAction maxreversed() {
        ClickAction ret=null;
        int max=0;
        for(int fila=0;fila<rows;fila++)
            for(int columna=0;columna<cols;columna++) {
                //System.out.println(fila+","+columna+"="+Arrays.toString(reversibles[fila][columna]));
                if (!exclude(fila,columna,rows,cols)) {
                    if (reversibles[fila][columna][myplayer]>max) {
                        ret=new ClickAction(this,fila,columna);
                        max=reversibles[fila][columna][myplayer];
                    }
                }
            }
        return ret;
    }
    
    // Das excluídas, (porque non queda mais remedio) collemos a que máis revira
    private GameAction excluded() {
        ClickAction ret=null;
        int max=0;
        for(int fila=0;fila<rows;fila++)
            for(int columna=0;columna<cols;columna++) {
                if (exclude(fila,columna,rows,cols)) {
                    if (reversibles[fila][columna][myplayer]>max) {
                        ret=new ClickAction(this,fila,columna);
                        max=reversibles[fila][columna][myplayer];
                    }
                }
            }
        return ret;    
    }
    
    /**
     * Podemos engadir a exclude as casiñas que consideremos para mellorar a estratexia
     */
    private boolean exclude(int f,int c,int nfilas,int ncols) {
        int[][] excluded={ {0,1},{1,0},{1,1},{0,ncols-2},{1,ncols-2},{nfilas-2,0},{nfilas-2,1},
                           {nfilas-2,ncols-2},{1,ncols-1},{nfilas-2,ncols-1},{nfilas-1,1},{nfilas-1,ncols-2}
        };
        /*for(int[] mv: excluded) {
            if ((mv[0]==f)&&(mv[1]==c)) return true;
        }*/
        return false;
    }
}
