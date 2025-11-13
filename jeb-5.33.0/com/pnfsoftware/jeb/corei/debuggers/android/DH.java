package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerBreakpoint;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class DH extends AbstractCommandHandler {
   DH(K var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      if (!var1.isEmpty()) {
         String var6 = getParameter(var1, 0);
         long var7 = this.pC.pC(var6);
         return var7 == -1L ? ExecutionResult.fromObject(this.pC.UT.setBreakpoint(var6, null)) : ExecutionResult.fromObject(this.pC.UT.setBreakpoint(var7));
      } else {
         StringBuilder var2 = new StringBuilder();
         int var3 = 0;

         for (IDebuggerBreakpoint var5 : this.pC.UT.getBreakpoints()) {
            Strings.ff(var2, "%d - %s [%s: %b]\n", var3, var5.getAddress(), S.L("enabled"), var5.isEnabled());
            var3++;
         }

         return ExecutionResult.success(var2.toString());
      }
   }
}
