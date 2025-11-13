package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import java.util.ArrayList;

public class Lt extends FB {
   public Lt(IEncodedMemoryArea var1) {
      super(var1);
   }

   @Override
   protected Yg[] pC(long var1, byte[] var3, int var4) throws ProcessorException {
      if (var1 == 255L) {
         return new Yg[]{new fj("ZA")};
      } else if (var1 == 85L) {
         return new Yg[]{this.pC(0, var4, IX.A)};
      } else if (var1 == 170L) {
         return new Yg[]{this.pC(1, var4, IX.A)};
      } else {
         int var5 = (int)var1;
         ArrayList var6 = new ArrayList();

         for (int var7 = 0; var7 < 4; var7++) {
            int var8 = 17 << var7;
            int var9 = var5 & var8;
            if (var9 == var8) {
               var6.add(this.pC(var7, var4, IX.kS));
            } else if (var9 != 0) {
               return super.pC(var1, var3, var4);
            }
         }

         return (Yg[])var6.toArray(new Yg[var6.size()]);
      }
   }

   @Override
   protected Yg pC(int var1, int var2) throws ProcessorException {
      return this.pC(var1, var2, IX.wS);
   }

   protected Yg pC(int var1, int var2, IX var3) throws ProcessorException {
      try {
         return new lw(4194318, var1, var2, 0, var3.getDataType(null).toString());
      } catch (oJ var5) {
         throw new ProcessorException(var5);
      }
   }
}
