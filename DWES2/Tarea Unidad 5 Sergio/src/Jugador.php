<?php

namespace Clases;

use PDO;
use PDOException;

//Creamos la clase Jugador que extiende de la clase Conexion
class Jugador extends Conexion{
    //Atributos de la clase Jugador
    private $id;
    private $nombre;
    private $apellidos;
    private $posicion;
    private $dorsal;
    private $barcode;

    //Constructor de la clase Jugador que hereda el constructor de la clase Conexion (Establece la conexión a DB)
    function __construct(){
        parent::__construct();
    }

    //Setters de la clase Jugador
    public function setId($id){
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


    //Método para crear jugador en la base de datos::
    function create(){
        $insert = "INSERT INTO jugadores(nombre,apellidos,dorsal,posicion,barcode)
        VALUES (:n,:a,:d,:p,:b)";
        $stmt = $this->conexion->prepare($insert);
        try {
            $stmt->execute([
                ':n' => $this->nombre,
                ':a' => $this->apellidos,
                ':d' => $this->dorsal,
                ':p' => $this->posicion,
                ':b' => $this->barcode
            ]);
        } catch (PDOException $ex) {
            die("Ha ocurrido un error al crear un jugador: " . $ex->getMessage());
        }
    }

    //Método para obtener el listado todos los jugadores ordenados por posición y apellidos:
    function listadoJugadores(){
        $consulta = "SELECT * FROM jugadores ORDER BY posicion,apellidos";
        $stmt = $this->conexion->prepare($consulta);
        try {
            $stmt->execute();
        } catch (PDOException $ex) {
            die("Error al tratar de obtener los jugadores: " . $ex->getMessage());
        }
        $this->conexion = null;
        return $stmt->fetchAll(PDO::FETCH_OBJ);
    }

    //Método para comprobar si la tabla jugadores de la base de datos está vacía
    function tieneDatos(){
        $consulta = "SELECT * FROM jugadores";
        $stmt = $this->conexion->prepare($consulta);
        try {
            $stmt->execute();
        } catch (PDOException $ex) {
            die("Error al verificar si ya existen datos: " . $ex->getMessage());
        }

        if ($stmt->rowCount() == 0) return false;

        return true;
    }
    //Método para comprobar si ya existe el barcode en la base de datos:
    function barcodeExiste($barc){
        $consulta = "SELECT * FROM jugadores WHERE barcode=:b";
        $stmt = $this->conexion->prepare($consulta);
        try {
            $stmt->execute([':b' => $barc]);
        } catch (PDOException $ex) {
            die("Error al comprobar si ya existe el barcode: " . $ex->getMessage());
        }

        if ($stmt->rowCount() == 0) {
            return false; //El barcode no existe
        } else {
            return true; //El barcode existe
        }
    }

    //Método para comprobar si el dorsal ya existe en la base de datos:
    function dorsalExiste($dors){
        $consulta = "SELECT * FROM jugadores WHERE dorsal=:d";
        $stmt = $this->conexion->prepare($consulta);
        try {
            $stmt->execute([':d' => $dors]);
        } catch (PDOException $ex) {
            die("Error al comprobar si ya existe el dorsal: " . $ex->getMessage());
        }

        if ($stmt->rowCount() == 0) {
            return false; //El dorsal no existe
        } else {
            return true; //El dorsal existe
        }
    }

    //Método para borrar la tabla jugadores de la base de datos:
    function borrarTodo(){
        $borrado = "delete from jugadores";
        $stmt = $this->conexion->prepare($borrado);
        try {
            $stmt->execute();
        } catch (PDOException $ex) {
            die("Error al borrar todos los jugadores: " . $ex->getMessage());
        }
    }
}
