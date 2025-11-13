package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;

public class afv extends agk implements afq, afr {
   ICPredicate A;

   public afv(ICPredicate var1) {
      super(agk.Av.kS, agk.Sv.kS);
      this.A = var1;
   }

   @Override
   public ICPredicate pC() {
      return this.A;
   }

   @Override
   public String toString() {
      return "do-while-end: " + this.A;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      if (this.A.evaluate(var1, var2) != 0L) {
         var1.setControlWord(CMethodState.ControlWord.GOTO_PREV_BLOCK);
      } else {
         var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      }

      return null;
   }
}
