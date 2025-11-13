package com.pnfsoftware.jeb.core.units.code.asm.processor.memory;

public abstract class AbstractEncodedMemoryArea implements IEncodedMemoryArea {
   @Override
   public int decodeInt(byte[] var1) {
      return (int)this.decode(var1);
   }
}
