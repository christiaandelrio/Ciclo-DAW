/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import Adivina.ChoosedNumberAction;
import game.GameAction;
import game.GameException;
import game.Player;
import java.util.Scanner;

/**
 *
 * @author xavi
 */
public class ConsoleNumberPlayer extends Player {

    @Override
    public GameAction getPlay() throws GameException {
        int num;
        
        while(true) {
            try {
                String input=game.read(getNick()+", introduce número:");
                if (input.equals("quit")) return null;
                num=Integer.parseInt(input);
                return new ChoosedNumberAction(num);
            } catch(NumberFormatException e) {
                System.out.println("Escribe un número");
            }
        }
    }
    
}
