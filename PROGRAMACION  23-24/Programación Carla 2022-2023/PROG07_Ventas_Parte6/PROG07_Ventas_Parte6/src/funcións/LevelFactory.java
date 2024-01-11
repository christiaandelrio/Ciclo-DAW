package funcións;

import funcións.Level.Verbosity;
import static funcións.Level.Verbosity.INFO;


public class LevelFactory  {
    public static Level get(Verbosity level) {
        Level lvl=null;
        switch(level) {
            case INFO: lvl=InfoLevel.getLevel(); break;
            case ERR: lvl=ErrorLevel.getLevel(); break;
            case DEBUG: lvl=DebugLevel.getLevel(); break;
        }
        return lvl;
    }
}
