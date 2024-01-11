
package game;

/**
 *  Un Ui é calquera clase que dispoña desta funcionalidade.
 *  Non é posible crear esa funcionalidade si non sabmeos como é a Ui.
 *  E unha interface
 */
public interface Ui {
    public void setGame(Game game);
    
    public default void start(PlayEventHandler handler) throws GameException {
        refresh();
        handler.eventLoop();
    }
    
    public default void refresh() {
    };

    public void showMessage(String title,String msg);
    public String read(String msg);
}
