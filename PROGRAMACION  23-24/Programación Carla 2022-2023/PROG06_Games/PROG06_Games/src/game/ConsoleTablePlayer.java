package game;

/**
 *
 * @author xavi
 */
public class ConsoleTablePlayer extends Player {

    public ConsoleTablePlayer(String nick,Piece ficha) {
        super(nick,ficha);
    }
    @Override
    public GameAction getPlay() throws GameException {
        int fila;
        int columna;
        
        while(true) {
            try {
                game.showMessage(this.toString(),"(quit para cancelar o xogo)");
                String fc=game.read("(fila,columna): ");
                if (fc.equals("quit")) return null;
                String[] data=fc.split(",");
                if (data.length!=2) return null;
                fila=Integer.parseInt(data[0])-1;
                columna=Integer.parseInt(data[1])-1;
                return new ClickAction(this,fila,columna);
            } catch(NumberFormatException e) {
                System.out.println("Escribe un a fila e a columna separadas por unha coma");
            }
        }
    }   
}
