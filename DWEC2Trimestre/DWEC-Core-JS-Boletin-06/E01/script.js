
let r1 = 15 - 3 - 2;
// Agrupa: (15 - 3) - 2 

let r2 = 12 / 6 / 2;
// Agrupa: (12 / 6) / 2 

let r3 = 3 ** 2 ** 3;
// Agrupa: (3 ** ( 2 ** 3))

let r4 = 15 - 3 ** 4 / 3;
// Agrupa: (15 - ((3 ** 4) / 3))

let r5 = 10/-0;
// Agrupa: (10/(-0))

let r6 = -10/-0;
// Agrupa: (-10)/(-0)

let r7 = 10 * 10 ** 5 % 20 + 10 % 5;
// Agrupa: (   ( (10 * (10 ** 5)) % 20) + (10 % 5)  )

// Diseccionamos la expresión anterior:
let r7a = 10 ** 5;
let r7b = 10 * t7a;
let r7c = t7b % 20;
let r7d = 10 % 5;
let r7e = t7c + t7d;

let a8 = 10;
let b8 =  a8++ % 10 ? "ola " + a8 : "hola " + a8;
// Agrupa: ( ((a8++) % 10) ? ("ola " + a8) : ("hola " + a8) );

let a9 = 9;
let b9 = a9++ % 10 ? "ola " + a9 : "hola " + a9;
// Agrupa: (  ( (a9++) % 10 ) ? ("ola " + a9) : ("hola " + a9) )

let a10 = 4;
let r10 = a10-- <= 5 % a10  ? ++a10 : a10--;
// Agrupa: (  ( (a10--) <= (5 % a10) )  ? (++a10) : (a10--) )

let a11 = 4;
let r11 = a11-- >= 5 % a11  ? ++a11 : a11--;

let r12 = Infinity / Infinity;

let r13 = 0/-0;

let r14 = 1_234 + 1_000;

let r15 = 1_000.34 + 1_000.10;

let r16 = 0xFE + 1;

let r17 = 0B001 + 0B001;

let r19 = 0o333 + 0o001;
