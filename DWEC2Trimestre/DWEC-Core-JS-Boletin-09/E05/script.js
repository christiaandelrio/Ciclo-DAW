let r1 = Number(9007199254740992n) + 1;
// (el número es Number.MAX_SAFE_INTEGER)

let r2 = Number(9007199254740992n + 4000n);

let a3 = BigInt(true);
let b3 = BigInt(false);
let c3 = a3 + b3;

// Falla
// let r4 = BigInt(undefined);

let r5 = BigInt("");

