/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CatroenRaia;

import game.ConsoleTableColumnPlayer;
import game.ConsoleTableUI;
import game.Game;
import game.GameException;
import game.Piece;
import game.Player;
import game.TableGame;
import game.Ui;

/**
 *
 * @author xavi
 */
public class CatroEnRaiaGame extends TableGame {
    public CatroEnRaiaGame(Player player1,Player player2,Ui ui) {
        super(6,7);
        this.gamers=new Player[] { player1,player2 };
        this.engine=new CatroEnRaiaEngine();
        this.ui=ui;
    }
    
    public static void main(String[] args) {
        try {
            Player p1=new ConsoleTableColumnPlayer("Xogador 1",new Piece('*'));
            Player p2=new AutomaticCatroRaiaPlayer("Xogador 2",new Piece('+'));
            Game catroenraia=new CatroEnRaiaGame(p1,p2,new ConsoleTableUI());
            catroenraia.start();
        } catch (GameException ex) {
            System.out.println("Erro iniciando xogo: "+ex.getMessage());
        }
    }
    
}
