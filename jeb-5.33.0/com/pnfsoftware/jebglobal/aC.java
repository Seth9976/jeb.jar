package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

class aC extends abp {
   private Map A;

   public aC(INativeCodeAnalyzer var1, Map var2, int... var3) {
      super(var1, var3);
      this.A = var2;
   }

   @Override
   protected boolean pC(long var1, int var3) {
      return !this.A(var1, var3);
   }

   public boolean A(long var1, int var3) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var4 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)this.A.get(var1);
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var5 = null;
      if (var4 == null) {
         var4 = PU.pC(this.pC, var1);
      }

      boolean var6 = false;
      if (var4 != null && (var4.getSize() == 2 && var3 > 2 || var4.getSize() == 4 && var3 > 4)) {
         var5 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)this.A.get(var1 + var4.getSize());
         var6 = true;
         if (var5 == null) {
            return false;
         }
      }

      return var4 == null || var6 && var5 == null && !PU.wS(var4) || !this.pC(var4, var5, true, 0);
   }

   protected boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, boolean var3, int var4) {
      if (var1 == null) {
         return var4 == 2;
      } else if (PU.ld(var1)) {
         return true;
      } else if (var1.A().length < 2) {
         return true;
      } else {
         if (var1.A().length == 2) {
            if (Strings.contains(var1.wS().pC(), "ADD", "SUB")) {
               return this.pC(var2, null, false, var4 + 1);
            }

            mN var5 = MX.pC(var1);
            if (var5 != null) {
               mN var6 = null;
               if (var2 != null) {
                  var6 = MX.pC(var2);
               }

               if (!var5.ys() && var5.wS().UT()) {
                  return true;
               }

               if (var6 == null || (!var5.ys() || var6.ys()) && (var5.ys() || !var6.ys())) {
                  if (var5.ys() && var5.ld().length == 1 && var5.A(0).equals(var5.wS().A())) {
                     return true;
                  }

                  return this.pC(var2, null, false, var4 + 1);
               }

               return true;
            }
         } else {
            Yg var7 = var1.A()[0];
            Yg var8 = var1.A()[1];
            if (var7.equals(var8)) {
               return true;
            }
         }

         return this.pC(var2, null, false, var4);
      }
   }
}
