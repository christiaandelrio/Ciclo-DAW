<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="script.js" defer></script>
    <title>DWES-JS-B05-01</title>
    <style>
        .monoespacio { font-family: monospace; }
    </style>
</head>
<body>
    <code>
<?php
    $cadenaReferencia = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789';
    $mensaje          = 'abcsdf1111111111111111111111111111111111111111111111111111111111';
    $codigo  = 'fkdjfklhgosidfowheoihfosfdlkfjlkdfjoiwejf2343sd987238479lkdjflk4';  
    
    for($i = 0; $i < mb_strlen($cadenaReferencia); $i++) {
        echo mb_substr($cadenaReferencia, $i, 1 ) . " : " . mb_substr($mensaje, $i , 1 )  . " ? == ?" . mb_strpos($cadenaReferencia, mb_substr($mensaje, $i, 1 )) . "<br>";
    }
 

?>
    </code>
</body>
</html>