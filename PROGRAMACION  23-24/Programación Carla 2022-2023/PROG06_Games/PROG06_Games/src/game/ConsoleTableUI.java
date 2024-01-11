/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xavi
 */
public class ConsoleTableUI extends ConsoleSimpleUI {
    public void refresh() {
        Table t=((TableGame)game).getTable();
        int filas=t.getHeight();
        int columnas=t.getWidth();
        
        System.out.print("  ");
        for(int idx=1;idx<=columnas;idx++) {
            System.out.print(" "+String.format("%2d",idx));
        }
        System.out.println();
        for(int f=1;f<=filas;f++) {
            System.out.print(String.format("%2d",f));
            for(int c=1;c<=columnas;c++) {
                try {
                    Piece p=t.get(f-1,c-1);
                    if (p==null)    System.out.print("   ");
                    else            System.out.print("  "+p.getId());
                } catch (GameException ex) {
                }
            }
            System.out.println();
        }
    }
}
