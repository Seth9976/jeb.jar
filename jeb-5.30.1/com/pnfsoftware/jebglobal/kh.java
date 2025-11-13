package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class kh extends AbstractCommandHandler {
   kh(RS var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      long var2 = this.q.q(getParameterSafe(var1, 0));
      int var4 = Conversion.stringToInt(getParameterSafe(var1, 1), -1);
      if (var2 >= 0L && var4 >= 0) {
         byte[] var5 = new byte[var4];
         var4 = this.q.q.q(var2, var4, var5, 0);
         return ExecutionResult.success(Strings.ff("Read %Xh bytes: %s", var4, Formatter.formatBinaryLineTruncate(var5, 0, Math.min(var4, var5.length), 256)));
      } else {
         return RS.q("Read", "address size", "0x8080 16");
      }
   }
}
