document.addEventListener("DOMContentLoaded", function (event) {
    const estadoPincel = document.getElementById('pincel');
    const zonaDibujo = document.getElementById('zonadibujo');
    let colorSeleccionado = "#FF0";
    let colorear = false;


    // CREO TABLA
    const tabla = document.createElement('table');

    // AÑADO ESTILOS
    tabla.classList.add('tablerodibujo')
    tabla.setAttribute("style", "border: 1");

    // AÑADO EVENTO CLICK
    tabla.addEventListener("click", activarDesactivarPincel);

    // GENERO ROWS
    for (let i = 0; i <= 29; i++) {
        let row = document.createElement("tr");
        row.style.color = "white";

        // GENERO TDs
        for (let x = 0; x <= 29; x++) {
            let td = document.createElement('td');
            td.style.color = "white";
            td.style.borderStyle = "inset";

            // AÑADO EVENTO MOUSE OVER A CADA CELDA
            td.addEventListener('mouseover', colorearCelda);

            // AÑADO LA CELDA x A LA ROW i
            row.appendChild(td);
        }

        // Añado la row i a la tabla
        tabla.appendChild(row);

    }

    // TODO añadir caption <p>
    let p = document.createElement("p");
    p.style.margin = '0px';
    p.innerText = "Haga CLICK en cualquier celda para activar/deesactrivar el Pincel"
    zonaDibujo.appendChild(p);

    // En este punto tento la tabla, rows y celdas generadas. Las añado al contenedor
    zonaDibujo.appendChild(tabla);

    // INDICO EN PANTALLA QUE LE PINCEL ESTÁ DESACTIGVADO
    estadoPincel.textContent = colorear ? 'PINCEL ACTIVADO' : 'PINCEL DESACTIVADO';


    // Se lanza al hacer clic en una celda
    function seleccionarColor(event) {
        colorSeleccionado = window.getComputedStyle(event.target, "").backgroundColor;
    }

    // Se lanza al hacer clic en una celda
    function agregarClaseSeleccionado(event) {
        const cuadradosPaleta = document.querySelectorAll("td.color1,td.color2,td.color3,td.color4,td.color5,td.color6")
        for (let i = 0; i < 6; i++) {
            cuadradosPaleta[i].classList.remove('seleccionado');
        }

//Agregamos la clase "seleccionado" al elemento donde se hace click
        event.target.classList.add('seleccionado');
    }

    // Se añaden los eventos click a los botones del color picker
    const elementosPaleta = document.querySelectorAll("td.color1,td.color2,td.color3,td.color4,td.color5,td.color6");
    for (let i = 0; i < 6; i++) {
        elementosPaleta[i].addEventListener('click', seleccionarColor);
        elementosPaleta[i].addEventListener('click', agregarClaseSeleccionado);
    }

    // Se ejecuta al pasar el ratón por encima dae las celdas de la zonadibujo
    function colorearCelda(event) {
        //Pinta el color de la celda en la que se ha hecho click
        if (colorear) {
            const element = event.target
            element.style.backgroundColor = colorSeleccionado; // TODO
        }
    }

    // Se ejecuta al hacer clic en la tabla de la zona de dibujo
    function activarDesactivarPincel() {
        colorear = !colorear;
        estadoPincel.textContent = colorear ? 'PINCEL ACTIVADO' : 'PINCEL DESACTIVADO';
    }


});
