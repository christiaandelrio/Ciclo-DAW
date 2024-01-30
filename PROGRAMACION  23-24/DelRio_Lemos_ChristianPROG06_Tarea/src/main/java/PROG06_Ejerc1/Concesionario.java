/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG06_Ejerc1;

/**
 *
 * @author chris
 */
public class Concesionario {
    
    //La clase debe permitir guardar vehículos, siendo el número máximo de estos 50
    private int numVehiculos; //Número de vehículos
    private Vehiculo[] vehiculos; //Creo un array que almacenará cada uno de los vehículos creados correctamente

    //Constructores
    
    public Concesionario() {
        this.vehiculos = new Vehiculo[50]; //Inicializo el array dándole un tamaño de 50
        this.numVehiculos = 0; //Inicializo el número de vehículos en 0
    }
       
    public String buscaVehiculo(String matricula){
        
        //Vamos a recorrer el array de vehículos hasta el número de vehículos que haya en el momento
        for (int i = 0; i < numVehiculos; i++) {
            
          Vehiculo v = vehiculos[i]; //En cada vuelta del bucle almacena un vehículo que se irá comprobando
          
          if(v.getMatricula().equals(matricula)){ //Obtengo la matricula del vehiculo i y la comparo con la introducida
              return v.getMatricula() + "" + v.getPrecio() + "" + v.getMarca(); //Devuelvo lo que se me solicita del vehículo encontrado
          }
            
        }
        return null; //Si no encuentra coincidencias devuelve null
    }
    
    public int insertarVehiculo(Vehiculo v){
        
        if (this.numVehiculos == vehiculos.length) { //Si el concesionario está lleno devuelvo -1
            return -1;
        }
        
        if(this.buscaVehiculo(v.getMatricula()) != null){
            return -2;
        }else{
            this.vehiculos[numVehiculos]= v; //Guardamos el vehículo v en el array vehículos en la posición numVehiculos si va todo bien
            this.numVehiculos++; //Incrementamos el número de vehículos del concesionario y devolvemos 0
            return 0;        
        }
        
    }
    
    //Listar por pantalla los datos de todos los vehículos del concesionario
    public void listaVehiculos(){
         for (int i = 0; i < numVehiculos; i++) {
            
          System.out.println("Vehículo "+i+" con datos: " + this.vehiculos[i].getMarca()+","+this.vehiculos[i].getDescripcion()
                             +","+this.vehiculos[i].getMatricula()+","+this.vehiculos[i].getNombrePropietario());
            
        }       
    }
    
    //Actualizar los kilómetros
    public boolean actualizaKms(String matricula, double kilometros){
       for (int i = 0; i < numVehiculos; i++) {
            
          Vehiculo v = vehiculos[i]; 
          
          if(v.getMatricula().equals(matricula)){ //Si la matrícula introducida coincide con alguna
              v.setKilometros(kilometros);
              return true;
          }  
        }
        return false;
    }
    
}
