package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

class yR extends hT {
   yR(String var1, String var2, String[] var3, String[] var4, String[] var5, Map var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public String pC(String var1) {
      if (var1.endsWith(".W")) {
         var1 = var1.substring(0, var1.length() - 2);
      }

      for (String var5 : hT.xC) {
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

      if (Strings.isContainedIn(var1, "LDCL", "LDC2L")) {
         var1 = "LDC";
      } else if (Strings.isContainedIn(var1, "STCL", "STC2L")) {
         var1 = "STC";
      } else if (Strings.isContainedIn(var1, "LDRB", "LDRSB", "LDRH", "LDRSH")) {
         var1 = "LDR";
      } else if (Strings.isContainedIn(var1, "STRB", "STRSB", "STRH", "STRSH")) {
         var1 = "STR";
      } else if (Strings.isContainedIn(var1, "ADDW")) {
         var1 = "ADD";
      } else if (Strings.isContainedIn(var1, "SUBW")) {
         var1 = "SUB";
      } else if (Strings.isContainedIn(var1, "MOVW")) {
         var1 = "MOV";
      } else if (var1.startsWith("IT")) {
         var1 = "IT";
      }

      return var1;
   }

   @Override
   public String wS(String var1) {
      if (var1.equals("ORN")) {
         return "--T32-only-";
      } else {
         return var1.startsWith("DCPS") ? "--T32-instruction-" : super.wS(var1);
      }
   }
}
