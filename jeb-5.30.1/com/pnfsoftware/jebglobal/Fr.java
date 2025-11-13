package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class Fr extends AbstractCommandHandler {
   Fr(RS var1, ICommandManager var2, String var3) {
      super(var2, var3);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      List var2 = this.q.q.ui();
      if (var2 == null) {
         return ExecutionResult.GENERIC_ERROR;
      } else {
         StringBuilder var3 = new StringBuilder();

         for (bg var5 : var2) {
            Strings.ff(var3, "- %s\n", var5);
         }

         return ExecutionResult.success(var3.toString().trim());
      }
   }
}
