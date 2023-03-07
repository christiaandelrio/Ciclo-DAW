<?php 

namespace Clases; //hago uso de un namespaces para las clases

use PDO;
use PDOException;

class Jugador extends Conexion  //Indicamos que Jugador hereda de la clase Conexion con extends
{
    private $id;
    private $nombre;
    private $apellidos;
    private $posicion;
    private $dorsal;
    private $barcode;


    public function setId($id){  //Establecer id
        $this->id = $id;
    }

    public function setNombre($nombre){
        $this->nombre = $nombre;
    }

    public function setApellidos($apellidos){
        $this->apellidos = $apellidos; 
    }

    public function setPosicion($posicion){
        $this->posicion = $posicion;
    }

    public function setDorsal($dorsal){
        $this->dorsal = $dorsal;
    }

    public function setBarcode($barcode){
        $this->barcode = $barcode;
    }


    //Mostrar el listado con todos los jugadores
    function mostrarJugadores(){
        $sql = "SELECT * FROM jugadores";
        $stmt = $this->conexion->prepare($sql);

        try{
            $stmt->execute();
        }catch(PDOException $ex){
            die("Error al listar a los jugadores ".$ex->getMessage());
        }
        $this->conexion=null;

        return $stmt->fetchAll(PDO::FETCH_OBJ);
    }   


    function create(){
        $insert = "INSERT INTO jugadores(nombre,apellidos,dorsal,posicion,barcode)VALUES(:n,:a,:d,:p,:b)";
        $stmt=($this->conexion->prepare($insert));

        try{
            $stmt->execute([
                ':n'=>$this->nombre,
                ':a' => $this->apellidos,
                ':d' => $this->dorsal,
                ':p' => $this->posicion,
                ':b' => $this->barcode
            ]);
        }catch(PDOException $ex){
            die("Error al insertar jugadores:" .$ex->getMessage());
        }
    }

    function existenJugadores(){
        $select = "SELECT * FROM jugadores";
        $stmt = $this->conexion->prepare($select);

        try{
            $stmt->execute();
        }catch(PDOException $ex){
            die("Error al comprobar si existen jugadores: ".$ex->getMessage());
        }

        if($stmt->rowCount()==0){  //Si no encuentra registros en la tabla jugadores, devuelve false
            return false;
        }else{
            return true;
        }
    }

    function existeBarcode($barcode)
    {
        $select = "SELECT * FROM jugadores WHERE barcode=:b";
        $stmt = $this->conexion->prepare($select);

        try {
            $stmt->execute([':b' => $barcode]);
        } catch (PDOException $ex) {
            die("Error al comprobar barcode: " . $ex->getMessage());
        }

        if ($stmt->rowCount() == 0) {
            return false; //Si no existe un barcode igual en la bd devuelve false
        } else {
            return true; //Si existe devuelve true
        }
    }

   
    function existeDorsal($dorsal){

            $select = "SELECT * FROM jugadores WHERE dorsal=:d";
            
            $stmt = $this->conexion->prepare($select);
            try {
                $stmt->execute([':d' => $dorsal]);
            } catch (PDOException $ex) {
                die("Error al comprobar el dorsal: " . $ex->getMessage());
            }
    
            if ($stmt->rowCount() == 0) {
                return false; //No existe un dorsal igual
            } else {
                return true; //Si existe
            }
        }


        
}