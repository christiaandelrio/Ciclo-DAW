package funciones;


import funciones.Level.Verbosity;
import funciones.LevelFactory;
import funciones.LevelFactory.*;
import static funciones.Level.Verbosity.*;
import java.util.HashMap;
import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yo
 */
public class VerboseException extends Exception {
    protected HashMap<Integer,ErrorMessage> information;
    private static Level level=LevelFactory.get(INFO);
    
    //Constructores
    public VerboseException(String msg){
        information=new HashMap<>();
        information.put(1, new ErrorMessage("1I",msg));
    }
    
    public VerboseException(String msg, String descripcion){
        information=new HashMap<>();
        information.put(1, new ErrorMessage("1I",msg));
        StackTraceElement[] trace=getStackTrace();
        Arrays.stream(trace)
                .map((t)->"Line "+t.getLineNumber()+" in "+t.getMethodName()+" in "+t.getFileName())
                .forEach((m)->addMensaxe(ERR,1,m));
    }
    
    public VerboseException(Exception e){
        information=new HashMap<>();
        information.put(1, new ErrorMessage("1I",e.getMessage()));
       StackTraceElement[] trace=e.getStackTrace();
        Arrays.stream(trace)
                .map((t)->"Line "+t.getLineNumber()+" in "+t.getMethodName()+" in "+t.getFileName())
                .forEach((m)->addMensaxe(DEBUG,1,m));
    }
    
    //Méotdos de clase
    public int addMensaxe(Verbosity level, int id, String msg){
       switch(level){
           case INFO:
               information.put(id,(new ErrorMessage(id+"I",msg)));
               break;
           case ERR:
               //Comprobamos si existe el objeto ErrorMessage
               ErrorMessage err=information.get(id);
               //Si no existe lanzamos una excepción
               if(err==null){
                   throw new IllegalArgumentException("No existe el error del nivel previo");
               }
               //Si existe, añadimos al atributo details del ErrorMessage
               err.addDetail(level, msg);
               break;
           case DEBUG:
               //Comprobamos si existe el objeto ErrorMessage
               ErrorMessage erro=information.get(id);
               //Si no existe lanzamos una excepción
               if(erro==null){
                   throw new IllegalArgumentException("No existe el error del nivel previo");
               }
               //Si existe, añadimos al atributo details del ErrorMessage
               erro.addDetail(level, msg);
               break;
       } 
       return id;
    }
    //Método sobrecargado que no recibe el id
    public int addMensaxe(Verbosity level, String msg){
        //El id se genera con el id siguiente al más alto de los existentes
        int id=information.keySet().stream().max((a,b)-> a-b).get()+1;
        //LLama al método anterior una vez generado el id
        return addMensaxe(level,id,msg);
    }
    //Método sobrecargado que no recibe ni id ni level
    public int addMensaxe(String msg){
        return addMensaxe(Verbosity.INFO,msg);
    }
    
    public static void setLevel(Verbosity level) {
        VerboseException.level=LevelFactory.get(level);
    }
    
    @Override
    public String getMessage() {
        return Arrays.stream(level.messages(this)).map((s)->s+"\n").reduce("",String::concat);
    }

    public boolean getMessage(Verbosity verbosity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
