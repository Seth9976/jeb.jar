package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class MG implements Ef {
   private final int q;
   private final IEncodedMemoryArea RF;
   private final boolean xK;

   public MG(int var1, IEncodedMemoryArea var2) {
      this(var1, var2, false);
   }

   public MG(int var1, IEncodedMemoryArea var2, boolean var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   protected int q(byte[] var1) {
      int var2 = this.RF.decodeInt(var1);
      if (var2 == 7) {
         var2 = 4;
      } else if (var2 > 3) {
         var2 = -1;
      }

      return var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = 0;
      PZ var4 = Dm.q(7, this.RF);
      int var5 = this.q(var1);
      if (var5 > 0) {
         var3 = DirectEncodedMemoryArea.get(this.q + 4 - var5, var5).decodeInt(var1);
      }

      int var6 = 0;
      if (var5 < 4) {
         var6 = DirectEncodedMemoryArea.get(this.q, 4 - var5).decodeInt(var1);
      }

      Object var7;
      if (this.xK) {
         var7 = GC.xK(4194318, 16, var2);
      } else {
         int var8 = var1[2] & 128;

         try {
            String var9 = (var8 == 0 ? "H" : "V") + "." + var4.getDataType(var1);
            var7 = new RI(4194318, var3, var2, 0, var9).q("");
         } catch (eK var10) {
            throw new ProcessorException(var10);
         }
      }

      CW var11 = GC.q(12 + DirectEncodedMemoryArea.get(13, 2).decodeInt(var1), 32);
      CW var12 = CW.q((long)var6, 0);
      return new BY((CW)var7, var11, var12);
   }
}
