package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import java.util.Collection;

public class ahy extends aid {
   Collection RF;

   public ahy(Collection var1) {
      super(aid.eo.oW, aid.CU.RF);
      this.RF = var1;
   }

   public Collection RF() {
      return this.RF;
   }

   @Override
   public String toString() {
      return "switch-case: " + this.RF;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      return null;
   }
}
