package game;

public class GameAction {
    Action action;
    Player player;

    public GameAction(Player player,Action action) {
        this.action = action;
        this.player = player;
    }
    
    public Action getAction() {
        return action;
    }
    
    public Player getPlayer() {
        return player;
    }
}
