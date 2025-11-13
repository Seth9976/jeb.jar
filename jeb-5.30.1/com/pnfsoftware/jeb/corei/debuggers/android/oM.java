package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThread;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class oM extends AbstractCommandHandler {
   oM(nI var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      IDebuggerThread var2 = this.q.Uv.getDefaultThread();
      return var2 == null ? nI.q : ExecutionResult.fromBoolean(var2.stepOver());
   }
}
