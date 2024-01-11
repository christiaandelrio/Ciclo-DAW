/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import java.util.stream.Stream;

/**
 *
 * @author yo
 */
public class DebugLevel implements Level {
    private static DebugLevel levelObject=null;

    public static DebugLevel getLevel() {
        if (DebugLevel.levelObject==null) DebugLevel.levelObject=new DebugLevel();
        return levelObject;
    }
    
    private DebugLevel() {    }
    
    @Override
    public String[] messages(VerboseException e) {
        return e.information.values().stream()
                .flatMap((ErrorMessage m)->Stream.concat(
                            Stream.of(m.toString()),
                            m.details.stream()
                                .filter((s)->s.id.endsWith("E")||s.id.endsWith("D"))
                                .map((s)->s.id.endsWith("D")?"\tDEBUG: "+s.toString():"\tERROR: "+s.toString())
                        )
                )
                .toArray(String[]::new);
    }
    
}
