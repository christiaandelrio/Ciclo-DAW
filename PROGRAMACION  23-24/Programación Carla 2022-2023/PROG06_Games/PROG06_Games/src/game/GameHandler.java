package game;

/**
 *
 * @author xavi
 */
public class GameHandler implements PlayEventHandler {
    protected final Game game;
    
    public GameHandler(Game game) {
        this.game=game;
    }
    
    @Override
    public void eventLoop() {
        while(game.getState()==State.RUNNING) {
            try {
                Player player=game.getCurrentPlayer();
                GameAction action=player.getPlay();
                if (action==null)   game.setState(State.ENDED);
                else                handleEvent(action);
            } catch (GameException ex) {
                System.out.println("ERROR: "+ex.getMessage());
                System.exit(0);
            }
        }
    }

    @Override
    public void handleEvent(GameAction move) {
        try {
            game.play(move);
            if (game.getState()==State.RUNNING) {
                Player player=game.nextTurn();
                while(player.isAutomatic() && game.getState()==State.RUNNING) {
                    game.refreshUI();
                    GameAction action=player.getPlay();
                    game.play(action);
                    if (game.getState()==State.RUNNING)
                        player=game.nextTurn();
                }
            } 
            game.refreshUI();
            if (game.getState()!=State.RUNNING) game.result();
        } catch(GameException ex) {
            game.showMessage("ERROR","Xogada Errónea");
        }
    }
}
