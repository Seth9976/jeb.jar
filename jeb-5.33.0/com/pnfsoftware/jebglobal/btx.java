package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class btx extends btc {
   btx(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      char[] var3 = (char[])var1.getArrayObject((IDImm)var2.get(0));
      int var4 = (int)((IDImm)var2.get(1)).toLong();
      int var5 = (int)((IDImm)var2.get(2)).toLong();
      String var6 = String.valueOf(var3, var4, var5);
      return var1.registerObject(var6);
   }
}
