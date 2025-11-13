package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.android.IDalvikDebuggerUnit;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.Ux;
import java.util.List;

class EE extends AbstractCommandHandler {
   EE(CU var1, ICommandManager var2, String var3, String[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      try {
         LC var2 = ((CI)CU.xK(this.q)).oW();
         if (!(var2 instanceof Ux)) {
            return ExecutionResult.GENERIC_ERROR;
         } else if (var1.isEmpty()) {
            IDalvikDebuggerUnit.ThreadFrameSlotIndexMode var5 = ((CI)CU.Dw(this.q)).getThreadFrameSlotIndexMode();
            return ExecutionResult.success("Current mode: " + var5);
         } else {
            IDalvikDebuggerUnit.ThreadFrameSlotIndexMode var3 = IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.valueOf(
               getParameterSafe(var1, 0).trim().toUpperCase()
            );
            ((CI)CU.Uv(this.q)).setThreadFrameSlotIndexMode(var3);
            return ExecutionResult.success("New mode: " + var3);
         }
      } catch (Exception var4) {
         CU.xK.catching(var4);
         return ExecutionResult.error(var4);
      }
   }
}
