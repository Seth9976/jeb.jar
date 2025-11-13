package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class xZ extends AbstractCommandHandler {
   xZ(ub var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      HX var2 = ((ia)ub.ys(this.pC)).gp();
      if (var2 == null) {
         return this.pC.xC();
      } else {
         LD var3 = var2.pC(true);
         return var3 == null ? ExecutionResult.GENERIC_ERROR : ExecutionResult.success(var3.toString());
      }
   }
}
