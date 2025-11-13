package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.List;

class bvl extends btc {
   bvl(String var1) {
      super(var1);
   }

   @Override
   public IDImm simulateExecution(IDState var1, List var2) throws DexDecEvaluationException {
      byte[] var3 = Strings.encodeUTF8(var1.getStringObject((IDImm)var2.get(0)));
      return var1.registerObject(var3);
   }
}
