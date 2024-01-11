/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calendario;

/**
 *
 * @author yo
 */
public class Fecha {
  public enum enumMes {Enero, Febrero, Marzo, Abril, Mayo, Junio, Julio, Agosto, Septiembre, Octubre, Noviembre, Diciembre};  
  public int dia;
  public enumMes mes;
  public int anio;
  //Se declara un tipo enumerado y los atributos dia, mes y año
  public Fecha (enumMes mes) {
      //se crea un constructor que inicialice el mes al valor recibido
      //y los demás atributos a 0.
      dia=0;
      this.mes=mes;
      anio=0;
  }
  public Fecha (int dia, enumMes mes, int anio){
      //se declara un constructor que inicializa todos los atributos
      this.dia=dia;
      this.mes=mes;
      this.anio=anio;
  }
  public int getDia(){
      return dia;
  }
  public void setDia(int dia) {
      this.dia = dia;
  }
  public enumMes getMes(){
      return mes;
  }
  public void setMes(enumMes mes) {
      this.mes = mes;
  }
  public int getAnio(){
      return anio;
  }
  public void setAnio(int anio) {
      this.anio = anio;
  }
  public boolean isSummer(){
      boolean summer = ((dia >= 21) && (mes == enumMes.Junio)) || ((dia <= 23) && (mes == enumMes.Septiembre)) || ((mes == enumMes.Julio) || (mes == enumMes.Agosto));
      return summer;
   }
  @Override
  public String toString(){
      String fecha = "La fecha es " +dia +" de " +mes +" de " +anio;
      return fecha;
  }
}
      