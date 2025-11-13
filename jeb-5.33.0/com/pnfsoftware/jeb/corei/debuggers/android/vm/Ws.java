package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractValueComposite;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import java.util.List;

class Ws extends AbstractCommandHandler {
   Ws(Sv var1, ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6) {
      super(var2, var3, var4, var5, var6);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      try {
         InputToken[] var2 = this.parseInputToken(var1);
         Mh var3 = this.pC.pC(var2[0]);
         uX var4 = this.pC.pC(var3, var2[1]);
         boolean var5 = var2[2] != null;
         String var6 = var2[3].getValue();
         String var7;
         if (Sv.A(var6)) {
            Kr var8 = this.pC.pC(var3, var4, var6, false);
            if (var8.getTypedValue() instanceof AbstractValueComposite) {
               var7 = Strings.ff("%s(composite):\n%s", var8.getName(), ((AbstractValueComposite)var8.getTypedValue()).format());
            } else {
               var7 = var8.getTypedValue().toString();
            }

            if (var5) {
               var7 = this.pC.pC(var7, 2);
            }
         } else {
            ITypedValue var10 = this.pC.UO.A(var6, var3, var4);
            var7 = var10.format();
            if (var5) {
               var7 = this.pC.pC(var7, 1);
            }
         }

         return ExecutionResult.success(var7);
      } catch (Exception var9) {
         Sv.kS.catching(var9);
         return ExecutionResult.error(var9);
      }
   }
}
