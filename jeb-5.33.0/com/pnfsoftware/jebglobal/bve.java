package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class bve extends btc {
   bve(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      CharSequence var3 = (CharSequence)var1.getObject((IDImm)var2.get(0), true);
      boolean var4 = var3 == null || var3.length() == 0;
      return var1.getGlobalContext().createImm(var4 ? 1L : 0L, var1.getGlobalContext().getTypeFactory().getBoolean());
   }
}
