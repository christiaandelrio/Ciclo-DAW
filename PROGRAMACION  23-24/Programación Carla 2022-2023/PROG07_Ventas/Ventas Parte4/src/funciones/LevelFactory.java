/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import funciones.Level.Verbosity;
import static funciones.Level.Verbosity.*;

/**
 *
 * @author yo
 */
class LevelFactory {
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
