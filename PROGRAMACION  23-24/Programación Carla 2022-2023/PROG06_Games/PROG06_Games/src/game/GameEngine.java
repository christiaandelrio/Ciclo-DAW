package game;

/**
 *
 * E calquera obxecto que dispoña da seguinte funcionalidade. 
 * O xeito de implantar a funcionalidade depende do xogo concreto
 * Polo tanto é unha interface
 */
public interface GameEngine {
    public void setGame(Game game);
    public void reset();
    public default State play(GameAction move) throws GameException {
        if (isValid(move)) {
            doMove(move);
        } else throw new GameException("A xogada "+move+" é errónea");
        return getState();
    };
    public int nextTurn();
    public int getTurn();
    public State getState();
    public void setState(State status);
    public boolean isValid(GameAction move);
    public State doMove(GameAction move);
    //public Result getMoveResult(GameAction move);
}
