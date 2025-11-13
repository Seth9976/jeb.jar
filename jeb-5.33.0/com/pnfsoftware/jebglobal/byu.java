package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;

class byu extends bsh {
   byu(byt var1, IDMethodContext var2) {
      super(var2);
      this.wS = var1;
   }

   @Override
   protected boolean pC(IDCallInfo var1) {
      bqh var2 = bqh.pC(var1.getMethodSignature(), this.wS.g);
      return var2 == bqh.kS || var2 == bqh.wS;
   }
}
