package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewInfo;

class bzk extends bsh {
   bzk(bzj var1, IDMethodContext var2, int var3) {
      super(var2, var3);
      this.wS = var1;
   }

   @Override
   protected boolean pC(IDCallInfo var1) {
      bqh var2 = bqh.pC(var1.getMethodSignature(), this.wS.g);
      if (var2 == bqh.pC) {
         return false;
      } else {
         return var1.getInvokeType() != DInvokeType.STATIC ? false : !(var1 instanceof IDNewInfo);
      }
   }
}
