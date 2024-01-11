package Adivina;

import game.Game;
import game.GameException;
import game.Player;
import game.State;
import game.ConsoleNumberPlayer;
import game.ConsoleSimpleUI;


public class AdivinaGame extends Game {
    public AdivinaGame() {
        gamers=new Player[] { new ConsoleNumberPlayer() };
        engine=new AdivinaGameEngine(100);
        ui=new ConsoleSimpleUI();
    }
    
    @Override
    public void result() {
        AdivinaGameEngine e=(AdivinaGameEngine) engine;
        if (engine.getState()==State.WIN) ui.showMessage("Noraboa!", "Acertaches en "+e.getIntentos()+" intentos");
        else if (engine.getState()==State.LOSE) ui.showMessage("Eres algo torpe", "O número era "+e.getNumber());
    }
    
    public static void main(String[] args) {
        try {
            AdivinaGame adivina=new AdivinaGame();
            adivina.start();
        } catch (GameException ex) {
            System.out.println("Erro iniciando xogo: "+ex.getMessage());
        }
    }
}
