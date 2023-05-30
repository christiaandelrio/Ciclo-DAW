window.onload = function iniciarTablero() {

        //Primero definimos el área de dibujo
        const zonaDibujo = document.getElementById('zonadibujo');
        //Obtenemos la paleta de colores
        const paleta = document.getElementById('paleta');
      
        //Crear una tabla de 30x30
        const tabla = document.createElement('table');
        tabla.classList.add('tablerodibujo'); //Le añadimos la clase para que se le apliquen los estilos 
        tabla.setAttribute('border', '1'); //Borde 1
        for (let i = 0; i < 30; i++) {
          const tr = document.createElement('tr');
          for (let j = 0; j < 30; j++) {
            const td = document.createElement('td');
            tr.appendChild(td);
          }
          tabla.appendChild(tr);
        }
        zonaDibujo.appendChild(tabla);
      
        //Establecer el color por defecto como el primer color de la paleta
        let colorSeleccionado = window
          .getComputedStyle(paleta.getElementsByTagName('td')[0]) //Accedemos a los estilos del primer td de la paleta
          .getPropertyValue('background-color'); //Y obtenemos el valor de su background-color, guardándolo como color seleccionado
      
        //Seleccionar el color de la paleta
        function seleccionarColor(evento) {
          const estilos = window.getComputedStyle(evento.target); //Obtenemos los estilos del objeto que ha provocado el evento
          colorSeleccionado = estilos.getPropertyValue('background-color'); //Y accedemos al valor 
        }
      
        //Agregar la clase "seleccionado" al color seleccionado
        function agregarClaseSeleccionado(evento) {
          const elementosPaleta = paleta.getElementsByTagName('td');
          for (let i = 0; i < 6; i++) {
            elementosPaleta[i].classList.remove('seleccionado');
          }
          evento.target.classList.add('seleccionado');
        }
      
        //Agregar las funciones a los elementos de la paleta cuando se hace clic en ellos
        const elementosPaleta = paleta.getElementsByTagName('td');
        for (let i = 0; i < 6; i++) {
          elementosPaleta[i].addEventListener('click', seleccionarColor);
          elementosPaleta[i].addEventListener('click', agregarClaseSeleccionado);
        }
      
        //Pintar la celda seleccionada al hacer clic y pintar las celdas al pasar el ratón mientras se mantiene pulsado el botón del ratón
        function pintarCeldas(evento) {
          const celda = evento.target;
          if (evento.type === 'click') {
            celda.style.backgroundColor = colorSeleccionado;
            pintar = !pintar; //Al hacer click cambiamos el valor de pintar a true 
            cambiarTextoPintar(); //Y a continuación cambiamos el texto del pincel a activado 
          } else if (evento.type === 'mouseover' && pintar) { 
            celda.style.backgroundColor = colorSeleccionado;
          }
        }
          
        function cambiarTextoPintar() {
          const estadoPincel = document.getElementById('pincel');
          estadoPincel.textContent = pintar ? 'PINCEL ACTIVADO' : 'PINCEL DESACTIVADO'; //Si pintar es true activado, por defecto está desactivado
        }
      
        //Agregar las funciones a las celdas de la tabla
        const celdas = tabla.getElementsByTagName('td');
        let pintar = false;
        for (let i = 0; i < celdas.length; i++) {
          celdas[i].addEventListener('click', pintarCeldas); //Al hacer click activamos la función pintar celdas
          celdas[i].addEventListener('mouseover', pintarCeldas); //Con el evento mouseover hacemos referencia a pasar el ratón por encima sin arrastrar
        }
}