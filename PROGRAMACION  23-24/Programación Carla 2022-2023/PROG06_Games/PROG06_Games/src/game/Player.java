package game;

/**
 *
 * @author xavi
 */
public abstract class Player {
    private final String nick;
    private final Piece ficha;
    protected boolean automatic;
    protected Game game;

    public Player() {
        this.nick=null;
        this.ficha=null;
    }
    
    public Player(String nick) {
        this.nick=nick;
        this.ficha=null;
    }
    
    public Player(String nick,Piece piece) {
        this.nick=nick;
        this.ficha=piece;
    }
    
    public Player(String nick,Piece piece,boolean automatic) {
        this.nick=nick;
        this.ficha=piece;
        this.automatic=automatic;
    }
    
    public void setGame(Game game) {
        this.game=game;
    }
    
    public boolean isAutomatic() {
        return automatic;
    }
    
    public String getNick() {
        if (nick==null) return "xogador";
        return nick;
    }
    
    public Piece getPiece() {
        return ficha;
    }
    
    /**
     * Dependendo do UI o xogador ten que obter a xogada dun xeito distinto
     * Ademáis tamén depende do xogo e de si é un xogador automatico ou non
     * @return
     * @throws GameException 
     */
    public abstract GameAction getPlay() throws GameException;
    
    @Override
    public String toString() {
        return nick+" ("+ficha+")";
    }
}
