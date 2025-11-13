package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;

public class afz extends agk implements afr {
   ICPredicate A;

   public afz(ICPredicate var1) {
      super(agk.Av.A, agk.Sv.A);
      this.A = var1;
   }

   @Override
   public ICPredicate pC() {
      return this.A;
   }

   @Override
   public String toString() {
      return "else-if: " + this.A;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      if (this.A.evaluate(var1, var2) != 0L) {
         var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      } else {
         var1.setControlWord(CMethodState.ControlWord.SKIP_NEXT_BLOCK);
      }

      return null;
   }
}
