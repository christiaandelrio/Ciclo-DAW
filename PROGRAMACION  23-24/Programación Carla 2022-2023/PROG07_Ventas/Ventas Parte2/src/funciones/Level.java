package funciones;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yo
 */
public interface Level{
    enum Verbosity { INFO, ERR, DEBUG };
    public String[] messages(VerboseException e);

}
