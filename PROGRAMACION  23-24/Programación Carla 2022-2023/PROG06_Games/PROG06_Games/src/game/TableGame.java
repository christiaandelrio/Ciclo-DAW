package game;

public class TableGame extends Game {
    private Table table;
    
    public TableGame(int nfilas,int ncols) {
        this.table=new Table(nfilas,ncols);
    }
    
    public Table getTable() {
        return table;
    }
}
