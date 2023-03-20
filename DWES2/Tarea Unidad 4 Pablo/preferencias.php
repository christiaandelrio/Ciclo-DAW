<?php
    session_start();

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
                       <div class="card-head"> <h1>Preferencias Usuario</h1></div>
                        <div class="card-body">
                        <form  action="mostrar.php" method="post">
                            <div class="form-group">
                            <label><i class="fas fa-language"></i>  Idioma</label>
                            <select class="form-contorl" name="idioma">
                                <option value="Ingles">Ingles </option>
                                <option value="Español">Español</option>
                            </select>
                            </div>
                            <div class="form-group">
                                <label><i class="fas fa-users"></i> Perfil Público</label>
                                <select class="form-contorl" name="perfilpublico">
                                    <option value="1">Si </option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label><i class="fas fa-globe"></i> Zona Horaria</label>
                                <select class="form-contorl" name="zonahoraria">
                                    <option value="GMT-2">GMT-2 </option>
                                    <option value="GMT-1">GMT-1</option>
                                    <option value="GMT">GMT</option>
                                    <option value="GMT+2">GMT+2 </option>
                                    <option value="GMT+1">GMT+1</option>
                                </select>
                            </div>
                
                            <button type="submit" name="submitPreferencias"  class="btn btn-primary">Establecer preferencias</a></button>
                            <br>
                            <a href="mostrar.php" class="btn btn-primary">Mostrar preferencias</a>
                         </form>
            </div>    
            </div>
        </div>
    </div>




   

</body>
</html>