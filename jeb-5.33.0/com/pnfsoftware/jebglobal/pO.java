package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class pO extends AbstractCommandHandler {
   pO(vK var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      String var2 = getParameter(var1, 0);
      if (var2 != null) {
         int var5 = Conversion.stringToInt(var2);
         if (var5 == 0) {
            return ExecutionResult.GENERIC_ERROR;
         } else {
            boolean var4 = this.pC.pC.A(var5);
            return !var4 ? ExecutionResult.GENERIC_ERROR : ExecutionResult.success(Strings.ff("Default thread set to %Xh", var5));
         }
      } else {
         int var3 = this.pC.pC.os();
         return var3 <= 0 ? ExecutionResult.GENERIC_ERROR : ExecutionResult.success(Strings.ff("Current thread is %Xh", var3));
      }
   }
}
