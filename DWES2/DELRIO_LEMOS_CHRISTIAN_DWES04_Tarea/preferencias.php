<?php session_start();  //Inicio la sesión
    
    if(!isset($_SESSION['idioma']) && !isset($_SESSION['perfil']) && !isset($_SESSION['zona'])){

        $_SESSION['idioma']="";
        $_SESSION['perfil']="";
        $_SESSION['zona']="";
    }

    if(isset($_POST['establecer'])){
        $_SESSION['idioma'] = $_POST['idioma'];        //Guardar las preferencias en la sesión
        $_SESSION['perfil'] = $_POST['perfil'];
        $_SESSION['zona'] =  $_POST['zona'];

        ob_start(); //Recargar la propia página
        echo "<p class='text-info'>Preferencias de usuario guardadas</p>";  //Mostrar mensaje preferencias establecidas
    }

    if(isset($_POST['mostrar'])){   //El botón mostrar redirigirá a mostrar.php
        header('Location: mostrar.php');
    }

?>

<!doctype html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="estilos.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <title>Tarea Unidad 4</title>
  </head>

  <body>
  <div class="d-flex flex-column mb-3" >
        <h1>Preferencias de Usuario</h1>
        <form class="form-floating" method="post">
            <h6>Idioma <i class="fas fa-language"></i></h6>
            <select class="form-select" name="idioma">
                <option <?php if(isset($_SESSION['idioma']) && $_SESSION['idioma'] == 'Español'){ echo 'selected';}?>>Español</option>
                <option <?php if(isset($_SESSION['idioma']) && $_SESSION['idioma'] == 'Inglés'){ echo 'selected';}?>>Inglés</option>
            </select>
            <br>
            <h6>Perfil Público <i class="fa fa-user"></i></h6>
            <select class="form-select" name="perfil">
                <option <?php if(isset($_SESSION['perfil']) && $_SESSION['perfil'] == 'Sí'){ echo 'selected';}?>>Sí</option>
                <option <?php if(isset($_SESSION['perfil']) && $_SESSION['perfil'] == 'No'){ echo 'selected';}?>>No</option>
            </select>
            <br>
            <h6>Zona Horaria <i class="fa fa-clock"></i></h6>
            <select class="form-select" name="zona">
                <option <?php if(isset($_SESSION['zona']) && $_SESSION['zona'] == 'GMT-2'){ echo 'selected';}?>>GMT-2</option> <!-- Así se mostrará marcada la preferencia seleccionada por defecto-->
                <option <?php if(isset($_SESSION['zona']) && $_SESSION['zona'] == 'GMT-1'){ echo 'selected';}?>>GMT-1</option>
                <option <?php if(isset($_SESSION['zona']) && $_SESSION['zona'] == 'GMT'){ echo 'selected';}?>>GMT</option>
                <option <?php if(isset($_SESSION['zona']) && $_SESSION['zona'] == 'GMT+1'){ echo 'selected';}?>>GMT+1</option>
                <option <?php if(isset($_SESSION['zona']) && $_SESSION['zona'] == 'GMT+2'){ echo 'selected';}?>>GMT+2</option>
            </select>
            <br>
            <input type="submit" class="btn btn-info" name="mostrar" value="Mostrar Preferencias" />
            <input type="submit" class="btn btn-success" name="establecer" value="Establecer Preferencias" />
        </form>
    </div>
  </body>
</html>
