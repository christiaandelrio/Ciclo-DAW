package Adivina;

import game.GameAction;

/**
 *
 * @author xavi
 */
public class ChoosedNumberAction extends GameAction {
    private final int value;
    
    
    public ChoosedNumberAction(int number) {
        super(null,null);
        this.value=number;
    }

    public int getValue() {
        return value;
    }
    
}
