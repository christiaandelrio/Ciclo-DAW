<?php


class Palindromo extends Conexion
{
    private $id;
    private $usuario;
    private $frase;
    private $esPalindromo;

    public function __construct()
    {
        parent::__construct();
    }

    public function getId(){
        return $this->id;
    }

    public function getUsuario(){
        return $this->usuario;
    }

    public function getFrase(){
        return $this->frase;
    }

    public function getPalindromo()
    {
        return $this->esPalindromo;
    }

    public function setId($id){
        return $this->id = $id;
    }

    public function setUsuario($usuario){
        return $this->usuario = $usuario;
    }

    public function setFrase($frase){
        return $this->frase = $frase;
    }

    public function setPalindromo($esPalindromo)
    {
        return $this->esPalindromo = $esPalindromo;
    }

    public function insertarPalindromo(){
        $insert = "insert into palindromos(usuario, frase, esPalindromo) values(:u, :f, :e)";
        $stmt = $this->conexion->prepare($insert);
        try{
            $stmt->execute([
                ':u' => $this->usuario,
                ':f' => $this->frase,
                ':e' => $this->esPalindromo
            ]);
        }catch(PDOException $ex){
            die("Error al insertar el producto: ".$ex->getMessage());
        }
    }
}