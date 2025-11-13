package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import java.util.Comparator;

class Vj implements Comparator {
   Vj(vb var1) {
      this.q = var1;
   }

   public int q(String var1, String var2) {
      if (var1.length() >= 1 && !Character.isLetter(var1.charAt(0))) {
         var1 = var1.substring(1);
      }

      if (var2.length() >= 1 && !Character.isLetter(var2.charAt(0))) {
         var2 = var2.substring(1);
      }

      return var1.compareTo(var2);
   }
}
