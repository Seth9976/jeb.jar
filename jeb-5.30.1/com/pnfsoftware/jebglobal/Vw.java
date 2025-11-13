package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import java.util.ArrayList;

public class Vw extends rB {
   public Vw(IEncodedMemoryArea var1) {
      super(var1);
   }

   @Override
   protected CW[] q(long var1, byte[] var3, int var4) throws ProcessorException {
      if (var1 == 255L) {
         return new CW[]{new sJ("ZA")};
      } else if (var1 == 85L) {
         return new CW[]{this.q(0, var4, Dm.RF)};
      } else if (var1 == 170L) {
         return new CW[]{this.q(1, var4, Dm.RF)};
      } else {
         int var5 = (int)var1;
         ArrayList var6 = new ArrayList();

         for (int var7 = 0; var7 < 4; var7++) {
            int var8 = 17 << var7;
            int var9 = var5 & var8;
            if (var9 == var8) {
               var6.add(this.q(var7, var4, Dm.xK));
            } else if (var9 != 0) {
               return super.q(var1, var3, var4);
            }
         }

         return (CW[])var6.toArray(new CW[var6.size()]);
      }
   }

   @Override
   protected CW q(int var1, int var2) throws ProcessorException {
      return this.q(var1, var2, Dm.Dw);
   }

   protected CW q(int var1, int var2, Dm var3) throws ProcessorException {
      try {
         return new RI(4194318, var1, var2, 0, var3.getDataType(null).toString());
      } catch (eK var5) {
         throw new ProcessorException(var5);
      }
   }
}
