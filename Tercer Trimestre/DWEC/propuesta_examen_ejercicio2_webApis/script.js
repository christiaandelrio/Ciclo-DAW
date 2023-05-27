const inputNumero = document.getElementById("numero");
const b1 = document.getElementById("b1");
const b2 = document.getElementById("b2");
const b3 = document.getElementById("b3");

const entradaJson = document.getElementById("entradaJson");

hayCookieoNo();



//R1)El boton b1 "Enviar" que cada vez que se hace click en él permita indicar
//al usuario que quiere enviar la entrada anterior.

//Añado Evento click al boton:

b1.addEventListener("click",pulsarEnviar);
b2.addEventListener("click",generarColores);
b3.addEventListener('click',descargarJSON)

//R2)Con esta funcion alertamos al usuario de que quiere meter bien o mal el numero
function pulsarEnviar(event){

    console.log("Boton enviar pulsado");

    if(inputNumero.value>1 || inputNumero.value<50){

       alert("¿Está seguro de querer enviar la entrada anterior?")
       guardarCookie();
       dibujaCuadrados();
        console.log("El usuario tiene que decicir que hace") 
    }else{
        //R3 - Si la entrada no es valida se sacará un mensaje:
        alert("Entrada no valida: debe estar comprendida entre 1 y 50")
    }


}
//R7) Guardamos la cookie procedente del input del numero en LocalStorage y el valor elegido por el usuario
//y cuando vuelva a abrir la aplicación recibirá ese numero.
function guardarCookie(){
    localStorage.setItem("numero", inputNumero.value)
}

function hayCookieoNo(){

// Obtener el número almacenado en localStorage
  var numeroGuardado = localStorage.getItem("numero");
  console.log("Numero guardado: "+numeroGuardado);
  // Verificar si hay un número guardado
  if (numeroGuardado !== null) {
    // El número está almacenado en localStorage, puedes usarlo
    console.log("El número guardado es: " + numeroGuardado);
  } else {
    // No se encontró ningún número guardado
    console.log("No hay número guardado.");
  }
   
}


//R4) Un boton que generará los colores mediante una funcion asociado al evento click
function generarColores(event){
    event.preventDefault();
    document.body.addEventListener('click',()=>{
      console.log(e)
    })
  
}

b2.addEventListener('click',()=>{
    
    let r = Math.floor(Math.random()*256);
    let g = Math.floor(Math.random()*256);
    let b = Math.floor(Math.random()*256);
    document.body.style.backgroundColor= `rgb(${r} ${g} ${b})`
})





/* R5) Al hacer click en el boton B! se validará la entrada (numero N) y si cumple con los requisitos indicados, 
se dibujaran en la página N Cuadrados de color, posicion y tamaño aleatorio */


//Creamos una funcion que se llama dibujar cuadrados , donde se recogerá el valor de b1
//y ese valor se validará y si es correcto se generan los cuadrados correctos.

function dibujaCuadrados(){

  
        let cuadrados = document.querySelectorAll('.cuadrado');
        console.log(cuadrados);
        cuadrados.forEach(item=>{
          item.remove();
        })
  
        for (i=0;i<inputNumero.value;i++){
            let pix = Math.floor(Math.random()*51)+10
            const cuadrado = document.createElement("div");
            cuadrado.style.height=pix+"px";
            cuadrado.style.width=pix+"px";
            cuadrado.style.position="relative";
            cuadrado.classList.add("cuadrado");
            let top = Math.floor(Math.random()*501)
            let left = Math.floor(Math.random()*601)
            cuadrado.style.position="relative";
            cuadrado.style.top=top+"px";
            cuadrado.style.left=left+"px";
            cuadrado.innerText=i;
            cuadrado.style.textAlign="center";
            let r = Math.floor(Math.random()*256);
            let g = Math.floor(Math.random()*256);
            let b = Math.floor(Math.random()*256);
            cuadrado.style.backgroundColor= `rgb(${r} ${g} ${b})`
            document.body.appendChild(cuadrado);
        }
        
 

 }


/*R8) El formulario de entrada también dispondrá de una caja de texto y su botón correspondiente (B3) para 
introducir una URL de la que se puede descargar un JSON. Al hacer clic en B3 se intentará descargar el JSON y 
mostrarlo en un área de texto (AT1). Si hay un error, mostrará la información en el área de texto, en color rojo.*/



function descargarJSON() {
 ;
    const url = document.getElementById('entradaJson').value;
    const areaTexto = document.getElementById("areaTexto");
    areaTexto.innerHTML = ""

    if(url=='' || url == undefined){
      var p = document.createElement("p")
      p.innerHTML=`Ha habido un error`;
      p.style.color = "red";
      areaTexto.append(p);
      return;
    } 
    fetch(url)
      .then(response => response.json())
      .then(data => {
        console.log("respuesta");
        console.log(data);
        for(element in data){
          console.log(data[element]);
          var p = document.createElement("p")
          p.innerHTML=`UserID: ${data[element].userId} - Id: ${data[element].id} - Titulo: ${data[element].title}`;
          areaTexto.append(p);
        }
        //areaTexto.innerHTML = JSON.stringify(data);
       // areaTexto.style.color = "black";
      })
      .catch(error => {
        console.log("Error");
        var p = document.createElement("p")
        p.innerHTML=`Ha habido un error`;
        p.style.color = "red";
        areaTexto.innerHTML = p;

      });
  }
