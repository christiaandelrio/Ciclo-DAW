/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *  Un PlayEventHandler é calquera obxecto que teña a funcionalidade aquí definida
 *  E unha interface
 */
public interface PlayEventHandler {
    /**
     * Crea un bucle mentres o estado do xogo sexa RUNNING que se encarga de 
     * recoller a xogada do usuario (GameAction) e  despachala ao handler (handleEvent)
     */
    public void eventLoop();
    /**
     * Recibe unha acción do usuario (GameAction) e se encarga de que se leve a cabo segundo as regras do engine utilizado.
     * @param move 
     */
    public void handleEvent(GameAction move);
    
}
