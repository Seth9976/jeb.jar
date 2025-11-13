package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class PL extends AbstractCommandHandler {
   PL(ub var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      if (!((ia)ub.E(this.pC)).pC()) {
         return ExecutionResult.error("The native process must be suspended first");
      } else if (this.pC.E == null) {
         return ExecutionResult.GENERIC_ERROR;
      } else if (this.pC.wS >= this.pC.UT) {
         this.pC.E = null;
         return ExecutionResult.GENERIC_ERROR;
      } else {
         long var2 = this.pC.UT - this.pC.wS;
         long var4 = ((ia)ub.sY(this.pC)).getMemory().findBytes(this.pC.wS, var2, this.pC.E);
         if (var4 < 0L) {
            this.pC.E = null;
            return ExecutionResult.error("Not found");
         } else {
            long var6 = this.pC.wS + var4;
            this.pC.wS = var6 + 1L;
            return ExecutionResult.success(Strings.ff("Found at %Xh", var6));
         }
      }
   }
}
