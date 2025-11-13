package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.AutocompletionResult;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import com.pnfsoftware.jebglobal.Ha;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.eO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

class rQ extends Sv.Av {
   rQ(Sv var1) {
      this.pC = var1;
   }

   @Override
   public AutocompletionResult pC(String var1, int var2, AbstractCommandHandler var3, InputToken[] var4) throws JebException {
      if (var2 != 0) {
         return this.pC.wS.pC(var1, 0, var3, var4);
      } else {
         int var6 = var3.getParameterIndex("objectId", null);
         if (var6 < var4.length && var4[var6] != null) {
            int var7 = var3.getParameterIndex("", "t");
            int var8 = var3.getParameterIndex("", "f");
            Mh var9 = this.pC.pC(var7 == -1 ? null : var4[var7]);
            uX var10 = this.pC.pC(var9, var8 == -1 ? null : var4[var8]);
            String var11 = var4[var6].getValue();

            try {
               TreeSet var12 = new TreeSet();
               boolean var5 = !var11.startsWith("this") && !var11.startsWith("@");
               long var13 = this.pC.pC(var11, var9, var10);

               for (Ha var15 = ((Tb)Sv.A(this.pC)).UT(); var13 > 0L; var13 = ((bA)var15).oT().sY(var13)) {
                  for (eO var18 : ((bA)var15).oT().pC(var13, null, null)) {
                     if ((!var5 || var18.pC(8)) && var18.A.startsWith(var1)) {
                        var12.add(var18.A);
                     }
                  }
               }

               return new AutocompletionResult(new ArrayList(var12));
            } catch (IOException var19) {
               return null;
            }
         } else {
            return null;
         }
      }
   }
}
