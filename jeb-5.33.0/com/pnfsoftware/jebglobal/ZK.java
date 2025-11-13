package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class ZK extends AbstractCommandHandler {
   ZK(vK var1, ICommandManager var2, String var3) {
      super(var2, var3);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      boolean var2;
      if (var1.isEmpty()) {
         var2 = this.pC.pC.cX();
      } else {
         long var3 = this.pC.pC(getParameterSafe(var1, 0));
         var2 = this.pC.pC.A(var3);
      }

      return ExecutionResult.fromBoolean(var2);
   }
}
