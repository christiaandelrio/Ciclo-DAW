package game;

import java.util.Scanner;

/**
 *
 * @author xavi
 */
public class ConsoleSimpleUI implements Ui {
    protected Game game;
    
    @Override
    public void setGame(Game game) {
        this.game=game;
    }

    @Override
    public void showMessage(String title, String msg) {
        System.out.println(title+": "+msg);
    }

    @Override
    public String read(String msg) {
        Scanner scn=new Scanner(System.in);
        System.out.print(msg+" ");
        return scn.nextLine();
    }
    
}
