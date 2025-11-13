package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.AutocompletionResult;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import com.pnfsoftware.jebglobal.Fx;
import com.pnfsoftware.jebglobal.Gl;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.hL;
import java.io.IOException;
import java.util.ArrayList;

class iZ extends CU.eo {
   iZ(CU var1) {
      this.q = var1;
   }

   @Override
   public AutocompletionResult q(String var1, int var2, AbstractCommandHandler var3, InputToken[] var4) throws JebException {
      if (var1.startsWith("c")) {
         return null;
      } else if (var1.startsWith("L")) {
         LC var5 = ((CI)CU.q(this.q)).oW();

         try {
            Gl var6 = ((Ux)var5).zz().za();
            ArrayList var7 = new ArrayList();

            for (hL var11 : var6.q) {
               if (var11.xK.startsWith(var1)) {
                  var7.add(var11.xK);
               }
            }

            return new AutocompletionResult(var7);
         } catch (Fx | IOException var12) {
            return null;
         }
      } else {
         return this.q.Dw.q(var1, var2, var3, var4);
      }
   }
}
