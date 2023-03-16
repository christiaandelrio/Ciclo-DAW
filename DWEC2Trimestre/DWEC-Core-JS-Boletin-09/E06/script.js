let r1 = "23" + 2;

let r2 = +"23" + 2;

let r3 = "23" - 2;

let r4 = 2 - "23";

let r5 = +"23.4";

let r6 = -"23.4";

// Falla
// let r7 = +"23443443" + 2344n;

let r8 = BigInt("23443443") + 2344n;
