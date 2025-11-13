package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jebglobal.Ha;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.gW;
import java.util.Collection;
import java.util.List;

class KD extends AbstractCommandHandler {
   KD(Sv var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.pC = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      try {
         Ha var2 = ((Tb)Sv.E(this.pC)).UT();
         if (!(var2 instanceof bA)) {
            return ExecutionResult.GENERIC_ERROR;
         } else if (!(var2 instanceof bA)) {
            return ExecutionResult.GENERIC_ERROR;
         } else {
            Collection var3 = ((bA)var2).oT().pC(true);
            StringBuilder var4 = new StringBuilder();
            Strings.ff(var4, S.L("%d classes"), var3.size());

            for (gW var6 : var3) {
               Strings.ff(var4, "\n- %s", var6.toString());
            }

            return ExecutionResult.success(var4.toString());
         }
      } catch (Exception var7) {
         Sv.kS.catching(var7);
         return ExecutionResult.error(var7);
      }
   }
}
