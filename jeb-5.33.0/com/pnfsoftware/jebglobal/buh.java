package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class buh extends btc {
   buh(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      byte[] var3 = (byte[])var1.getArrayObject((IDImm)var2.get(0));
      int var4 = (int)((IDImm)var2.get(1)).toLong();
      int var5 = (int)((IDImm)var2.get(2)).toLong();
      int var6 = (int)((IDImm)var2.get(3)).toLong();
      byte[] var7 = bvw.pC(var3, var4, var5, var6);
      return var1.registerObject(var7);
   }
}
