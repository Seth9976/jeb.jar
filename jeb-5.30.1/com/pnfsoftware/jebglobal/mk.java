package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class mk extends AbstractCommandHandler {
   mk(RS var1, ICommandManager var2, String var3) {
      super(var2, var3);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      List var2 = this.q.q.cC().q();
      if (var2 == null) {
         return ExecutionResult.GENERIC_ERROR;
      } else {
         StringBuilder var3 = new StringBuilder();

         for (fQ var5 : var2) {
            Strings.ff(var3, "- %Xh [activated: %b]\n", var5.q(), var5.Dw);
         }

         return ExecutionResult.success(var3.toString());
      }
   }
}
