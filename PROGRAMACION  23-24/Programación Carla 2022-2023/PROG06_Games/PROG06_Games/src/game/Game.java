package game;

public abstract class Game {
    protected  Ui ui;
    protected  GameEngine engine;
    protected  PlayEventHandler handler;
    protected  Player[] gamers;
    
    protected Game() {
        this.ui=null;
        this.engine=null;
        this.handler=new GameHandler(this);
        this.gamers=null;
    }
    
    public void start() throws GameException {
        if (engine==null) throw new GameException("Debes instalar o Engine (Motor do Xogo)");
        if (ui==null) throw new GameException("Debes indicar unha interface de usuario");
        engine.setGame(this);
        ui.setGame(this);
        for(Player p:gamers) p.setGame(this);
        reset();
    }
    
    public void reset() throws GameException {
        engine.reset();
        ui.start(handler);
    }
    
    public Player nextTurn() {
        return gamers[engine.nextTurn()];
    }
    public Player getCurrentPlayer() {
        return gamers[engine.getTurn()];
    }
    
    public Player getPlayer(int idx) {
        return gamers[idx];
    }
    
    public int getTurn() {
        return engine.getTurn();
    }
    
    protected void result() {
        switch(engine.getState()) {
            case WIN: System.out.println("Victoria!. Noraboa, "+getCurrentPlayer()); break;
            case TIE: System.out.println("Empate!!"); break;
            case LOSE: System.out.println("O sinto, "+getCurrentPlayer()+", perdeches."); break;
        }
    }

    public State getState() {
        return engine.getState();
    }
    
    public State play(GameAction move) throws GameException {
        State st=engine.play(move);
        return st;
    }
    
   public void refreshUI() throws GameException {
       ui.refresh();
   }
 
   public void showMessage(String title,String text) {
       ui.showMessage(title,text);
   }

   public String read(String msg) {
       return ui.read(msg);
   }

    void setState(State state) {
        engine.setState(state);
    }

}
