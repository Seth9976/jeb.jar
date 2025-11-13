package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class jG {
   public eO[] pC;

   public static jG pC(AN var0) throws IOException {
      jG var1 = new jG();
      int var2 = var0.wS();
      var1.pC = new eO[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         var1.pC[var3] = eO.pC(var0);
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d methods:", this.pC.length);

      for (eO var5 : this.pC) {
         Strings.ff(var1, "\n- %s", var5);
      }

      return var1.toString();
   }
}
