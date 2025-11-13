package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class kA implements Ef {
   private static final Dm RF = Dm.q(7, kA::q);
   private int xK;
   private int Dw;
   static final Ef q = new NS();

   private kA(int var1, int var2) {
      this.xK = var1;
      this.Dw = var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = q(var1);
      DirectEncodedMemoryArea var4 = DirectEncodedMemoryArea.get(this.Dw + var3, 4 - var3);
      return new VP(XD.q(this.xK, RF), var4).buildOperand(var1, var2);
   }

   static int q(byte[] var0) {
      int var1 = var0[1] & 31;

      for (int var2 = 0; var2 < 4; var2++) {
         if ((var1 & 1 << var2) != 0) {
            return var2;
         }
      }

      return -1;
   }

   public static final Ef q(int var0, int var1) {
      return new kA(var0, var1);
   }

   public static final Ef q(int var0) {
      return new XD(new gq(DirectEncodedMemoryArea.get(16, 5), 524294, 1048582, 2097158, 4194310), DirectEncodedMemoryArea.get(var0, 5));
   }
}
