package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

class Sc extends IB {
   Sc(String var1) {
      super(var1);
   }

   @Override
   public String q(String var1) {
      int var2 = var1.indexOf(46);
      if (var2 > 0) {
         var1 = var1.substring(0, var2);
      }

      for (String var6 : IB.gP) {
         if (var1.endsWith(var6)
            && !Strings.isContainedIn(var1, "VMLS", "VNMLS", "VACLE", "VACLT", "VACGE", "VACGT", "VCEQ", "VCGE", "VCGT", "VCLE", "VCLS", "VCLT")) {
            var1 = var1.substring(0, var1.length() - 2);
            break;
         }
      }

      return var1;
   }
}
