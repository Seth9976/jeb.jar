package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class gv extends AbstractCommandHandler {
   gv(if var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      Cg var2 = ((um)if.nf(this.q)).lm();
      if (var2 == null) {
         return this.q.qa();
      } else {
         Ht var3 = var2.q(true);
         return var3 == null ? ExecutionResult.GENERIC_ERROR : ExecutionResult.success(var3.toString());
      }
   }
}
