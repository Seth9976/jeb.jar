package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class im {
   public gW[] pC;

   public static im pC(AN var0) throws IOException {
      im var1 = new im();
      int var2 = var0.wS();
      var1.pC = new gW[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         var1.pC[var3] = gW.pC(var0);
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d classes", this.pC.length);

      for (gW var5 : this.pC) {
         Strings.ff(var1, "\n- %s", var5.toString());
      }

      return var1.toString();
   }
}
