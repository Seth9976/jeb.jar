package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

class QR extends Mu {
   QR(String var1) {
      super(var1);
   }

   @Override
   public String pC(String var1) {
      int var2 = var1.indexOf(46);
      if (var2 > 0) {
         var1 = var1.substring(0, var2);
      }

      for (String var6 : Mu.sY) {
         if (var1.endsWith(var6)
            && !Strings.isContainedIn(var1, "VMLS", "VNMLS", "VACLE", "VACLT", "VACGE", "VACGT", "VCEQ", "VCGE", "VCGT", "VCLE", "VCLS", "VCLT")) {
            var1 = var1.substring(0, var1.length() - 2);
            break;
         }
      }

      return var1;
   }
}
