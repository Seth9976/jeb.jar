package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class Gf extends AbstractCommandHandler {
   Gf(RS var1, ICommandManager var2, String var3) {
      super(var2, var3);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      Ht var2 = this.q.q.EB();
      return var2 == null ? ExecutionResult.GENERIC_ERROR : ExecutionResult.success(var2.toString());
   }
}
