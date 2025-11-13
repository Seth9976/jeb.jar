package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class buw {
   public static List q(IDInstruction var0) {
      return var0.getUsedVariables();
   }

   public static List RF(IDInstruction var0) {
      List var1 = var0.getUsedVariables();
      Iterator var2 = var1.iterator();

      while (var2.hasNext()) {
         IDVar var3 = (IDVar)var2.next();
         if (var3.getType() != null && var3.getType().isObject()) {
            var2.remove();
         }
      }

      return var1;
   }

   public static List xK(IDInstruction var0) {
      List var1 = var0.getUsedVariables();
      ArrayList var2 = new ArrayList();
      var0.visitDepthPost(new bux(var2));
      var1.removeAll(var2);
      return var1;
   }
}
