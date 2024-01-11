/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

import java.util.Collection;

/**
 *
 * @author yo
 */
public interface Printer<T>{
    default Object mapNULL(Object obj){
        if (obj==null) return "";
        return obj;
    }
    
    default String clean(String str){
        str=str.replace(","," ");
        return str;
    }
    
    public String[] toCSV(Collection<T> list) throws Exception;
    public String toRow(T object) throws Exception;
    public String print(T object) throws Exception;
}
