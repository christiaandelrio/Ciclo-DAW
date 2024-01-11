package Adivina;

import game.GameAction;
import game.GameEngineAdapter;
import game.GameException;
import game.Result;
import game.State;

public class AdivinaGameEngine extends GameEngineAdapter {
    private final int MAX_INTENTOS=5;
    private int num;
    private final int max;
    private int intentos;
     
    public AdivinaGameEngine(int max) {
        super(1);
        this.max=max;
    }
 
    @Override
    public State play(GameAction move) throws GameException {
        ChoosedNumberAction n=(ChoosedNumberAction) move;
        if (n.getValue()==num) status=State.WIN;
        else if (intentos==MAX_INTENTOS) status=State.LOSE;
        else {
            intentos++;
            if (n.getValue()<num) game.showMessage("Fallaches!","Maior");
            else                game.showMessage("Fallaches!","Menor");
        }
        return status;
    }

    public int getIntentos() {
        return intentos;
    }
    
    public int getNumber() {
        return num;
    }

    
    @Override
    public void reset() {
        num=(int)Math.round(Math.random()*max);
        intentos=0;
        game.showMessage("Adivina","Debes indicar un número entre 0 e "+max);
        status=State.RUNNING;
    }

    
    @Override
    public boolean isValid(GameAction move) {
        return true;
    }

    @Override
    public State doMove(GameAction move) {
        return status;
    }

    /*@Override
    public Result getMoveResult(GameAction move) {
        return null;
    }*/
}
