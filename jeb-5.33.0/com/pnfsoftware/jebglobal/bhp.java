package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import java.util.Map;

public class bhp implements box {
   CFG pC;
   Map A;

   public bhp(CFG var1, Map var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   public bhp(CFG var1) {
      this(var1, null);
   }

   @Override
   public boolean pC(int var1) {
      Integer var2;
      if (this.A == null) {
         var2 = var1;
      } else {
         var2 = (Integer)this.A.get(var1);
         if (var2 == null) {
            return false;
         }
      }

      return this.pC.get(var2 - 1).size() == 1;
   }
}
