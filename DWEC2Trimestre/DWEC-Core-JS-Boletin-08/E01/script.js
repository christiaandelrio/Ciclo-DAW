let r1 = true === true;


let r2 = true === Boolean(true);


let r3 = true === new Boolean(true);


let r4 = Boolean(true) === new Boolean(true);


let r5 = 0n === -0n;


let r6 = 0 === 0n;


let r7 = '0' === 0;


let r8 = undefined === true;


let r9 = NaN === NaN;


let r10 = NaN !== NaN;


let r11 = -0 === 0;


let r12 = undefined === undefined;


let r13 = -Infinity === -3/0;

let a14 = {};
let b14 = {};
if (a14 === b14)  
   console.log("hola");

let a15 = {};
let b15 = a15;
if (a15 === b15)  
   console.log("hola");
;