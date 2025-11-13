package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class EG extends AbstractCommandHandler {
   EG(RS var1, ICommandManager var2, String var3) {
      super(var2, var3);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      boolean var2;
      if (var1.isEmpty()) {
         var2 = this.q.q.rL();
      } else {
         long var3 = this.q.q(getParameterSafe(var1, 0));
         var2 = this.q.q.xK(var3);
      }

      return ExecutionResult.fromBoolean(var2);
   }
}
