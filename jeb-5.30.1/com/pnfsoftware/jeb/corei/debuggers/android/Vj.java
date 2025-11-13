package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThread;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class Vj extends AbstractCommandHandler {
   Vj(nI var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      if (var1.isEmpty()) {
         IDebuggerThread var4 = this.q.Uv.getDefaultThread();
         if (var4 == null) {
            return nI.q;
         } else {
            int var3 = (int)var4.getId();
            return ExecutionResult.success(Strings.ff(S.L("Default thread is %d(%Xh)"), var3, var3));
         }
      } else {
         int var2 = Conversion.stringToInt(getParameterSafe(var1, 0));
         return !this.q.Uv.setDefaultThread(var2) ? ExecutionResult.error(S.L("Cannot set the default thread")) : ExecutionResult.GENERIC_SUCCESS;
      }
   }
}
