package game;

/**
 *
 * @author xavi
 */
public class TableGameAction extends GameAction {
    private int fila;
    private int columna;

    public TableGameAction(Player player, Action action) {
        super(player, action);
    }
    
    public TableGameAction(Player player,int fila,int columna) {
        super(player,Action.CLICK);
        this.fila=fila;
        this.columna=columna;
    }
    
    public int getFila() {
        return fila;
    }
    
    public int getColumna() {
        return columna;
    }
    
    public void setFila(int f) {
        this.fila=f;
    }
    
    public void setColumna(int c) {
        this.columna=c;
    }
    
        
    @Override
    public String toString() {
        return "move("+fila+","+columna+")";
    }
}
