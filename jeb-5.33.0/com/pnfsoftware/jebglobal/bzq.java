package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

class bzq extends bru {
   bzq(bzp var1, CFG var2, long var3, Collection var5, Map var6) {
      super(var2, var3, var5);
      this.A = var1;
      this.pC = var6;
   }

   @Override
   protected bru.Av pC(BasicBlock var1) {
      bru.Av var2 = bru.Av.pC();
      BasicBlock var3 = (BasicBlock)this.pC.get(var1);
      if (var3 != null) {
         var2.kS = new ArrayList();
         var2.kS.add(var3);
      }

      return var2;
   }
}
