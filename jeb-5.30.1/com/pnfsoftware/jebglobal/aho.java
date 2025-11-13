package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;

public class aho extends aid implements ahj, ahk {
   ICPredicate RF;

   public aho(ICPredicate var1) {
      super(aid.eo.xK, aid.CU.xK);
      this.RF = var1;
   }

   @Override
   public ICPredicate q() {
      return this.RF;
   }

   @Override
   public String toString() {
      return "do-while-end: " + this.RF;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      if (this.RF.evaluate(var1, var2) != 0L) {
         var1.setControlWord(CMethodState.ControlWord.GOTO_PREV_BLOCK);
      } else {
         var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      }

      return null;
   }
}
