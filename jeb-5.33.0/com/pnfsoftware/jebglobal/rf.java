package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

class rf extends hT {
   rf(String var1, String var2, String[] var3, String[] var4, String[] var5, Map var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public String wS(String var1) {
      return Strings.isContainedIn(var1, "FABS") ? "--A64-FP-" : super.wS(var1);
   }
}
