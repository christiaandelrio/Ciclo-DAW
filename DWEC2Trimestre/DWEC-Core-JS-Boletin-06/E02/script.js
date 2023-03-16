let a1 = 5;
let r1 = a1++ + --a1;

let a2 = 1;
let r2 = a2++ + a2--;

let a3 = 1;
let r3 = ++a3 + a3--;

let a4 = 4;
let r4 = a4 > 1 ? a4 < 1 ? a4++ : a4-- : --a4 ;

let a5 = 1;
let b = 2;
let r5 = a5 * b >= 2 ? (a5 += b--, ++b) : a5 /= --b

let a6 = 1;
for (let i = 1, k = 10; i < 100 ;i+=k) {
    a += i;
}
   