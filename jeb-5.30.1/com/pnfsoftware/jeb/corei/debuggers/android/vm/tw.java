package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.AutocompletionResult;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import com.pnfsoftware.jebglobal.LC;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.ih;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

class tw extends CU.eo {
   tw(CU var1) {
      this.q = var1;
   }

   @Override
   public AutocompletionResult q(String var1, int var2, AbstractCommandHandler var3, InputToken[] var4) throws JebException {
      if (var2 != 0) {
         return this.q.Dw.q(var1, 0, var3, var4);
      } else {
         int var6 = var3.getParameterIndex("objectId", null);
         if (var6 < var4.length && var4[var6] != null) {
            int var7 = var3.getParameterIndex("", "t");
            int var8 = var3.getParameterIndex("", "f");
            kY var9 = this.q.q(var7 == -1 ? null : var4[var7]);
            Xa var10 = this.q.q(var9, var8 == -1 ? null : var4[var8]);
            String var11 = var4[var6].getValue();

            try {
               TreeSet var12 = new TreeSet();
               boolean var5 = !var11.startsWith("this") && !var11.startsWith("@");
               long var13 = this.q.q(var11, var9, var10);

               for (LC var15 = ((CI)CU.RF(this.q)).oW(); var13 > 0L; var13 = ((Ux)var15).JY().za(var13)) {
                  for (ih var18 : ((Ux)var15).JY().q(var13, null, null)) {
                     if ((!var5 || var18.q(8)) && var18.HF.startsWith(var1)) {
                        var12.add(var18.HF);
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
