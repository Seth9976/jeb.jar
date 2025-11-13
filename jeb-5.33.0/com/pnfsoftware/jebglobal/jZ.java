package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class jZ {
   public SL[] pC;

   public static jZ pC(AN var0) throws IOException {
      jZ var1 = new jZ();
      int var2 = var0.wS();
      var1.pC = new SL[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         SL var4 = new SL(var0.fI(), var0.WR());
         var1.pC[var3] = var4;
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d frames:", this.pC.length);

      for (SL var5 : this.pC) {
         Strings.ff(var1, "\n- %s", var5);
      }

      return var1.toString();
   }
}
