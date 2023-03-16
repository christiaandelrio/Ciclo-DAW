
// diaSemana = 0 es domingo; ... diaSemana = 7 es sábado:
let dia = 1;
let mes = 1;
let anno = 2024;
let diaSemana =  ((dia += mes < 3 ? anno-- : anno - 2,  ((23 * mes / 9)|0) + dia + 4 + ((anno / 4)|0) - ((anno / 100)|0) + ((anno / 400)|0) ) % 7)|0 ;