package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class bua extends btc {
   bua(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      String var3 = var1.getStringObject((IDImm)var2.get(0));
      char var4 = (char)((IDImm)var2.get(1)).toLong();
      char var5 = (char)((IDImm)var2.get(2)).toLong();
      String var6 = var3.replace(var4, var5);
      return var1.registerObject(var6);
   }
}
