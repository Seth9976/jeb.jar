package com.pnfsoftware.jebglobal;

public class cpz {
   public static cpx q(String var0) {
      if (var0 == null) {
         return null;
      } else {
         String var1 = var0.toLowerCase();
         switch (var1) {
            case "ws2_32.dll":
               return cqc.q();
            case "shell32.dll":
               return cqa.q();
            case "shlwapi.dll":
               return cqb.q();
            case "oleaut32.dll":
               return cpy.q();
            default:
               return null;
         }
      }
   }
}
