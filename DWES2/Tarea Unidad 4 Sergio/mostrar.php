<?php
    session_start();

     //Mensaje éxito y de adevertencia de preferencias vacías
    $mensajeExito = false;
    $mensajePreferencias = false;

    //Si le doy al botón de borrar
    if(isset($_POST['borrar'])){

        // Si existe la sesión la destruyo y muestro el mensaje
        if(isset($_SESSION['idioma']) && isset($_SESSION['perfil']) && isset($_SESSION['zona'])){
                
            session_destroy();
            unset($_SESSION);
            $mensajeExito = true;

        //Si no existe sesión muestro el mensaje de que se deben de guardar antes las preferencias
        }else{
            $mensajePreferencias = true;
        }
    }
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous" defer></script>
    <!-- Iconos -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />

    <title>Mostrar</title>
</head>
<body class="bg-secondary">
<div class="row justify-content-center">
        <form action="" method="POST" class="card mt-4 col-6 py-3 bg-success text-white">
            <!-- Mostrar mensajes  -->
            <?php
                if($mensajeExito === true){
                    echo <<<MARCA
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            Preferencias borradas
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    MARCA;
                }
                if($mensajePreferencias === true){
                    echo <<<MARCA
                        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                            Debe fijar primero las preferencias
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    MARCA;
                }
            ?>
            <h4 class="card-header mb-2">
                <span class="material-symbols-outlined me-2">settings_account_box</span>
                Preferencias
            </h4>
            
            <!-- Mostrar los datos guardados en la sesión -->
            <p>Idioma: <?php if(isset($_SESSION['idioma'])){if($_SESSION['idioma'] === 'es'){echo 'español';}else{echo 'inglés';};}else{echo 'No establecido';}; ?></p>
            <p>Perfil público: <?php if(isset($_SESSION['perfil'])){echo $_SESSION['perfil'];}else{echo 'No establecido';}; ?></p>
            <p>Zona horaria: <?php if(isset($_SESSION['zona'])){echo $_SESSION['zona'];}else{echo 'No establecido';}; ?></p>
        
            <!-- Botones para ir a preferencias y borrar datos de sesión -->
            <div class="buttons mt-2">
                <!--redirijo al archivo mostrar.php-->
                <a class="btn btn-primary" href="preferencias.php">Establecer</a>
                <!--boton de envio del formulario-->
                <input class="btn btn-danger" type="submit"  name="borrar" value="Borrar">
            </div>

        </form>
    </div>
</body>
</html>