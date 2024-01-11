package Buscaminas;

import game.ClickAction;
import game.GameAction;
import game.GameEngineAdapter;
import game.GameException;
import game.Piece;
import game.State;
import game.Table;
import game.TableGame;
import java.util.Random;


public class BuscaminasEngine extends GameEngineAdapter {
    private int nbombas;
    private int count;
    private Table table;
    private int rows;
    private int cols;

    public BuscaminasEngine(int nbombas) {
        super(1);
        this.nbombas=nbombas;
    }
    @Override
    public boolean isValid(GameAction move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State doMove(GameAction move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void reset() {
        try {
            table=((TableGame)game).getTable();
            rows=table.getHeight();
            cols=table.getWidth();
            count=cols*rows-nbombas;
            setBombs();
            fill();
            status=State.RUNNING;
        } catch (GameException ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
    }

    @Override
    public State play(GameAction action) throws GameException {
        switch(action.getAction()) {
            case CLICK:
                ClickAction play=(ClickAction)action;
                Mine mine=(Mine)table.get(play.getFila(),play.getColumna());
                
                if (mine.isBomb()) {
                    showAll();
                    this.status=State.LOSE;
                } else if (mine.hidden) {
                    show(play.getFila(),play.getColumna(),rows,cols);
                    if (count==0) this.status=State.WIN;
                }
                break;
        }
        return status;

    }
    
    private void setBombs() throws GameException {
        Random rnd=new Random();
        int totcells=rows*cols;
        ClickAction[] moves=new ClickAction[totcells];
        int b=0;
        int pos;
        
        if (nbombas > (int)Math.ceil(cols*rows*80/100)) throw new GameException("Demasiadas Bombas");
        
        for(int idx=0;idx<totcells;idx++) moves[idx]=new ClickAction(game.getPlayer(0),idx/cols,idx%cols);
        
        while(b<nbombas) {
            try {
                pos=rnd.nextInt(totcells);
                table.put(moves[pos].getFila(),moves[pos].getColumna(), Mine.getBomb());
                moves[pos]=null;
                
                // Copio todos menos os null
                pos=0;
                for(int idx=0;idx<totcells;idx++) {
                    if (moves[idx]!=null) {
                        moves[pos]=moves[idx];
                        pos++;
                    }
                }
                totcells--;
                b++;
            } catch (GameException ex) {
            }
        }
    }
    
    private void fill() throws GameException {
        for(int f=0;f<rows;f++)
            for(int c=0;c<cols;c++) {
                if (table.get(f,c)==null) table.put(f,c,getMine(f,c));
            }
    }
    
    private Mine getMine(int f,int c) throws GameException {
        int countmines=0;
        
        if (f > 0) {           
            if (isBomb(table.get(f-1, c))) countmines++;
            if ((c > 0) && (isBomb(table.get(f-1, c-1)))) countmines++;
            if ((c < cols-1) && (isBomb(table.get(f-1, c+1)))) countmines++;
        }
        if (f < rows-1) {
            if (isBomb(table.get(f+1, c))) countmines++;
            if ((c > 0) && (isBomb(table.get(f+1, c-1)))) countmines++;
            if ((c < cols-1) && (isBomb(table.get(f+1, c+1)))) countmines++;
        }
        if ((c > 0) && (isBomb(table.get(f, c-1)))) countmines++;
        if ((c < cols-1) && (isBomb(table.get(f, c+1)))) countmines++;
        return Mine.getMine(countmines);
    }
    
    private boolean isBomb(Piece piece) {
        if (piece==null) return false;
        return ((Mine)piece).isBomb();
    }
    
    private void show(int f,int c,int rows,int cols) throws GameException {
        if ((f<0)||(f>=rows)||(c<0)||(c>=cols)) return;
        Mine mine=(Mine)table.get(f, c);
        
        if ((mine!=null)&&(mine.hidden)) {
            mine.show();
            count--;
            if (mine.value==0) {
                show(f-1,c-1,rows,cols);
                show(f-1,c,rows,cols);
                show(f,c-1,rows,cols);
                show(f,c+1,rows,cols);
                show(f+1,c-1,rows,cols);
                show(f+1,c,rows,cols);
                show(f+1,c+1,rows,cols);
            }
        }
    }
    
    private void showAll() throws GameException {
        for(int f=0;f<rows;f++)
            for(int c=0;c<cols;c++) {
                Mine m=(Mine) table.get(f, c);
                m.show();
            }
    }
}
