let a1 = 1;
if (false) a1++;


let a2 = 1;
if (false) a2++;;


let a3 = 0;
let resultado3 = eval('if( a3++) a3--; else --a3;');


let a4 = 0;
let resultado4 = eval('if( a4++) a4--; else a4--;');


let a5 = 1;
eval('if (!document.all) a5++;');


let a8 = 1;
if (!'') a8++;


let a9 = 1;
if (!``) --a9;


let a10 = 1;
if (0/0) a10++;


let a11 = 1;
if (!undefined) a11++;


let a12 = 1;
if (undefined == false) a12++;


let a13 = 1;
if (null) a12++; else a14--;


let a14 = 1;
if ('foo')
 a14-- ? 1 : 0;


let a15 = 0;
if ([] || a15--)
  ++a15;


let a16 = 0;
let b16 = 1;
if ('.' && a16--)
  --b16;


let a17 = 1;
if (--a17) a17++;


let a18 = 0;
if (a18++) a18++;
