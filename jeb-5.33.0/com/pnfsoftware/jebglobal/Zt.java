package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class Zt extends AbstractCommandHandler {
   Zt(vK var1, ICommandManager var2, String var3) {
      super(var2, var3);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      LD var2 = this.pC.pC.Cu();
      return var2 == null ? ExecutionResult.GENERIC_ERROR : ExecutionResult.success(var2.toString());
   }
}
