package Othello;

import CatroenRaia.AutomaticCatroRaiaPlayer;
import CatroenRaia.CatroEnRaiaEngine;
import CatroenRaia.CatroEnRaiaGame;
import game.ConsoleTableColumnPlayer;
import game.ConsoleTablePlayer;
import game.ConsoleTableUI;
import game.Game;
import game.GameException;
import game.Piece;
import game.Player;
import game.TableGame;
import game.Ui;

public class OthelloGame extends TableGame {
    
    public OthelloGame(Player player1,Player player2,Ui ui) {
        super(8,8);
        this.gamers=new Player[] { player1,player2 };
        this.engine=new OthelloEngine();
        this.ui=ui;
    }
    
    public static void main(String[] args) {
        try {
            Player p1=new ConsoleTablePlayer("Xogador 1",new Piece('*'));
            Player p2=new AutomaticOthelloPlayer("Xogador 2",new Piece('+'));
            Game othello=new OthelloGame(p1,p2,new ConsoleTableUI());
            othello.start();
        } catch (GameException ex) {
            System.out.println("Erro iniciando xogo: "+ex.getMessage());
        }
    }
}
