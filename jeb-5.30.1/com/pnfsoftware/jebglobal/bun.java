package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;

public enum bun {
   q,
   RF,
   xK,
   Dw;

   public boolean q() {
      return this == q;
   }

   public static bun q(IDexMethod var0, IDGlobalContext var1) {
      return q(var0.getSignature(false), var1);
   }

   public static bun q(String var0, IDGlobalContext var1) {
      if (var0 == null) {
         return q;
      } else {
         if (var1 != null) {
            IDexMethod var2 = var1.getDex().getMethod(var0);
            if (var2 == null || var2.getData() == null) {
               return q;
            }

            int var3 = var2.getData().getInlineMode();
            switch (var3) {
               case 0:
                  return RF;
               case 1:
                  return q;
               case 2:
                  return xK;
               case 3:
                  return Dw;
            }
         }

         return RF;
      }
   }
}
