package game;

public abstract class GameEngineAdapter implements GameEngine {
    protected Game game;
    protected int turno;
    protected State status;
    protected final int maxplayers;
    
    public GameEngineAdapter(int maxplayers) {
        this.maxplayers=maxplayers;
        this.turno=0;
        this.status=State.STOPPED;
    }
    
     @Override
    public void setGame(Game game) {
        this.game=game;
    }

    @Override
    public int nextTurn() {
        turno=(turno+1)%maxplayers;
        return turno;
    }

    @Override
    public int getTurn() {
        return turno;
    }

    @Override
    public State getState() {
        return status;
    }

    @Override
    public void setState(State status) {
        this.status=status;
    }

    @Override
    public abstract void reset();

    @Override
    public abstract boolean isValid(GameAction move);

    @Override
    public abstract State doMove(GameAction move);

   /* @Override
    public abstract Result getMoveResult(GameAction move);
    */
}
