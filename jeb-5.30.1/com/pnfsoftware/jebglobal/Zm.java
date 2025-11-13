package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class Zm extends AbstractCommandHandler {
   Zm(RS var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      int var2 = this.q.q.Rr();
      List var3 = this.q.q.TX();
      if (var3 == null) {
         return ExecutionResult.GENERIC_ERROR;
      } else {
         StringBuilder var4 = new StringBuilder();

         for (pr var6 : var3) {
            int var7 = var6.q() == var2 ? 42 : 45;
            Strings.ff(var4, "%c %x(%d) \"%s\"", Character.valueOf((char)var7), var6.q(), var6.q(), Strings.safe(var6.RF()));
            kW var8 = var6.Dw();
            if (var8 != null) {
               Long var9 = var8.q("pc");
               if (var9 != null) {
                  Strings.ff(var4, " @ %Xh", var9);
               }
            }

            String var10 = var6.xK();
            if (var10 != null) {
               Strings.ff(var4, " (%s)", var10);
            }

            var4.append("\n");
         }

         return ExecutionResult.success(var4.toString());
      }
   }
}
