package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThread;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class cq extends AbstractCommandHandler {
   cq(K var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      IDebuggerThread var2 = this.pC.UT.getDefaultThread();
      return var2 == null ? K.pC : ExecutionResult.fromBoolean(var2.stepOut());
   }
}
