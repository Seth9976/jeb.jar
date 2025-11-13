package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import java.util.Comparator;

class qt implements Comparator {
   qt(sy var1) {
      this.pC = var1;
   }

   public int pC(String var1, String var2) {
      if (var1.length() >= 1 && !Character.isLetter(var1.charAt(0))) {
         var1 = var1.substring(1);
      }

      if (var2.length() >= 1 && !Character.isLetter(var2.charAt(0))) {
         var2 = var2.substring(1);
      }

      return var1.compareTo(var2);
   }
}
