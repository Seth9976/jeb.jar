package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class mt extends gZ {
   private static final Ll.Av kS = Ll.Av.E;
   private static final IEncodedMemoryArea wS = DirectEncodedMemoryArea.get(5, 15);
   public static final gZ pC = new mt(9, wS, Ll.Av.pC);
   public static final gZ A = new mt(9, wS, kS);

   public mt(int var1, IEncodedMemoryArea var2, Ll.Av var3) {
      super(var1, var3, var2, -1);
   }

   @Override
   public Integer A(byte[] var1) {
      Integer var2 = super.A(var1);
      if (var2 == null) {
         return null;
      } else {
         if (this.pC(kS) && var2 == 6184) {
            var2 = 32768;
         }

         return var2;
      }
   }

   @Override
   public boolean E(byte[] var1) {
      return false;
   }
}
