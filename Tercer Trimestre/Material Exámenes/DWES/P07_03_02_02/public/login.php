<?php
require '../vendor/autoload.php';
YsJQueryAutoloader::register();

?>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <title>Formulario jQuery</title>
    <script src="https://code.jquery.com/jquery-3.5.0.min.js"
            integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=" crossorigin="anonymous"></script>

</head>
<body style="background:#00bfa5;">

<div class="container mt-5">
    <div class="d-flex justify-content-center h-100">
        <div class="card" style='width:24rem;'>
            <div class="card-header">
                <h3><i class="fa fa-cog mr-1"></i>Registro</h3>
            </div>
            <div class="card-body">
                <form name='miForm' id="miForm" method='POST' action="javascript:void(null);">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" class="form-control" placeholder="usuario" id='usu' name='usu'>
                        <span id='errUsu' for='usu' class="input-group form-group text-danger"></span>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input type="password" class="form-control" placeholder="contraseña" id='pass1' name='pass1'>
                    </div>

                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input type="password" class="form-control" placeholder="repita la contraseña" id='pass2' name='pass2'>
                    </div>
                    <span id='errPass' for='pass2' class='input-group form-group text-danger'></span>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fa fa-envelope"></i></span>
                        </div>
                        <input type="email" class="form-control" placeholder="e-Mail" name='mail' id='mail'>
                    </div>
                    <span id='errMail' for='mail' class='input-group form-group text-danger'></span>
                    <div class="form-group">
                        <input type="submit" value="Registrar" class="btn float-right btn-info" name='enviar'
                               id="enviar">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<?php
echo YsJQuery::newInstance()
    ->onClick()
    ->in("#enviar")
    ->execute(
        YsJQuery::getJSON(
            "validar.php",
            YsJQuery::toArray()->in('#miForm input'),
            new YsJsFunction('
                    if(msg.errUsu) alert(msg.errUsu);
                    if(msg.errPass) alert(msg.errPass);
                    if(msg.errMail) alert(msg.errMail);', 'msg'
            )
        )
    );
?>
</body>

</html>