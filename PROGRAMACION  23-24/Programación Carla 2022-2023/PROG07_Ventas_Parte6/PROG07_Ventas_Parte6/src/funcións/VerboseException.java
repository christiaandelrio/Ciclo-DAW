package funcións;

import funcións.Level.Verbosity;
import static funcións.Level.Verbosity.*;
import java.util.Arrays;
import java.util.HashMap;


public class VerboseException extends Exception {
    HashMap<Integer,ErrorMessage> information;  // Permite recuperar rápidamente a mensaxe polo seu id
    private static Level level=LevelFactory.get(INFO);
    
    public VerboseException(String msg) {
        information=new HashMap<>();
        information.put(1,new ErrorMessage("1I",msg));
        
        StackTraceElement[] trace=getStackTrace();
        Arrays.stream(trace)
                .map((t)->"Line "+t.getLineNumber()+" in "+t.getMethodName()+" in "+t.getFileName())
                .forEach((m)->addMensaxe(DEBUG,1,m));
        /** Alternativamente ....
         for(StackTraceElement t: trace) {
            String m="Line "+t.getLineNumber()+" in "+t.getMethodName()+" in "+t.getFileName();
            addMensaxe(DEBUG,1,m);
         }
         */
    }
    
    public VerboseException(Exception e) {
        information=new HashMap<>();
        information.put(1,new ErrorMessage("1I",e.getMessage()));
        
        StackTraceElement[] trace=e.getStackTrace();
        Arrays.stream(trace)
                .map((t)->"Line "+t.getLineNumber()+" in "+t.getMethodName()+" in "+t.getFileName())
                .forEach((m)->addMensaxe(DEBUG,1,m));
        /** Alternativamente ....
         for(StackTraceElement t: trace) {
            String m="Line "+t.getLineNumber()+" in "+t.getMethodName()+" in "+t.getFileName();
            addMensaxe(DEBUG,1,m);
         }
         */
    }
    
    public VerboseException(String msg,String description) {
        this(msg);
        addMensaxe(ERR,1,description);
    }
   
    public static void setLevel(Verbosity level) {
        VerboseException.level=LevelFactory.get(level);
    }
    
    public int addMensaxe(Verbosity level,int id,String msg) {
        if (level==Verbosity.INFO) information.put(id, new ErrorMessage(id+"I",msg));
        else {
            ErrorMessage err=information.get(id);
            if (err==null) throw new IllegalArgumentException("Non existe erro de nivel previo");
            err.addDetail(level, msg);
        }
        return id;
    }
    
    public int addMensaxe(Verbosity level,String msg) {
        int id=information.keySet().stream().max((a,b)-> a-b).get()+1; 

        /* Alternativamente...
        int id=information.keySet().stream().mapToInt((i)->i).max().getAsInt()+1;
        */
        
        /*  Alternativamente....
        int id=0;
        for(int n:information.keySet()) {
            if (n>id) id=n; 
        }
        id++
        */
        return addMensaxe(level,id,msg);
    }
    
    public int addMensaxe(String msg) {
        return addMensaxe(Verbosity.INFO,msg);
    }
    
    @Override
    public String getMessage() {
        return Arrays.stream(level.messages(this)).map((s)->s+"\n").reduce("",String::concat);
        /** Alternativamente.... 
             String[] data=level.messages(this);
             String r="";
             for(String s:data) {
                 r=(r+"\n").concat(s);
             }
             return r;
        */
    }   
    
    public String getMessage(Verbosity v) {
        Level mylevel=LevelFactory.get(v);
        return Arrays.stream(mylevel.messages(this)).map((s)->s+"\n").reduce("",String::concat);
    }
}
