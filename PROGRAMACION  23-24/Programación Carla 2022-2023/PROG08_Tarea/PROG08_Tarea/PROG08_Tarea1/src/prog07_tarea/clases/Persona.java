/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog07_tarea.clases;

import prog07_tarea.interfaces.Imprimible;

/*
 * Clase Persona
 */

public class Persona implements Imprimible {
        protected String nombre;
        protected String apellidos;
        protected String dni;

        // Constructores
        // -------------
                
        // Constructor
        public Persona (String nombre, String apellidos, String dni) {           
            this.nombre= nombre;
            this.apellidos= apellidos;
            this.dni= dni;
        }
        
        // Constructor copia
        public Persona (Persona persona) {           
            this.nombre= persona.getNombre();
            this.apellidos= persona.getApellidos();
            this.dni= persona.getDni();
        }
        
        // Métodos get
        // -------------
        
        // Método getNombre
        protected String getNombre (){
            return nombre;
        }
        
        // Método getApellidos
        protected String getApellidos (){
            return apellidos;
        }
        
        // Método getFechaNacim
        protected String getDni (){
            return this.dni;
        }

        // Métodos set
        // -------------

        // Método setNombre
        protected void setNombre (String nombre){
            this.nombre= nombre;
        }
        
        // Método setApellidos
        protected void setApellidos (String apellidos){
            this.apellidos= apellidos;
        }
        
        // Método setFechaNacim
        protected void setFechaNacim (String dni){
            this.dni= dni;
        }       
                
        // Implementaci�n de los m�todos de la interfaz Imprimible
        // -------------------------------------------------------
        // Método devolverContenidoString 
        @Override
        public String devolverContenidoString () {
            String contenido= "Nombre: " + nombre + " Apellidos: " + apellidos + " DNI: " + dni;
            return contenido;
        }           
        
}


