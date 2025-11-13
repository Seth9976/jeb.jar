package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class bzi extends bxr {
   bzi(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      Object var3 = var1.getObject((IDImm)var2.get(0));
      if (!(var3 instanceof String)) {
         return null;
      } else {
         int var4 = var3.hashCode();
         return var1.getGlobalContext().createConstant(var4, var1.getGlobalContext().getTypeFactory().getInt());
      }
   }
}
