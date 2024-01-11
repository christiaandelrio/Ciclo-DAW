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
public class ClickAction extends GameAction {
    private final int fila;
    private final int columna;
    
    public ClickAction(Player player,int fila,int columna) {
        super(player, Action.CLICK);
        this.fila=fila;
        this.columna=columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
 
    @Override
    public String toString() {
        return "("+(fila+1)+","+(columna+1)+")";
    }
}
