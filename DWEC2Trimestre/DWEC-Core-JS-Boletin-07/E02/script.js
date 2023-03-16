let a1 = ['Lucas', 'Ainoa', 'Xian'];
let r1 = a1[0] + " " + a1[2];

let a2 = [1, '', '3'];
let r2 = a2[0] + a2[1] + a2[2];

let a3 = [1, '', '3'];
let r3 = a3[0] - a3[1] - a3[2];

let a4 = [1, '0', '3'];
let r4 = a4[0] + a4[1] - a4[2];

let a5 = [1,  , '3'];
let r5 = a5[0] + a5[1] + a5[2];

let a6 = [1,  , '3'];
let r6 = a6[0] + a6[1] - a6[2];

let a7 = [1,  ,  , 3, ];
let r7 = a7.length;

let a8 = [1, 3 , '3'];
let r8 = a8.length;

let a9 = [1,  ,  , 3, ];
let r9 = [...a9 ];

let a10 = [1,  ,  , 3, ];
let o10 = {...a10};

let a11 = [ [1,2,3], 
          [ [3,2] ,[5,6]] , 
          [7, ,8], ];
let r11a = a11[0];
let r11b = a11[1][1][1];
let r11c = a11[2][1];
let r11d = [...a11];
let o11  = {...a11};

let [a, b, c] = [1, 2, 3];

// let {x, y, z} = {1, 2, 3};

let o14 = {...["juan", "luis", "moni"] };
let r14a = o14[0]
let r14b = o14[1]

