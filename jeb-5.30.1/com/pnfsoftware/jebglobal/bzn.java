package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class bzn extends bxr {
   bzn(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      CharSequence var3 = (CharSequence)var1.getObject((IDImm)var2.get(0), true);
      CharSequence var4 = (CharSequence)var1.getObject((IDImm)var2.get(1), true);
      int var5 = (int)((IDImm)var2.get(2)).toLong();
      int var6 = cam.q(var3, var4, var5);
      return var1.getGlobalContext().createConstant(var6, var1.getGlobalContext().getTypeFactory().getInt());
   }
}
