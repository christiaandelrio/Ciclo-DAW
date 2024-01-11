package funcións;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class ErrorLevel implements Level {
    private static ErrorLevel levelObject=null;
    
    
    public static ErrorLevel getLevel() {
        if (ErrorLevel.levelObject==null) ErrorLevel.levelObject=new ErrorLevel();
        return levelObject;
    }
    
    private ErrorLevel() {   }
    
    @Override
    public String[] messages(VerboseException ve) {
        return ve.information.values().stream()
                .flatMap((ErrorMessage m)->Stream.concat(
                                Stream.of(m.toString()),
                                m.details.stream()
                                    .filter((s)->s.id.endsWith("E"))
                                    .map((s)->"\tERROR: "+s.toString())
                            )
                )
                .toArray(String[]::new);
        /** Alternativamente.... */
        /*Collection<ErrorMessage> errors=ve.information.values();
        ArrayList<String> list=new ArrayList<>();
        for(ErrorMessage emsg:errors) {
           list.add(emsg.toString());
           for(ErrorMessage msg: emsg.details)
                if (msg.id.endsWith("E")) list.add("\tERROR: "+msg.toString()+"\n");
        }
        return list.toArray(new String[0]);*/
        
    }
}
