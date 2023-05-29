<?php
function validarNombre($usu){
    if(strlen($usu) < 4) return false;
    return true;
}

function validarEmail($mail){
    return preg_match("/^[a-z0-9]+([_\\.-][a-z0-9]+)*@([a-z0-9]+([\.-][a-z0-9]+)*)+\\.[a-z]{2,}$/i", $mail);
}

function validarPasswords($pass1, $pass2) {
    return $pass1 == $pass2 && strlen($pass1) > 5;
}

function validarFormulario($valores) {
    $respuesta = array();
    if (!validarNombre($valores['usu']))
        $respuesta['errUsu'] = "El nombre debe tener más de 3 caracteres.";

    if (!validarPasswords($valores['pass1'], $valores['pass2']))
        $respuesta['errPass'] = "La contraseña debe ser mayor de 5 caracteres o no coinciden.";

    if (!validarEmail($valores['mail']))
        $respuesta['errMail'] = "La dirección de email no es válida.";

    return $respuesta;
}

echo json_encode(validarFormulario($_REQUEST));
