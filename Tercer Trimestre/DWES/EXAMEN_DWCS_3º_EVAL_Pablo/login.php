<!-- Descarga de FPADISTANCIA la base de datos "pregunta2.zip" y cárgala en tu entorno de desarrollo del examen.
Desarrolla una página "login.php" que permita autenticar a un usuario contra la tabla "usuarios" de la base de datos
"pregunta2".
• si el usuario se autentica correctamente, será redirigido a la página pagina1.php, pasándole a esta (pagina1.php)
los datos de sesión con el nombre de usuario validado.
• modificar la pagina1.php para que muestre el nombre del usuario "logeado"
El nombre de usuario será una cadena sin espacios, de 10 caracteres como máximo, y los caracteres serán letras
minúsculas o mayúsculas del alfabeto castellano (debe validarse la entrada). -->

<?php

session_start();

class Usuario
{
    public $username;
    public $password;

    public function __construct($username, $password)
    {

        $this->username = $username;
        $this->password = $password;
    }
}

if ($_SERVER['REQUEST_METHOD'] == "POST") {
    //VAMOS A COMPROBAR SI ES VALIDO EL USUARIO
    if (isset($_POST["username"]) && isset($_POST["password"])) {

        $host = "localhost";
        $username = "root";
        $password = "";
        $dbname = "pregunta2";

        $connection = new mysqli($host, $username, $password, $dbname);

        if ($connection->connect_error) {
            //Die hace un eco pero no permite hacer nada al usuario.
            die("Ha habido un error al conectarse a la base de datos");
        }
        $username=$_POST["username"];
        $password=$_POST["password"];
        $resultado = $connection->query("SELECT * FROM usuarios WHERE usuario = '$username' and pass='$password'");

        if ($resultado === false) {
            die("Ha habido un error al obtener datos de la base de datos");
        }

        
        if ($resultado->num_rows > 0) {
            
            echo("Has hecho login correctamente");
            $_SESSION["username"] = $username;
            header("Location: pagina1.php");
            

        }else {
            echo("El usuario y la contraseña son incorrectos");
        }
    }
}


?>


<body>

    <form method="POST" action="login.php">
        <label for="username">usuario a introducir</label>
        <input id="username" name="username" />
        <label for="password">Password a introducir</label>
        <input id="password" name="password" />
        <button type="submit" id="enviar">Enviar</button>
    </form>

</body>