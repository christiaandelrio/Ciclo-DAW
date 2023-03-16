let r = Number.MAX_SAFE_INTEGER.toString(16);

r = Number.MAX_SAFE_INTEGER.toString(2);

r = (343).toString(2);

r = ~0b0000000;

r = ~0b11111111;

r = ~0xFFFFFFFF;

r = ~0x80000000;

r = ~1;

r = ~~1;

r = ~(!0);

r = ~(!1);

r = 0b11001111 & 0b10010011;

r = 0b11001111 | 0b10010011;

r = 0b11001111 ^ 0b10010011;

r = 65 & 32;

r = 65 | 32;

r = 65 ^ 32;

r = 0b01010101 ^ 0b10101010;

r = 0b00000001<<1;

r = (0b00000001<<1)<<1;

r = ((0b00000001<<1)<<1)<<1;

r = 0b00000001<<3;

r = 0x80000000>>4;

r = 0x80000000>>>4;

r = (0x80000000 + 0x0fffffff)|0;

r = ~false;

r = ~true;

r = ~~false;

r = ~~true;

r = ~undefined;

r = ~~undefined;

r = !(~false);

r = !(~~false);

r = ~a === -a - 1;

r = ~~(-0);;

r = -0<<2>>2;

