package CatroenRaia;

import game.GameAction;
import game.GameEngineAdapter;
import game.GameException;
import game.Player;
import game.State;
import game.Table;
import game.TableGame;
import game.TableGameAction;

/**
 *
 * @author xavi
 */
public class CatroEnRaiaEngine extends GameEngineAdapter {

    public CatroEnRaiaEngine() {
        super(2);
    }

    @Override
    public boolean isValid(GameAction move) {
        TableGameAction ac=(TableGameAction) move;
        TableGame tgame=(TableGame) game;
        Table table=tgame.getTable();
        if (table.isIn(ac.getFila(),ac.getColumna())) {
            try {
                return (table.get(0, ac.getColumna())==null);
            } catch (GameException ex) {
            }
        }
        return false;
    }

    @Override
    public State doMove(GameAction move) {
        TableGame tgame=(TableGame) game;
        try {
            Player player=game.getCurrentPlayer();
            Table table=tgame.getTable();
            int height=table.getHeight();
            TableGameAction ac=(TableGameAction) move;
            int f=0;
            while(f<height && table.get(f,ac.getColumna())==null) f++;  // Caída da Ficha
            ac.setFila(f-1); // Actualizamos o move a posición onde cae a ficha
            table.put(ac.getFila(),ac.getColumna(),player.getPiece());
            CatroEnRaiaResult result=new CatroEnRaiaResult(tgame,move);
            status=result.getState();
        } catch (GameException ex) {
        }
        return status;
    }

    /* @Override
    public Result getMoveResult(GameAction move) {
        return new CatroEnRaiaResult((TableGame)game,move);
    } */

    @Override
    public void reset() {
        ((TableGame)game).getTable().reset();
        status=State.RUNNING;
    }
}
