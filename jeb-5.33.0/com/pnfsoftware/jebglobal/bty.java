package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class bty extends btc {
   bty(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      char[] var3 = (char[])var1.getArrayObject((IDImm)var2.get(0));
      String var4 = String.valueOf(var3);
      return var1.registerObject(var4);
   }
}
