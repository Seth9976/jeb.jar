package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.util.format.Strings;

public class Av {
   public static boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      String var1 = var0.wS().E();
      return Strings.isContainedIn(var1, "PACIASP", "PACIAZ", "PACIBSP", "PACIBZ");
   }

   public static boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, com.pnfsoftware.jeb.corei.parsers.arm.rQ var1) {
      String var2 = var1.wS().E();
      String var3 = var0.wS().E();
      switch (var2) {
         case "PACIASP":
            return var3.equals("AUTIASP");
         case "PACIAZ":
            return var3.equals("AUTIAZ");
         case "PACIBSP":
            return var3.equals("AUTIBSP");
         case "PACIBZ":
            return var3.equals("AUTIBZ");
         default:
            throw new IllegalArgumentException("Unimplemented PAC instruction: " + var2);
      }
   }
}
