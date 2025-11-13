package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class Cj extends AbstractCommandHandler {
   Cj(RS var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      return !this.q.q.Dw() ? ExecutionResult.GENERIC_ERROR : ExecutionResult.GENERIC_SUCCESS;
   }
}
