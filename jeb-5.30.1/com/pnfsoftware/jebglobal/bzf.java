package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.List;

class bzf extends bxr {
   bzf(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      byte[] var3 = (byte[])var1.getArrayObject((IDImm)var2.get(0));
      int var4 = (int)((IDImm)var2.get(1)).toLong();
      int var5 = (int)((IDImm)var2.get(2)).toLong();
      return var1.registerObject(Strings.decodeUTF8(var3, var4, var5));
   }
}
