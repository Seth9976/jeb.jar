package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.android.IDalvikDebuggerUnit;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jebglobal.Ha;
import com.pnfsoftware.jebglobal.bA;
import java.util.List;

class zp extends AbstractCommandHandler {
   zp(Sv var1, ICommandManager var2, String var3, String[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      try {
         Ha var2 = ((Tb)Sv.kS(this.pC)).UT();
         if (!(var2 instanceof bA)) {
            return ExecutionResult.GENERIC_ERROR;
         } else if (var1.isEmpty()) {
            IDalvikDebuggerUnit.ThreadFrameSlotIndexMode var5 = ((Tb)Sv.wS(this.pC)).getThreadFrameSlotIndexMode();
            return ExecutionResult.success("Current mode: " + var5);
         } else {
            IDalvikDebuggerUnit.ThreadFrameSlotIndexMode var3 = IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.valueOf(
               getParameterSafe(var1, 0).trim().toUpperCase()
            );
            ((Tb)Sv.UT(this.pC)).setThreadFrameSlotIndexMode(var3);
            return ExecutionResult.success("New mode: " + var3);
         }
      } catch (Exception var4) {
         Sv.kS.catching(var4);
         return ExecutionResult.error(var4);
      }
   }
}
