package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

class cej extends bwe {
   cej(cei var1, CFG var2, long var3, Collection var5, Map var6) {
      super(var2, var3, var5);
      this.RF = var1;
      this.q = var6;
   }

   @Override
   protected bwe.eo q(BasicBlock var1) {
      bwe.eo var2 = bwe.eo.q();
      BasicBlock var3 = (BasicBlock)this.q.get(var1);
      if (var3 != null) {
         var2.xK = new ArrayList();
         var2.xK.add(var3);
      }

      return var2;
   }
}
