/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG05_Ejerc1_util;
import java.time.LocalDate;
/**
 *
 * @author Carla Portela Ubeira
 * Esta clase permite hacer validaciones
 * de que la fecha de matriculación introducida sea anterior a la fecha actual
 * y de que el DNI del propietario introducido sea correcto
 */
public class Validaciones {
    public static boolean comparaFecha (LocalDate fechaMatriculacion){
        return fechaMatriculacion.isBefore(LocalDate.now());
    }
    
    public static void validaDNI (String dniPropietario) throws Exception{
        DNI.validarNIF(dniPropietario);
    }

}
