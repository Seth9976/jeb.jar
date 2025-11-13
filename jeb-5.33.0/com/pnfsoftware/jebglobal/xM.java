package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import java.util.List;

class xM extends AbstractCommandHandler {
   xM(vK var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      int var2 = this.pC.pC.os();
      List var3 = this.pC.pC.sO();
      if (var3 == null) {
         return ExecutionResult.GENERIC_ERROR;
      } else {
         StringBuilder var4 = new StringBuilder();

         for (Dn var6 : var3) {
            int var7 = var6.pC() == var2 ? 42 : 45;
            Strings.ff(var4, "%c %x(%d) \"%s\"", Character.valueOf((char)var7), var6.pC(), var6.pC(), Strings.safe(var6.A()));
            Tl var8 = var6.wS();
            if (var8 != null) {
               Long var9 = var8.pC("pc");
               if (var9 != null) {
                  Strings.ff(var4, " @ %Xh", var9);
               }
            }

            String var10 = var6.kS();
            if (var10 != null) {
               Strings.ff(var4, " (%s)", var10);
            }

            var4.append("\n");
         }

         return ExecutionResult.success(var4.toString());
      }
   }
}
