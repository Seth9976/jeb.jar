package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class EZ extends AbstractCommandHandler {
   EZ(if var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      if (!((um)if.oW(this.q)).q()) {
         return ExecutionResult.error("The native process must be suspended first");
      } else if (this.q.oW == null) {
         return ExecutionResult.GENERIC_ERROR;
      } else if (this.q.Dw >= this.q.Uv) {
         this.q.oW = null;
         return ExecutionResult.GENERIC_ERROR;
      } else {
         long var2 = this.q.Uv - this.q.Dw;
         long var4 = ((um)if.gO(this.q)).getMemory().findBytes(this.q.Dw, var2, this.q.oW);
         if (var4 < 0L) {
            this.q.oW = null;
            return ExecutionResult.error("Not found");
         } else {
            long var6 = this.q.Dw + var4;
            this.q.Dw = var6 + 1L;
            return ExecutionResult.success(Strings.ff("Found at %Xh", var6));
         }
      }
   }
}
