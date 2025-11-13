package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class Oh extends AbstractCommandHandler {
   Oh(vK var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      long var2 = this.pC.pC(getParameter(var1, 0));
      int var4 = Conversion.stringToInt(getParameterSafe(var1, 1), -1);
      if (var2 < 0L || var4 < 0) {
         return vK.pC("Breakpoint", "address type", "0x8080 0");
      } else {
         return !this.pC.pC.pC(var2, var4) ? ExecutionResult.GENERIC_ERROR : ExecutionResult.GENERIC_SUCCESS;
      }
   }
}
