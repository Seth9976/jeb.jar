package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.AutocompletionResult;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import com.pnfsoftware.jebglobal.Ha;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.gW;
import com.pnfsoftware.jebglobal.im;
import com.pnfsoftware.jebglobal.oY;
import java.io.IOException;
import java.util.ArrayList;

class DH extends Sv.Av {
   DH(Sv var1) {
      this.pC = var1;
   }

   @Override
   public AutocompletionResult pC(String var1, int var2, AbstractCommandHandler var3, InputToken[] var4) throws JebException {
      if (var1.startsWith("c")) {
         return null;
      } else if (var1.startsWith("L")) {
         Ha var5 = ((Tb)Sv.pC(this.pC)).UT();

         try {
            im var6 = ((bA)var5).gp().ys();
            ArrayList var7 = new ArrayList();

            for (gW var11 : var6.pC) {
               if (var11.kS.startsWith(var1)) {
                  var7.add(var11.kS);
               }
            }

            return new AutocompletionResult(var7);
         } catch (oY | IOException var12) {
            return null;
         }
      } else {
         return this.pC.wS.pC(var1, var2, var3, var4);
      }
   }
}
