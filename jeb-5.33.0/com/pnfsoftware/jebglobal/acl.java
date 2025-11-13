package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import java.util.Map;

public class acl implements aix {
   CFG pC;
   Map A;

   public acl(CFG var1, Map var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public boolean pC(int var1) {
      Integer var2 = (Integer)this.A.get(var1);
      return var2 == null ? false : this.pC.get(var2 - 1).size() == 1;
   }
}
