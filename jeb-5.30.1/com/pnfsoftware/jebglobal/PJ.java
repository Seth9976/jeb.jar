package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

class PJ extends IB {
   PJ(String var1) {
      super(var1);
   }

   @Override
   public String q(String var1) {
      if (var1.endsWith(".W")) {
         var1 = var1.substring(0, var1.length() - 2);
      }

      for (String var5 : IB.gP) {
         if (var1.endsWith(var5)
            && !Strings.isContainedIn(
               var1, "TEQ", "ADCS", "BICS", "RSCS", "SBCS", "MOVS", "HVC", "SVC", "LSLS", "MLS", "MULS", "SMLALS", "SMMLS", "SMULLS", "UMLALS", "UMULLS", "HLT"
            )) {
            var1 = var1.substring(0, var1.length() - 2);
            break;
         }
      }

      if (var1.endsWith("S") && !Strings.isContainedIn(var1, "CPS", "MLS", "MRS", "SMMLS", "SRS", "SYS")) {
         var1 = var1.substring(0, var1.length() - 1);
      }

      return var1;
   }
}
