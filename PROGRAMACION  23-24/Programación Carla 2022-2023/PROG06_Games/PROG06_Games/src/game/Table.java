package game;

public class Table {
    private Piece[][] status;
    
    public Table(int nf,int nc) {
        status=new Piece[nf][nc]; //Array de nfxnc referencias a obxectos Piece.
    }
    
    public Piece get(int f,int c) throws GameException {
        if (!isIn(f,c)) throw new GameException("Posición ("+f+","+c+") fora do tableiro");
        return status[f][c];
    }
    
    public void put(int f,int c,Piece ficha) throws GameException {
        if (!isIn(f,c)) throw new GameException("Posición ("+f+","+c+") fora do tableiro");
        if (status[f][c]!=null) throw new GameException("Posición ("+f+","+c+") ocupada");
        status[f][c]=ficha;
    }
    
    public Piece remove(int f,int c) throws GameException {
        Piece p;
        if (!isIn(f,c)) throw new GameException("Posición ("+f+","+c+") fora do tableiro");
        p=status[f][c];
        status[f][c]=null;
        return p;
    }
    
    public void reset() {
        for(int f=0;f<status.length;f++) {
            for(int c=0;c<status[f].length;c++) status[f][c]=null;
        }
    }
    
    public boolean isIn(int f,int c) {
        return (f>=0 && f<status.length && c>=0 && c<status[0].length);
    }
    
    public int getHeight() {
        return status.length;
    }
    
    public int getWidth() {
        return status[0].length;
    }
}
