package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class MJ implements Hu {
   private static final IX A = IX.pC(7, MJ::pC);
   private int kS;
   private int wS;
   static final Hu pC = new gf();

   private MJ(int var1, int var2) {
      this.kS = var1;
      this.wS = var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = pC(var1);
      DirectEncodedMemoryArea var4 = DirectEncodedMemoryArea.get(this.wS + var3, 4 - var3);
      return new Yu(ER.pC(this.kS, A), var4).buildOperand(var1, var2);
   }

   static int pC(byte[] var0) {
      int var1 = var0[1] & 31;

      for (int var2 = 0; var2 < 4; var2++) {
         if ((var1 & 1 << var2) != 0) {
            return var2;
         }
      }

      return -1;
   }

   public static final Hu pC(int var0, int var1) {
      return new MJ(var0, var1);
   }

   public static final Hu pC(int var0) {
      return new ER(new XG(DirectEncodedMemoryArea.get(16, 5), 524294, 1048582, 2097158, 4194310), DirectEncodedMemoryArea.get(var0, 5));
   }
}
