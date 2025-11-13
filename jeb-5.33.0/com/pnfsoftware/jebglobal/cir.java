package com.pnfsoftware.jebglobal;

public class cir {
   public static cip pC(String var0) {
      if (var0 == null) {
         return null;
      } else {
         String var1 = var0.toLowerCase();
         switch (var1) {
            case "ws2_32.dll":
               return ciu.pC();
            case "shell32.dll":
               return cis.pC();
            case "shlwapi.dll":
               return cit.pC();
            case "oleaut32.dll":
               return ciq.pC();
            default:
               return null;
         }
      }
   }
}
