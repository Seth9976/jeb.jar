package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class MU extends cd {
   public MU(byte[] var1, byte[] var2, int var3, int var4, boolean var5, byte[] var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public boolean validate(INativeCodeAnalyzer var1, long var2, byte[] var4, int var5, int var6) {
      if (!super.validate(var1, var2, var4, var5, var6)) {
         return false;
      } else {
         int var7 = this.q(var4, var5) & 8191;
         if (var7 == 0) {
            return true;
         } else {
            while (var7 != 0 && (var7 & 1) == 0) {
               var7 >>>= 1;
            }

            while ((var7 & 1) == 1) {
               var7 >>>= 1;
            }

            return var7 == 0;
         }
      }
   }

   private int q(byte[] var1, int var2) {
      if (this.RF()) {
         return (var1[var2 + 2] & 0xFF) << 8 | var1[var2 + 3] & 0xFF;
      } else {
         return this.getProcessorMode() == 32 ? (var1[var2 + 1] & 0xFF) << 8 | var1[var2 + 0] & 0xFF : (var1[var2 + 3] & 0xFF) << 8 | var1[var2 + 2] & 0xFF;
      }
   }
}
