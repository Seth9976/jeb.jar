package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class fd extends AbstractCommandHandler {
   fd(RS var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      long var2 = this.q.q(getParameter(var1, 0));
      int var4 = Conversion.stringToInt(getParameterSafe(var1, 1), -1);
      if (var2 < 0L || var4 < 0) {
         return RS.q("Breakpoint", "address type", "0x8080 0");
      } else {
         return !this.q.q.q(var2, var4) ? ExecutionResult.GENERIC_ERROR : ExecutionResult.GENERIC_SUCCESS;
      }
   }
}
