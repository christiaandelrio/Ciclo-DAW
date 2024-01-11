package funcións;

import java.util.stream.Stream;

public class DebugLevel implements Level {
    private static DebugLevel levelObject=null;

    
    public static DebugLevel getLevel() {
        if (DebugLevel.levelObject==null) DebugLevel.levelObject=new DebugLevel();
        return levelObject;
    }
    
    private DebugLevel() {    }
    
    @Override
    public String[] messages(VerboseException ve) {
        return ve.information.values().stream()
                .flatMap((ErrorMessage m)->Stream.concat(
                            Stream.of(m.toString()),
                            m.details.stream()
                                .filter((s)->s.id.endsWith("E")||s.id.endsWith("D"))
                                .map((s)->s.id.endsWith("D")?"\tDEBUG: "+s.toString():"\tERROR: "+s.toString())
                        )
                )
                .toArray(String[]::new);
        
        /** Alternativamente.... 
        Collection<ErrorMessage> errors=ve.information.values();
        ArrayList<String> list=new ArrayList<>();
        for(ErrorMessage emsg:errors) {
           list.add(emsg.toString());
           for(ErrorMessage msg: emsg.details)
            if (msg.id.endsWith("D")) list.add("\n\tDEBUG: "+msg.toString());
            else if (msg.id.endsWith("E")) list.add("\n\tERROR: "+msg.toString());
        }
        return list.toArray(new String[0]);
        */
    }
}