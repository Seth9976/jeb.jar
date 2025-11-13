package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

class tM extends Sx {
   tM(String var1, String var2, String[] var3, String[] var4, String[] var5, Map var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public String q(String var1) {
      return var1.startsWith("B.") ? "B-cond" : var1;
   }

   @Override
   public String Dw(String var1) {
      return Strings.isContainedIn(var1, "B-cond", "LDR", "LDRB", "LDRSB", "LDRH", "LDRSH", "LDRSW") ? "" : super.Dw(var1);
   }
}
