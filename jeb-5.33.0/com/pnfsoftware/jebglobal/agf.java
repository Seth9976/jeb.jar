package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import java.util.Collection;

public class agf extends agk {
   Collection A;

   public agf(Collection var1) {
      super(agk.Av.E, agk.Sv.A);
      this.A = var1;
   }

   public Collection A() {
      return this.A;
   }

   @Override
   public String toString() {
      return "switch-case: " + this.A;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      return null;
   }
}
