let o1 = {};


let o2 = {e1:{}, e2:{}, e3:{}};


let o3 = 
  {e1:{e11:{}, e12:{}}, 
   e2:{}, 
   e3:{e3e1:{}, e3e2: { e3e2e1:{}}}
  };


let o4 = {0: "hola", 1: "adios", 3: "mañana", c4: "hoy" };
console.log(o4[1]);
// console.log(o4.1);
console.log(o4.c4);
delete o4.c4;


let o5 = {0: "hola", 1: "adios", 2: "mañana", 4: "hoy" };
for (let i = 0; i<= 4; i++) {
   console.log(o5[i]);
}



let o6 = {3: "hola", 
          1: "adios", 
          4: "mañana", 
          2: "hoy" };
for (let i = 0; i<= 4; i++) {
   console.log(o6[i]);
}
delete o6[4];



let o7 = { "hola": 34,
           '': [1, 3], 
           ":": {c1: 3, c2: 1},
         };
// let r7a = o7."hola";
let r7b = o7["hola"];
// let r7c = o7.'';
let r7d = o7[""];


let o8 = [ {c1:[1,2], 2: [3, 4]} ,
              {c1:"hola", "": "adios"} ,
              { 3: [ { c1: [1,2]}, 3] },
              { 'clave' : "hola"}, 
            ];
let r8a = o8[0].c1[1];
let r8b = o8[0][2][1];
let r8c = o8[1].c1[2];
let r8d = o8[1][""][1];
let r8e = o8[2][3][0].c1[1];
delete o8[2][3][0].c1[1];
