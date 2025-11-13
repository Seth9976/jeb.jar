package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;

public class aft extends agk {
   public aft() {
      super(agk.Av.pC, agk.Sv.kS);
   }

   @Override
   public String toString() {
      return "}";
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      return null;
   }
}
