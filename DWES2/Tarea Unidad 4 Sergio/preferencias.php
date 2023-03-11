<?php
    // Inicio la sesión
    session_start();

    //Mensaje éxito
    $mensajeExito = false;
    //Cuando le doy al botón guardo los datos en la sesión:
    if(isset($_POST['submitButton'])){
        $_SESSION['idioma'] = $_POST['idioma'];
        $_SESSION['perfil'] = $_POST['perfil'];
        $_SESSION['zona'] = $_POST['zona'];

        //Mensaje éxito
        $mensajeExito = true;
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
    
    <title>Sesiones</title>
</head>
<body class="bg-dark-subtle">
    <div class="row justify-content-center">
        <form action="" method="POST" class="card mt-4 col-6 py-3">
            <!-- Mostrar el mensaje de éxito (si está definido) -->
            <?php
                if($mensajeExito === true){
                    echo <<<MARCA
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            Preferencias de usuario guardadas
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    MARCA;
                
                }
            ?>
            <h4 class="card-header mb-2">
                Preferencias Usuario
            </h4>
            <h6>Idioma</p>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">
                    <span class="material-symbols-outlined">translate</span>
                </span>
                <select name="idioma" class="form-select" >
                    <!-- Si existe una sesión de idioma y el valor de idioma es 'es' el option de español lo marcamos como selected (Igual para el resto de opciones)-->
                    <option value="es" <?php if(isset($_SESSION['idioma']) && $_SESSION['idioma'] === 'es'){echo 'selected';}else{ echo ''; }; ?> >Español</option>
                    <option value="en" <?php if(isset($_SESSION['idioma']) && $_SESSION['idioma'] === 'en'){echo 'selected';}else{ echo ''; }; ?> >Inglés</option>
                </select>
            </div>

            <h6>Perfil Público</p>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon2">
                    <span class="material-symbols-outlined">groups</span>
                </span>
                <select name="perfil" class="form-select" >
                    <option value="si" <?php if(isset($_SESSION['perfil']) && $_SESSION['perfil'] === 'si'){echo 'selected';}else{ echo ''; }; ?>>Si</option>
                    <option value="no" <?php if(isset($_SESSION['perfil']) && $_SESSION['perfil'] === 'no'){echo 'selected';}else{ echo ''; }; ?>>No</option>
                </select>
            </div>

            <h6>Zona Horaria</p>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon3">
                    <span class="material-symbols-outlined">schedule</span>
                </span>
                <select name="zona" class="form-select" >
                    <option value="GMT" <?php if(isset($_SESSION['zona']) && $_SESSION['zona'] === 'GMT'){echo 'selected';}else{ echo ''; }; ?>>GMT</option>
                    <option value="GMT-1" <?php if(isset($_SESSION['zona']) && $_SESSION['zona'] === 'GMT-1'){echo 'selected';}else{ echo ''; }; ?>>GMT-1</option>
                    <option value="GMT-2" <?php if(isset($_SESSION['zona']) && $_SESSION['zona'] === 'GMT-2'){echo 'selected';}else{ echo ''; }; ?>>GMT-2</option>
                    <option value="GMT+1" <?php if(isset($_SESSION['zona']) && $_SESSION['zona'] === 'GMT+1'){echo 'selected';}else{ echo ''; }; ?>>GMT+1</option>
                    <option value="GMT+2" <?php if(isset($_SESSION['zona']) && $_SESSION['zona'] === 'GMT+2'){echo 'selected';}else{ echo ''; }; ?>>GMT+2</option>
                </select>
            </div>

            <div class="buttons text-center mt-4">
                <!--Botón para redirigir a la página de mostrar.php-->
                <a class="btn btn-primary" href="mostrar.php">Mostrar preferencias</a>
                <!--Botón de envio del formulario-->
                <input class="btn btn-success" type="submit"  name="submitButton" value="Establecer preferencias">
            </div>

        </form>
    </div>
</body>
</html>