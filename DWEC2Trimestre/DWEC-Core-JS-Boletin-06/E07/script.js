// para que aparezca b1 ir a INSPECCIÓN y añadirla a mano
let a1 = b1 = 10;

let a2 = b2 = (213434).toString(16);

let a3 = 1;
let r1 = true && (a3 = 31);

const z = y = x = Math.max(1,3);
y++;
x++;
z++;  // comentar esta línea desde el principio para que no casque

let w1 = 20;
let a4 = false && w1++;

let w2 = 20;
let a5 = (false && w2++);

let w3 = 20;
let a6 = true && w3++;

let w4 = 20;
let a7 = (true && w4++);
