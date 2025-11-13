package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerTargetInformation;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class ej extends AbstractCommandHandler {
   ej(nI var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      IDebuggerTargetInformation var2 = this.q.Uv.getTargetInformation();
      return var2 == null ? ExecutionResult.GENERIC_ERROR : ExecutionResult.success(var2.toString());
   }
}
