const botonEnviar = document.getElementById("botonEnviar");
const botonReset  = document.getElementById("botonReset");
const cajaDia          = document.getElementById("dia");
const cajaMes          = document.getElementById("mes");
const cajaAnno         = document.getElementById("anno");
const parrafoDiaSemana = document.getElementById("diaSemana");

botonEnviar.addEventListener('click', calcularDiaSemana);

function calcularDiaSemana ()  {
    const mes = Number(cajaMes.value);
    const anno = Number(cajaAnno.value);
    const dia = Number(cajaDia.value);

    // Comprobar si la entrada es válida:
    if ( mes < 1 || mes > 12)  {
        alert("El número de mes no es válido, por favor, introduzca valor correcto");
        cajaMes.focus();
        cajaAnno.value = anno;
        cajaDia.value = dia;
        return;
    }

    if ( dia > calcularDiasMes(mes, anno) ) {
        alert("Número de días no válido para este mes: por favor, introduzca valor correcto");
        cajaDia.focus();
        cajaAnno.value = anno;
        cajaMes.value = mes;
        return;
    } 
    
    // Ahora sacamos salida con día semana:
    parrafoDiaSemana.innerText = "El día de semana es: " + diaDeLaSemana(dia,mes, anno); 
};

function diaDeLaSemana(dia, mes, anno) {
    // Debido a que JavaScript opera todo en aritmética float, y la expresión está diseñada para C, que opera en aritmética int, debemos ajustar
    //  cada operación para que realice operaciones con aritmética entera.
    // NOTA: Como no hay enteros negativos en este caso, también valdría:
    //  ((dia += mes < 3 ? anno-- : anno - 2,  ((23 * mes / 9)>>>0) + dia + 4 + ((anno / 4)>>>0) - ((anno / 100)>>>0) + ((anno / 400)>>>0) ) % 7)>>>0 ;
    // AVISO: los paréntesis al rededor de cada operación >>> 0 o |0 son imprescindibles.
    let diaSemana =  ((dia += mes < 3 ? anno-- : anno - 2,  ((23 * mes / 9)|0) + dia + 4 + ((anno / 4)|0) - ((anno / 100)|0) + ((anno / 400)|0) ) % 7)|0 ;

    switch (diaSemana) {
        case 0: return "domingo";
        case 1: return "lunes";
        case 2: return "martes";
        case 3: return "miércoles";
        case 4: return "jueves";
        case 5: return "viernes";
        case 6: return "sábado";
        default: return "error grave: " + diaSemana;  // programación a la defensiva...
    }
}

function calcularDiasMes (mes, anno)  {
    let dias = 31;  
    if ( mes == 2 ) {
        dias =  ( (anno % 4 == 0) && (anno % 100 != 0 )) || ( anno % 400 == 0)   ? 29 : 28;
    } else if ( mes == 4 || mes == 6 || mes == 9 || mes == 11 ) {
        dias = 30;
    }
    
    return dias;
}

botonReset.addEventListener('click', () => {
    parrafoDiaSemana.innerHTML = '&nbsp;';
    cajaDia.value = '';
    cajaMes.value = '';
    cajaAnno.value = '';
    cajaAnno.focus();
});
