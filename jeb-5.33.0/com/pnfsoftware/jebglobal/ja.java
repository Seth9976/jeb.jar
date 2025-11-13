package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import java.util.List;

class ja extends AbstractCommandHandler {
   ja(ub var1, ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      InputToken[] var2;
      try {
         var2 = this.parseInputToken(var1);
      } catch (Exception var7) {
         ub.kS.catching(var7);
         return ExecutionResult.error(var7);
      }

      long var3 = this.pC.pC(var2[0].getValue());
      byte[] var5 = Formatter.hexStringToByteArray(var2[1].getValue());
      if (var3 >= 0L && var5 != null) {
         int var6 = ((ia)ub.kS(this.pC)).writeMemory(var3, var5.length, var5, 0);
         return var6 < 0 ? ExecutionResult.GENERIC_ERROR : ExecutionResult.success(Strings.ff("Written %Xh bytes: %s", var6, Formatter.formatBinaryLine(var5)));
      } else {
         return ExecutionResult.GENERIC_ERROR;
      }
   }
}
