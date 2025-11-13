package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import java.util.List;

class bzg extends bxr {
   bzg(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      int var3 = 3;
      var3 += var1.getStringObject((IDImm)var2.get(0)).length();
      var3 += var1.getStringObject((IDImm)var2.get(1)).length();
      return var1.getGlobalContext().createConstant(var3, var1.getGlobalContext().getTypeFactory().getInt());
   }
}
