<?php session_start();     //Inicio la sesión


    if(empty($_SESSION['idioma']) && empty($_SESSION['perfil']) && empty($_SESSION['zona']) && isset($_POST['borrar'])){   //Si entro sin establecer preferencias y pulso borrar                      

            echo "<p class='text-danger'>Debes fijar primero las preferencias</p>";

    }elseif(!isset($_SESSION['idioma']) && !isset($_SESSION['perfil']) && !isset($_SESSION['zona']) && isset($_POST['borrar'])){   //Si borré las preferencias y pulso borrar                           

        echo "<p class='text-danger'>Debes fijar primero las preferencias</p>";

    }

    if(isset($_SESSION['idioma']) && isset($_SESSION['perfil']) && isset($_SESSION['zona']) && isset($_POST['borrar'])){  //En caso de darle a borrar habiendo preferencias establecidas
        session_unset(); 
        session_destroy(); 
        echo "<p class='text-info'>Preferencias borradas</p>"; //Mostrar mensaje preferencias borradas
    }
    

    if(isset($_POST['establecer'])){   //Establecer lleva a preferencias.php
        header('Location:preferencias.php');
    }

    //var_dump($_SESSION);
?>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-I/N86EgVNl1aK/9r9cPbx0Z/tCCY5o5q3+LmPhJ/z5LmR0IlbAK+uzQMgO5EZo0jY5FG3qc+Ph1lbCiB5FQ==" crossorigin="anonymous" />
    <link rel="stylesheet" type="text/css" href="estilos.css">
    <title>Tarea Unidad 4</title>
  </head>



  <body>
  <div class="d-flex flex-column mb-3" >
        <h1>Preferencias</h1>
        <form class="form-floating" method="post">
            <h6>Idioma: <?php if(isset($_SESSION['idioma'])){echo $_SESSION['idioma'];}?></h6>  <!--Si tenemos establecido el valor, se muestra, en caso contrario saldrá vacío-->
            <br>
            <h6>Perfil Público: <?php if(isset($_SESSION['perfil'])){echo $_SESSION['perfil'];}?></h6>
            <br>
            <h6>Zona Horaria: <?php if(isset($_SESSION['zona'])){echo $_SESSION['zona'];}?></h6>
            <br>
            <input type="submit" class="btn btn-info" name="establecer" value="Establecer" />
            <input type="submit" class="btn btn-danger" name="borrar" value="Borrar" />
        </form>
    </div>
  </body>
</html>