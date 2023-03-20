<?php 
 session_start();
  $mensaje = "";  
 if(isset($_POST['borrarPreferencias'])){

    if(isset($_SESSION['idioma']) && isset($_SESSION['perfilpublico']) && $_SESSION['zonahoraria']){
        $_SESSION = array();
        $mensaje = "Prefrencias borradas"; 
    }else{
        $mensaje = "Debes fijar primero las preferencias"; 
    }

 }else{
    if( isset($_POST['submitPreferencias'])){
        $_SESSION['idioma'] = $_POST['idioma'];
        $_SESSION['perfilpublico'] = $_POST['perfilpublico'] == 0 ? 'No' : 'Si';
        $_SESSION['zonahoraria'] = $_POST['zonahoraria'];
    }
  
 }

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.6.0/css/all.min.css" integrity="sha512-ykRBEJhyZ+B/BIJcBuOyUoIxh0OfdICfHPnPfBy7eIiyJv536ojTCsgX8aqrLQ9VJZHGz4tvYyzOM0lkgmQZGw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>


    <div class="container" style="margin-top:100px"> 
        <div class="row"> 
            <div class="col-md-5" style="margin:auto"> 
                    <div class="card" style="width: 18rem;">
                    <div class="card-head"> <h1>Mostrar Preferencias</h1></div>
                <div class="card-body">
                    <p><i class="fas fa-language"></i> Idioma: <?php echo isset($_SESSION['idioma']) ? $_SESSION['idioma'] : ''; ?>  </p>
                    <p><i class="fas fa-users"></i> Perfil publico:  <?php echo isset($_SESSION['perfilpublico']) ? $_SESSION['perfilpublico'] : ''; ?></p>
                    <p><i class="fas fa-globe"></i> Zona horaria:   <?php echo isset($_SESSION['zonahoraria']) ? $_SESSION['zonahoraria'] : ''; ?></p>
                
                </div>
                <div class="card-footer">
                    <form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post"> 
                        <button type="submit" name="borrarPreferencias"  class="btn btn-primary">Borrar preferencias</a></button>
                        <br>
                        <a href="preferencias.php" class="btn btn-primary">Establecer</a>
                    </form>
                </div>
                <div>
                     <p style="color:red">  <?php echo $mensaje; ?> </p>
                </div>
            </div>    
            </div>
        </div>
    </div>

     

</body>
</html>