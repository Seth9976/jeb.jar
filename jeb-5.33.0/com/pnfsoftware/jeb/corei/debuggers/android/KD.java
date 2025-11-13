package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class KD extends AbstractCommandHandler {
   KD(K var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      return getParameter(var1, 0) != null ? ExecutionResult.NOT_IMPLEMENTED : ExecutionResult.fromBoolean(this.pC.UT.run());
   }
}
