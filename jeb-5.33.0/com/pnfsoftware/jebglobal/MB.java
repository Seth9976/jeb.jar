package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class MB implements Hu {
   private final int pC;
   private final IEncodedMemoryArea A;
   private final boolean kS;

   public MB(int var1, IEncodedMemoryArea var2) {
      this(var1, var2, false);
   }

   public MB(int var1, IEncodedMemoryArea var2, boolean var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   protected int pC(byte[] var1) {
      int var2 = this.A.decodeInt(var1);
      if (var2 == 7) {
         var2 = 4;
      } else if (var2 > 3) {
         var2 = -1;
      }

      return var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = 0;
      nl var4 = IX.pC(7, this.A);
      int var5 = this.pC(var1);
      if (var5 > 0) {
         var3 = DirectEncodedMemoryArea.get(this.pC + 4 - var5, var5).decodeInt(var1);
      }

      int var6 = 0;
      if (var5 < 4) {
         var6 = DirectEncodedMemoryArea.get(this.pC, 4 - var5).decodeInt(var1);
      }

      Object var7;
      if (this.kS) {
         var7 = LC.kS(4194318, 16, var2);
      } else {
         int var8 = var1[2] & 128;

         try {
            String var9 = (var8 == 0 ? "H" : "V") + "." + var4.getDataType(var1);
            var7 = new lw(4194318, var3, var2, 0, var9).pC("");
         } catch (oJ var10) {
            throw new ProcessorException(var10);
         }
      }

      Yg var11 = LC.pC(12 + DirectEncodedMemoryArea.get(13, 2).decodeInt(var1), 32);
      Yg var12 = Yg.pC((long)var6, 0);
      return new rw((Yg)var7, var11, var12);
   }
}
