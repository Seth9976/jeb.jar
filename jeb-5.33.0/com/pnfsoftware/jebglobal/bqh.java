package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;

public enum bqh {
   pC,
   A,
   kS,
   wS;

   public boolean pC() {
      return this == pC;
   }

   public static bqh pC(IDexMethod var0, IDGlobalContext var1) {
      return pC(var0.getSignature(false), var1);
   }

   public static bqh pC(String var0, IDGlobalContext var1) {
      if (var0 == null) {
         return pC;
      } else {
         if (var1 != null) {
            IDexMethod var2 = var1.getDex().getMethod(var0);
            if (var2 == null || var2.getData() == null) {
               return pC;
            }

            int var3 = var2.getData().getInlineMode();
            switch (var3) {
               case 0:
                  return A;
               case 1:
                  return pC;
               case 2:
                  return kS;
               case 3:
                  return wS;
            }
         }

         return A;
      }
   }
}
