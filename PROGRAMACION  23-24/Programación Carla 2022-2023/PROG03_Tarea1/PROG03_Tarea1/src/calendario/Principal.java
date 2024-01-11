/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package calendario;

import calendario.Fecha.enumMes;


/**
 *
 * @author yo
 */
public class Principal {

    public static void main(String[] args) {
        Fecha objFecha1 = new Fecha(enumMes.Julio); //instanciar un objeto de la clase Fecha con el primer constructor
        //actualizar los atributos dia y año
        objFecha1.setDia(20);
        objFecha1.setAnio(2022);
        System.out.println("----Primera fecha inicializada con el primer constructor----");
        //Mostrar la fecha en formato largo
        String objFecha1fecha = objFecha1.toString();
        System.out.println(objFecha1fecha);
        //Mostrar si la fecha es verano
        String objFecha1summer = objFecha1.isSummer()? "La fecha es verano" : "La fecha no es verano";
        System.out.println(objFecha1summer);
        
        System.out.println("----Segunda fecha inicializada con el segundo constructor----");
        Fecha objFecha2 = new Fecha(20, enumMes.Julio, 2022);
        //Mostrar el año de la fecha por pantalla
        System.out.println("El año es " +objFecha2.anio);
        String objFecha2fecha = objFecha2.toString();
        System.out.println(objFecha2fecha);
        String objFecha2summer = objFecha2.isSummer()? "La fecha es verano" : "La fecha no es verano";
        System.out.println(objFecha2summer); 
    }
}
