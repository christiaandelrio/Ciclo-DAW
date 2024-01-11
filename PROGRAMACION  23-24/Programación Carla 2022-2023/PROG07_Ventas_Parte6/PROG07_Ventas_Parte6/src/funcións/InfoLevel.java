package funcións;

import java.util.ArrayList;
import java.util.Collection;

public class InfoLevel implements Level {
    private static InfoLevel levelObject=null;
    
 
    public static InfoLevel getLevel() {
        if (InfoLevel.levelObject==null) InfoLevel.levelObject=new InfoLevel();
        return levelObject;
    }
    
    private InfoLevel() {}
    
    @Override
    public String[] messages(VerboseException ve) {
        return ve.information.values().stream()
                .map((em)->(em.id.equals("1I"))?em.toString():"\t"+em.toString())
                .toArray(String[]::new);
        /** Alternativamente.... 
        Collection<ErrorMessage> errors=ve.information.values();
        ArrayList<String> list=new ArrayList<>();
        for(ErrorMessage msg:errors) list.add(msg.toString());
        return list.toArray(new String[0]);
        */
    }
    
}
