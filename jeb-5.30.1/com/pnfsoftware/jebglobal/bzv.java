package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class bzv extends bxr {
   bzv(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      CharSequence var3 = (CharSequence)var1.getObject((IDImm)var2.get(0), true);
      int var4 = var3.length();
      int var5 = 0;

      while (var5 < var4 && var3.charAt(var5) <= ' ') {
         var5++;
      }

      int var6 = var4;

      while (var6 > var5 && var3.charAt(var6 - 1) <= ' ') {
         var6--;
      }

      int var7 = var6 - var5;
      return var1.getGlobalContext().createInt(var7);
   }
}
