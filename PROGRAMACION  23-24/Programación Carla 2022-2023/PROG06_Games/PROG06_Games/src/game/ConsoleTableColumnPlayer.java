/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author xavi
 */
public class ConsoleTableColumnPlayer extends Player {

    public ConsoleTableColumnPlayer(String nick, Piece piece) {
        super(nick,piece);
    }
     @Override
    public GameAction getPlay() throws GameException {
        int columna;
        
        while(true) {
            try {
                game.showMessage(getNick(),"(quit para cancelar o xogo)");
                String c=game.read("Columna: ");
                if (c.equals("quit")) return null;
                columna=Integer.parseInt(c)-1;
                return new TableGameAction(this,0,columna);
            } catch(NumberFormatException e) {
                System.out.println("Escribe un número");
            }
        }
    }   
    
}
