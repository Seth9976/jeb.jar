package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.hL;
import java.util.Collection;
import java.util.List;

class qV extends AbstractCommandHandler {
   qV(CU var1, ICommandManager var2, String var3, String var4) {
      super(var2, var3, var4);
      this.q = var1;
   }

   @Override
   public ExecutionResult execute(List var1) {
      try {
         LC var2 = ((CI)CU.oW(this.q)).oW();
         if (!(var2 instanceof Ux)) {
            return ExecutionResult.GENERIC_ERROR;
         } else if (!(var2 instanceof Ux)) {
            return ExecutionResult.GENERIC_ERROR;
         } else {
            Collection var3 = ((Ux)var2).JY().q(true);
            StringBuilder var4 = new StringBuilder();
            Strings.ff(var4, S.L("%d classes"), var3.size());

            for (hL var6 : var3) {
               Strings.ff(var4, "\n- %s", var6.toString());
            }

            return ExecutionResult.success(var4.toString());
         }
      } catch (Exception var7) {
         CU.xK.catching(var7);
         return ExecutionResult.error(var7);
      }
   }
}
