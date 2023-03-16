let r1 = true == Boolean(true);

let r2 = false == Boolean(false);

let r3 = true == new Boolean(true);

let r4 = new Boolean(true) == new Boolean(true) ;

let r5 = "" == false;

let r6 = "hola" == true;

let r7 = -0.0 == false;

let r8 = 34 * 0/-1 == false;

let r9 = undefined == undefined;

let r10 = null == null;

let r11 = +0 == -0;

let r12 = '23' == 23;

let r13 = -'0'/10 == -0;

let r14 = [ 1, 2] == '1,2';

let r15 = null == undefined;

let r16 = [ 1, 2] == '1, 2';

let r17 = !(NaN == NaN);

let r18 = -Infinity == -3/0;

let r19 = undefined == false;

let r20 = undefined == true;

let r21 = NaN == NaN;

let r22 = NaN != NaN;

let r23 = new String('ola') == 'ola';

let a24 = {};
let b24 = {};
if (a24 == b24)  
   console.log("hola");

let a25 = {};
let b25 = a;
if (a25 == b25)  
   console.log("hola");
