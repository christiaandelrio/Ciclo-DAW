package funciones;


import java.util.ArrayList;
import funciones.Level.*;//Importamos la interfaz
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yo
 */
class ErrorMessage {
    String id;
    private String descripcion;
    ArrayList<ErrorMessage> details;//Se escoge ArrayList porque admite duplicados
    
    //Constructor

    public ErrorMessage(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.details=new ArrayList<>();
    }

    public ErrorMessage(String descripcion) {
        this.id="1I";//No recibe id como parámetro así que será este de forma predeterminada
        this.descripcion = descripcion;
        this.details=new ArrayList<>();
    }
    
    public void addDetail(Verbosity level, String msg){
        char sufijo='I';
        switch(level){
            case INFO:
                throw new IllegalArgumentException("Este erro e de nivel INFO.So é válido para o obxecto VerboseException");
            case ERR:
                sufijo='E';
                break;
            case DEBUG:
                sufijo='D';
                break;
        }
        //Añadimos un nuevo objeto ErrorMessage al ArrayList details mediante el método add y reemplazamos el id con el sufijo del switch
        details.add(new ErrorMessage(id.replace('I', sufijo), msg));
    }
    
    
}
