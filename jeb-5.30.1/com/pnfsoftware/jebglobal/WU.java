package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import java.util.Map;

class WU extends ade {
   private Map RF;

   public WU(INativeCodeAnalyzer var1, Map var2) {
      super(var1);
      this.RF = var2;
   }

   @Override
   protected IInstruction q(long var1) {
      fA var3 = (fA)this.RF.get(var1);
      if (var3 == null) {
         var3 = OC.q(this.q, var1);
      }

      if (var3 == null) {
         return null;
      } else {
         return var3.Uv().oW() && var3.Dw().q().equals("NOP") ? var3 : null;
      }
   }
}
