package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerBreakpoint;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class tw extends AbstractCommandHandler {
   tw(nI var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      if (var1.isEmpty()) {
         return ExecutionResult.fromBoolean(this.q.Uv.clearBreakpoints());
      } else {
         int var2 = Conversion.stringToInt(getParameterSafe(var1, 0), -1);
         IDebuggerBreakpoint var3 = (IDebuggerBreakpoint)Lists.get(this.q.Uv.getBreakpoints(), var2);
         return var3 == null ? ExecutionResult.GENERIC_ERROR : ExecutionResult.fromBoolean(this.q.Uv.clearBreakpoint(var3));
      }
   }
}
