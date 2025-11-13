package com.pnfsoftware.jeb.core.units.code.asm.processor.memory;

import java.util.function.Function;

public class FunctionEncodedMemoryArea extends AbstractEncodedMemoryArea {
   private final int length;
   private final Function f;

   public FunctionEncodedMemoryArea(int var1, Function var2) {
      this.length = var1;
      this.f = var2;
   }

   @Override
   public int getLength() {
      return this.length;
   }

   @Override
   public long decode(byte[] var1) {
      return (Long)this.f.apply(var1);
   }
}
