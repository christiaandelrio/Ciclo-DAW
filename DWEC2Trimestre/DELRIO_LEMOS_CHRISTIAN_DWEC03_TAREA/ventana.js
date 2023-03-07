const nuevaVentana = self.window;
  
nuevaVentana.document.body.innerHTML += "<h3>Ejemplo de Ventana Nueva</h3>";
nuevaVentana.document.body.innerHTML += "URL: "+nuevaVentana.location + "<br>";
nuevaVentana.document.body.innerHTML += "Protocolo: "+nuevaVentana.location.protocol+ "<br>";
nuevaVentana.document.body.innerHTML += "Nombre en Código: "+nuevaVentana.navigator.appCodeName+ "<br>";

if(navigator.javaEnabled()){
    nuevaVentana.document.body.innerHTML += "Java SI disponible en esta ventana <br>";
}else{
    nuevaVentana.document.body.innerHTML += "Java NO disponible en esta ventana <br>";
}

nuevaVentana.document.body.innerHTML += "<iframe src='http://www.google.es' 'width=800' height='600'</iframe>";

