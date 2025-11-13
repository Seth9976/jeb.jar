package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;

public class ahs extends aid implements ahk {
   ICPredicate RF;

   public ahs(ICPredicate var1) {
      super(aid.eo.RF, aid.CU.RF);
      this.RF = var1;
   }

   @Override
   public ICPredicate q() {
      return this.RF;
   }

   @Override
   public String toString() {
      return "else-if: " + this.RF;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      if (this.RF.evaluate(var1, var2) != 0L) {
         var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      } else {
         var1.setControlWord(CMethodState.ControlWord.SKIP_NEXT_BLOCK);
      }

      return null;
   }
}
