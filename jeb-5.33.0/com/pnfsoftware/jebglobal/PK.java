package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

class PK extends hT {
   PK(String var1, String var2, String[] var3, String[] var4, String[] var5, Map var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public String pC(String var1) {
      int var2 = var1.indexOf(46);
      if (var2 > 0) {
         var1 = var1.substring(0, var2);
      }

      for (String var6 : hT.xC) {
         if (var1.endsWith(var6) && !Strings.isContainedIn(var1, "VMLS", "VNMLS")) {
            var1 = var1.substring(0, var1.length() - 2);
            break;
         }
      }

      return var1;
   }
}
