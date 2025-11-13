package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import java.util.Map;

class XU extends abo {
   private Map A;

   public XU(INativeCodeAnalyzer var1, Map var2, int... var3) {
      super(var1, var3);
      this.A = var2;
   }

   @Override
   protected boolean pC(long var1, int var3) {
      if (var3 != 4 && var3 != 8) {
         return super.pC(var1, var3);
      } else {
         return this.pC.getMemory().getStandardEndianess().isLittle() ? this.pC(var1) : this.pC(var1 + (var3 >>> 1));
      }
   }

   private boolean pC(long var1) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var3 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)this.A.get(var1);
      if (var3 == null) {
         var3 = PU.pC(this.pC, var1);
      }

      return var3 == null ? false : var3.UT().E() && var3.pC().isPCUpdated();
   }
}
