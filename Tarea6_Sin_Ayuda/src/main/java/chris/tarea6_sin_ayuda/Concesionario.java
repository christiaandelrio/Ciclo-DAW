/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chris.tarea6_sin_ayuda;

/**
 *
 * @author chris
 */
public class Concesionario {
    
    private Vehiculo vehiculos []; //Se suele declarar atributos como private 
    private int numVehiculos;

    public Concesionario() {
        this.vehiculos = new Vehiculo[80];
        this.numVehiculos = 0;
    }
       
    
    public String buscaVehiculo(String matricula){
        for(int i=0;i<numVehiculos;i++){
            
            if(vehiculos[i].getMatricula().equals(matricula)){
                return vehiculos[i].toString();
            }
            
        }
        
        return null;
    }
    
    public int insertarVehiculo(Vehiculo v){
        
        if(this.numVehiculos == vehiculos.length){
            return -1;
        }
        
        if(this.buscaVehiculo(v.getMatricula()) != null){
            return -2;
        }else{
            this.vehiculos[numVehiculos] = v;
            numVehiculos++;
            return 0;
        }
    }
    
    public void listaVehiculos(){
        if(this.numVehiculos == 0){
            System.out.println("El concesionario está vacío");
        }else{
            for(int i=0; i<this.numVehiculos; i++){
                System.out.println("Vehículo con matrícula: "+this.vehiculos[i].getMatricula());
            }
        }
    }
    
    public boolean actualizaKms(String matricula, double numKms){
        for(int i = 0; i < numVehiculos; i++){
            if(vehiculos[i].getMatricula().equals(matricula)){
                vehiculos[i].setNumKilometros(numKms);
                
                return true;
            }
        }
        
        return false;
    }
}
