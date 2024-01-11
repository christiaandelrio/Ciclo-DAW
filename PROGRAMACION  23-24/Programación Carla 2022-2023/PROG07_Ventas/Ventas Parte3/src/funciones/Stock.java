/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones;

/**
 *
 * @author yo
 */
public interface Stock <K,V extends Number>{
    V getSTock(K codigo);
    void addStock(K codigo, V num) throws VerboseException;
    void setStock(K codigo, V num)throws VerboseException;
}
