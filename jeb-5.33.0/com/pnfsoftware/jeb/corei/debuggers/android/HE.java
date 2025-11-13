package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.core.units.code.debug.DebuggerThreadStatus;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThread;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThreadStackFrame;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class HE extends AbstractCommandHandler {
   HE(K var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      IDebuggerThread var2 = this.pC.UT.getDefaultThread();
      int var3 = var2 == null ? 0 : (int)var2.getId();
      List var4 = this.pC.UT.getThreads();
      if (var4 == null) {
         return ExecutionResult.GENERIC_ERROR;
      } else {
         StringBuilder var5 = new StringBuilder();

         for (IDebuggerThread var7 : var4) {
            int var8 = var7.getId() == var3 ? 42 : 45;
            Strings.ff(var5, "%c %d/%Xh \"%s\" [%s]", Character.valueOf((char)var8), var7.getId(), var7.getId(), Strings.safe(var7.getName()), var7.getStatus());
            if (var7.getStatus() == DebuggerThreadStatus.PAUSED) {
               String var9 = "?";
               IDebuggerThreadStackFrame var10 = var7.getFrame(0);
               if (var10 != null) {
                  var9 = var10.getAddress();
               }

               var5.append(" @ " + var9);
            }

            var5.append("\n");
         }

         return ExecutionResult.success(var5.toString());
      }
   }
}
