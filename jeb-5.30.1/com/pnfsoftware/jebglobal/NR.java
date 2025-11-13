package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class NR extends rR {
   private static final jD.eo oW = jD.eo.oW;
   private static final IEncodedMemoryArea gO = DirectEncodedMemoryArea.get(5, 15);
   public static final rR Dw = new NR(9, gO, jD.eo.q);
   public static final rR Uv = new NR(9, gO, oW);

   public NR(int var1, IEncodedMemoryArea var2, jD.eo var3) {
      super(var1, var3, var2, -1);
   }

   @Override
   public Integer RF(byte[] var1) {
      Integer var2 = super.RF(var1);
      if (var2 == null) {
         return null;
      } else {
         if (this.q(oW) && var2 == 6184) {
            var2 = 32768;
         }

         return var2;
      }
   }

   @Override
   public boolean oW(byte[] var1) {
      return false;
   }
}
