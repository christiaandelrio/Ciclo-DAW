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
public class ErrorLevel implements Level {
    private static ErrorLevel levelObject=null;
    
    public static ErrorLevel getLevel() {
        if (ErrorLevel.levelObject==null) ErrorLevel.levelObject=new ErrorLevel();
        return levelObject;
    }
    
    private ErrorLevel() {   }
    
    @Override
    public String[] messages(VerboseException e) {
        return e.information.values().stream()
                .flatMap((ErrorMessage m)->Stream.concat(
                                Stream.of(m.toString()),
                                m.details.stream()
                                    .filter((s)->s.id.endsWith("E"))
                                    .map((s)->"\tERROR: "+s.toString())
                            )
                )
                .toArray(String[]::new);
    }
    
}
