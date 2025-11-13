package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class bvf extends btc {
   bvf(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      CharSequence var3 = (CharSequence)var1.getObject((IDImm)var2.get(0), true);
      CharSequence var4 = (CharSequence)var1.getObject((IDImm)var2.get(1), true);
      boolean var5 = false;
      if (var3 == var4) {
         var5 = true;
      } else {
         int var6;
         if (var3 != null && var4 != null && (var6 = var3.length()) == var4.length()) {
            if (var3 instanceof String && var4 instanceof String) {
               var5 = var3.equals(var4);
            } else {
               var5 = true;

               for (int var7 = 0; var7 < var6; var7++) {
                  if (var3.charAt(var7) != var4.charAt(var7)) {
                     var5 = false;
                     break;
                  }
               }
            }
         }
      }

      return var1.getGlobalContext().createImm(var5 ? 1L : 0L, var1.getGlobalContext().getTypeFactory().getBoolean());
   }
}
