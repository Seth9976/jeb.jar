package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class bV extends AbstractCommandHandler {
   bV(RS var1, ICommandManager var2, String var3, String[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      Ht var2 = this.q.q.EB();
      String var3 = getParameterSafe(var1, 0);
      long var4 = Conversion.stringToLong(getParameterSafe(var1, 1), -1L);
      boolean var6 = RegisterUtil.setValueByNameAsLong(var2, var3, var4) && this.q.q.q(var2);
      return ExecutionResult.fromBoolean(var6);
   }
}
