package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

class ac extends Sx {
   ac(String var1, String var2, String[] var3, String[] var4, String[] var5, Map var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public String Dw(String var1) {
      return Strings.isContainedIn(var1, "FABS") ? "--A64-FP-" : super.Dw(var1);
   }
}
