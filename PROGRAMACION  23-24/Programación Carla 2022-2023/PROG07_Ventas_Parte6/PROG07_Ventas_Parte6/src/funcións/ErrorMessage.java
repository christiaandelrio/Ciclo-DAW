package funcións;

import funcións.Level.Verbosity;
import static funcións.Level.Verbosity.*;
import java.util.ArrayList;


public class ErrorMessage {
    String id;
    String description;
    ArrayList<ErrorMessage> details;
    
    public ErrorMessage(String id,String msg) {
        this.id=id;
        this.description=msg;
        this.details=new ArrayList<>();
    }
    
    public ErrorMessage(String msg) {
        this.id="1I";
        this.description=msg;
        this.details=new ArrayList<>();
    }
    
    public void addDetail(Verbosity level,String msg) {
        char suffix='I';
    
        switch(level) {
            case INFO:
                throw new IllegalArgumentException("Este erro é de nivel INFO. So é válido para a clase VentasException");
            case ERR:
                suffix='E';
                break;
            case DEBUG:
                suffix='D';
                break;
        }
        details.add(new ErrorMessage(id.replace('I',suffix),msg));
    }
    
    @Override
    public String toString() {
        return description;
    }
}
