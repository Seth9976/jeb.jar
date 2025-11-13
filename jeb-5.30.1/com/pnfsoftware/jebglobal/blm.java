package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import java.util.Map;

public class blm implements bsz {
   CFG q;
   Map RF;

   public blm(CFG var1, Map var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   public blm(CFG var1) {
      this(var1, null);
   }

   @Override
   public boolean q(int var1) {
      Integer var2;
      if (this.RF == null) {
         var2 = var1;
      } else {
         var2 = (Integer)this.RF.get(var1);
         if (var2 == null) {
            return false;
         }
      }

      return this.q.get(var2 - 1).size() == 1;
   }
}
