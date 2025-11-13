package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class buv extends btc {
   buv(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      CharSequence var3 = (CharSequence)var1.getObject((IDImm)var2.get(0), true);
      char var4 = (char)((IDImm)var2.get(1)).toLong();
      int var5 = (int)((IDImm)var2.get(2)).toLong();
      int var6 = bvx.A(var3, var4, var5);
      return var1.getGlobalContext().createConstant(var6, var1.getGlobalContext().getTypeFactory().getInt());
   }
}
