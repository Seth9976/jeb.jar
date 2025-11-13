package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

class Fj extends adh {
   private Map RF;

   public Fj(INativeCodeAnalyzer var1, Map var2, int... var3) {
      super(var1, var3);
      this.RF = var2;
   }

   @Override
   protected boolean q(long var1, int var3) {
      return !this.RF(var1, var3);
   }

   public boolean RF(long var1, int var3) {
      fA var4 = (fA)this.RF.get(var1);
      fA var5 = null;
      if (var4 == null) {
         var4 = OC.q(this.q, var1);
      }

      boolean var6 = false;
      if (var4 != null && (var4.getSize() == 2 && var3 > 2 || var4.getSize() == 4 && var3 > 4)) {
         var5 = (fA)this.RF.get(var1 + var4.getSize());
         var6 = true;
         if (var5 == null) {
            return false;
         }
      }

      return var4 == null || var6 && var5 == null && !OC.Dw(var4) || !this.q(var4, var5, true, 0);
   }

   protected boolean q(fA var1, fA var2, boolean var3, int var4) {
      if (var1 == null) {
         return var4 == 2;
      } else if (OC.nf(var1)) {
         return true;
      } else if (var1.RF().length < 2) {
         return true;
      } else {
         if (var1.RF().length == 2) {
            if (Strings.contains(var1.Dw().q(), "ADD", "SUB")) {
               return this.q(var2, null, false, var4 + 1);
            }

            fV var5 = PG.q(var1);
            if (var5 != null) {
               fV var6 = null;
               if (var2 != null) {
                  var6 = PG.q(var2);
               }

               if (!var5.nf() && var5.Dw().Uv()) {
                  return true;
               }

               if (var6 == null || (!var5.nf() || var6.nf()) && (var5.nf() || !var6.nf())) {
                  if (var5.nf() && var5.gP().length == 1 && var5.RF(0).equals(var5.Dw().RF())) {
                     return true;
                  }

                  return this.q(var2, null, false, var4 + 1);
               }

               return true;
            }
         } else {
            CW var7 = var1.RF()[0];
            CW var8 = var1.RF()[1];
            if (var7.equals(var8)) {
               return true;
            }
         }

         return this.q(var2, null, false, var4);
      }
   }
}
