package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class ji extends AbstractCommandHandler {
   ji(RS var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      long var2 = this.q.q(getParameterSafe(var1, 0));
      byte[] var4 = Formatter.hexStringToByteArray(getParameterSafe(var1, 1));
      if (var2 >= 0L && var4 != null) {
         int var5 = this.q.q.RF(var2, var4.length, var4, 0);
         return ExecutionResult.success(Strings.ff("Written %Xh bytes: %s", var5, Formatter.formatBinaryLine(var4, 0, Math.min(var5, var4.length), 0)));
      } else {
         return RS.q("Write", "address data", "0x8080 AB123456");
      }
   }
}
