package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class bub extends btc {
   bub(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      String var3 = var1.getStringObject((IDImm)var2.get(0));
      String var4 = var1.getStringObject((IDImm)var2.get(1));
      String var5 = var1.getStringObject((IDImm)var2.get(2));
      String var6 = var3.replaceAll(var4, var5);
      return var1.registerObject(var6);
   }
}
