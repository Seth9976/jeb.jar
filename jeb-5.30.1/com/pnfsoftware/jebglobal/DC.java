package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import java.util.Map;

class DC extends adg {
   private Map RF;

   public DC(INativeCodeAnalyzer var1, Map var2, int... var3) {
      super(var1, var3);
      this.RF = var2;
   }

   @Override
   protected boolean q(long var1, int var3) {
      if (var3 != 4 && var3 != 8) {
         return super.q(var1, var3);
      } else {
         return this.q.getMemory().getStandardEndianess().isLittle() ? this.q(var1) : this.q(var1 + (var3 >>> 1));
      }
   }

   private boolean q(long var1) {
      fA var3 = (fA)this.RF.get(var1);
      if (var3 == null) {
         var3 = OC.q(this.q, var1);
      }

      return var3 == null ? false : var3.Uv().oW() && var3.q().isPCUpdated();
   }
}
