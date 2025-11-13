package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class Yh extends AbstractCommandHandler {
   Yh(vK var1, ICommandManager var2, String var3) {
      super(var2, var3);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      List var2 = this.pC.pC.Ek().pC();
      if (var2 == null) {
         return ExecutionResult.GENERIC_ERROR;
      } else {
         StringBuilder var3 = new StringBuilder();

         for (xK var5 : var2) {
            Strings.ff(var3, "- %Xh [activated: %b]\n", var5.pC(), var5.pC);
         }

         return ExecutionResult.success(var3.toString());
      }
   }
}
