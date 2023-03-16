let a1 = 1;
let r1 = true || ++a1;


let a2 = 1;
let r2 = false || ++a2;


let a3 = -1;
let r3 = ++a3 || true;


let a4 = 1;
let r4 = false && ++a4;

let a5 = 0;
let b5 = 1;
let r5 = a5++ && b5++

let a6 = 1;
let b6 = 0;
let r6 = --a6 || b6++;

let a7 = 1;
let r7 = --a7 || a7++;

let a8 = 1;
let r8 = --a8 && a8++;

let a9 = 1;
let r9 = a9-- && a9++


// Comprobamos todas las combinaciones de valores bool para a10 y b10:
let a10 = false;
let b10 = false;
let r10 = !(a10 && b10) === (!a10 || !b10);

a10 = true;
b10 = false;
r10 = !(a10 && b10) === (!a10 || !b10);

a10 = false;
b10 = true;
r10 = !(a10 && b10) === (!a10 || !b10);

a10 = true;
b10 = true;
r10 = !(a10 && b10) === (!a10 || !b10);


// La otra Ley de De Morgan:
let a11 = false;
let b11 = false;
let r11 = !(a11 || b11) === (!a11 && !b11);

a11 = false;
b11 = true;
r11 = !(a11 || b11) === (!a11 && !b11);

a11 = true;
b11 = false;
r11 = !(a11 || b11) === (!a11 && !b11);

a11 = true;
b11 = true;
r11 = !(a11 || b11) === (!a11 && !b11);

// Ojo con los paréntesis
a12 = false;
b12 = false;
let r12 = !(a12 && b12) === !a12 || !b12;

a12 = true;
b12 = false;
r12 = !(a12 && b12) === !a12 || !b12;

a12 = false;
b12 = true;
r12 = !(a12 && b12) === !a12 || !b12;

a12 = true;
b12 = true;
r12 = !(a12 && b12) === !a12 || !b12;

// 
let a13 = false;
let b13 = false;
let r13 = !(a13 || b13) === (!a13 && !b13);

a13 = false;
b11 = true;
r13 = !(a13 || b13) === (!a13 && !b13);

a13 = true;
b13 = false;
r13 = !(a13 || b13) === (!a13 && !b13);

a13 = true;
b13 = true;
r13 = !(a13 || b13) === (!a13 && !b13);
