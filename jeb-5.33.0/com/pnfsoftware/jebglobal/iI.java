package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import java.util.Map;

class iI extends abm {
   private Map A;

   public iI(INativeCodeAnalyzer var1, Map var2) {
      super(var1);
      this.A = var2;
   }

   @Override
   protected IInstruction pC(long var1) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var3 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)this.A.get(var1);
      if (var3 == null) {
         var3 = PU.pC(this.pC, var1);
      }

      if (var3 == null) {
         return null;
      } else {
         return var3.UT().E() && var3.wS().pC().equals("NOP") ? var3 : null;
      }
   }
}
