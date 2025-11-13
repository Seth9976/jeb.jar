package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class buc extends btc {
   buc(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      String var3 = var1.getStringObject((IDImm)var2.get(0));
      int var4 = (int)((IDImm)var2.get(1)).toLong();
      String var5 = var3.substring(var4);
      return var1.registerObject(var5);
   }
}
