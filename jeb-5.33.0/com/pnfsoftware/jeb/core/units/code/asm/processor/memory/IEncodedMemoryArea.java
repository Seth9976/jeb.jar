package com.pnfsoftware.jeb.core.units.code.asm.processor.memory;

public interface IEncodedMemoryArea {
   int getLength();

   long decode(byte[] var1);

   int decodeInt(byte[] var1);
}
