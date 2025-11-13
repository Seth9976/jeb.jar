package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import java.util.Map;

public class aed implements akz {
   CFG q;
   Map RF;

   public aed(CFG var1, Map var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public boolean q(int var1) {
      Integer var2 = (Integer)this.RF.get(var1);
      return var2 == null ? false : this.q.get(var2 - 1).size() == 1;
   }
}
