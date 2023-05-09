
const iniciar = () =>{
    
    //1 - CREAMOS LA TABLA PARA DIBUJAR

    //Para ello, obtenemos la refrencia del contenedor donde vamos a colocar la tabla
    const contenedorTablero=document.getElementById("zonadibujo");

    //Creamos los elementos de la tabla
    let tabladibujo=document.createElement("table");
    let tablabody=document.createElement("tbody");
    let aviso=document.createElement("caption");
    let textocaption=document.createTextNode("Haga CLICK en cualquier celda para activar/desactivar el Pincel");

    //Creamos las celdas mediante un bucle for
    for(let i=0; i<30; i++){
        //Creamos las filas
        let fila=document.createElement("tr");
        for(let j=0; j<30; j++){
            //Por cada fila creamos una celda  y establecemos el atributo style a background-color white y el alto y el ancho en 10 px
            let celda=document.createElement("td");
            celda.setAttribute("style", "background-color: ;"); //o celda.style.backgroundColor="white"
            celda.setAttribute("width", "10px");
            celda.setAttribute("height", "10px");
            //Una vez creadas las celdas las añadimos a la fila
            fila.appendChild(celda);
        }
        //Una vez creada la fila la añadimos a tablabody
        tablabody.appendChild(fila);
    }
    //Una vez creado el cuerpo de la tabla lo añadimos a la tabla y lo metemos en el correspondiente div
    aviso.appendChild(textocaption);
    tabladibujo.appendChild(aviso);
    tabladibujo.appendChild(tablabody);
    tabladibujo.setAttribute("border", "2");
    tabladibujo.setAttribute("id", "tabla");
    contenedorTablero.appendChild(tabladibujo);

    //2 - INICIALIZAMOS LAS VARIABLES

    //Accedemos a los elementos de la paleta y de la tabla de dibujar
    const paleta=document.getElementById("paleta");
    const elementosPaleta=paleta.getElementsByTagName('td');
    const celdas=tabladibujo.getElementsByTagName("td");

    //Creamos una variable que alamacena el color seleccionado de la paleta, que por defecto es el del primer elemento de elementosPaleta
    let colorSeleccionado=getComputedStyle(elementosPaleta[0]).getPropertyValue("background-color");

    //3 - CREAMOS LAS FUNCIONES FLECHA

    //Función para cambiar el texto del pincel
    const cambiarTextoPincel=(estado)=>{
        if(estado){
            document.getElementById("pincel").textContent="PINCEL ACTIVADO";
        } else {
            document.getElementById("pincel").textContent="PINCEL DESACTIVADO";
        }
    }

    //Inicialmente el pincel está desactivado hasta que se selecciona un color
    let pincelActivo=false;
    //Pintamos el estado del pincel
    cambiarTextoPincel(pincelActivo);

    //Función que cambia el color de la paleta accediendo al valor de la propiedad background-color y añadiendo la clase "seleccionado" al elemento seleccionado
    const seleccionarColor = (evento) =>{
        colorSeleccionado=window.getComputedStyle(evento.target).getPropertyValue('background-color');
        //Accedemos a los elementos de la paleta
        for (let n = 0; n < elementosPaleta.length; n++) {
            elementosPaleta[n].classList.remove('seleccionado'); //Eliminamos la clase "seleccionado" de todos los elementos de elementosPaleta
        }
        evento.target.classList.add("seleccionado"); //añadimos la clase "seleccionado" al elemento seleccionado
    }
        
    //Función de pintar la tabla de dibujar
    const pintar = (evento) =>{
        //Modificamos el estado del pincel y lo imprimimos
        pincelActivo=true;
        cambiarTextoPincel(pincelActivo);
        //Pintamos la celda
        const elemento=evento.target;//la propiedad target del objeto Event es el elemento en ek que se originó el evento
        elemento.style.backgroundColor=colorSeleccionado;
        //Si clicamos en la celda actual, se desactiva el pincel
        elemento.addEventListener("click", pararPintar, true);
    }

    //Función de pintar la tabla de dibujar deslizando el ratón
    const pintarDeslizar = (evento) =>{
        //Si el pincel está activado y deslizamos el ratón, pintamos las celdas
        if(pincelActivo){
        const elemento=evento.target;//la propiedad target del objeto Event es el elemento en ek que se originó el evento
        elemento.style.backgroundColor=colorSeleccionado;
        //Si clicamos en la celda actual sobre la que pasamos el ratón, para de pintar y cambia el estado del pincel
        elemento.addEventListener("click", pararPintar, true);
        }
    }

    //Función para parar de pintar si hacemos click en el elemento actual
    const pararPintar = (evento) => {
        pincelActivo=false;
        cambiarTextoPincel(pincelActivo);
    }

    //4 - Creamos los addEventListeners (mediante bucle for)

    //Para cada elemento de la paleta 
    for(let i=0; i<elementosPaleta.length; i++){
        elementosPaleta[i].addEventListener('click', seleccionarColor, true);
    }
    //Para cada celda de la tabla  
    for(let j=0; j<celdas.length;j++){
        celdas[j].addEventListener("click",pintar, true); 
        celdas[j].addEventListener("mouseover", pintarDeslizar, true);  
    }
}  

//Cada vez que se cargue la ventana, se llamará a la función iniciar
window.addEventListener('load', iniciar, false);




