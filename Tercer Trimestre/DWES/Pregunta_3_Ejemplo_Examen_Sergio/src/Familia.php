<?php
/*Se define una clase llamada "Familia". Esta clase se encuentra en un namespace llamado "Clases" y requiere el archivo "autoload.php" ubicado en la carpeta "vendor" utilizado para cargar las dependencias de un proyecto.

La clase "Familia" extiende otra clase llamada "Conexion", encaergada de interactuar con la base de datos. La clase tiene dos propiedades privadas llamadas "$cod" y "$nombre" que son accesibles únicamente desde dentro de la clase. La clase tiene métodos getter y setter para ambas propiedades. Estos métodos permiten obtener y establecer el valor de las propiedades desde fuera de la clase.
*/

namespace Clases;

require '../vendor/autoload.php';

use PDO;

class Familia extends Conexion
{
    private $cod;
    private $nombre;

    /*El constructor de la clase "Familia" llama al constructor de la clase "Conexion", que establece una conexión con la base de datos.*/
    /**
     * Familia constructor.
     */
    public function __construct()
    {
        parent::__construct();
    }

    /**
     * @return mixed
     */
    public function getCod()
    {
        return $this->cod;
    }

    /**
     * @param mixed $cod
     */
    public function setCod($cod)
    {
        $this->cod = $cod;
    }

    /**
     * @return mixed
     */
    public function getNombre()
    {
        return $this->nombre;
    }

    /**
     * @param mixed $nombre
     */
    public function setNombre($nombre)
    {
        $this->nombre = $nombre;
    }

    /*"getFamilias", que devuelve un array de códigos de familias. Este método ejecuta una consulta SQL en la base de datos utilizando la conexión establecida por la clase "Conexion". El resultado de la consulta se almacena en un array llamado "$familias" que se devuelve al final del método.*/
    /**
     * @param
     * @return array
     */
    public function getFamilias()
    {
        $consulta = "select cod from familias order by cod";
        $stmt = self::$conexion->prepare($consulta);
        try {
            $stmt->execute();
        } catch (\PDOException $ex) {
            die("Error al devolver las familias: " . $ex->getMessage());
        }
        while ($fila = $stmt->fetch(PDO::FETCH_OBJ)) {
            $familias[] = $fila->cod;
        }
        return $familias;
    }
}
