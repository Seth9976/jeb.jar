package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class Mw {
   public int pC;
   public long[] A;

   public static Mw pC(AN var0) throws IOException {
      Mw var1 = new Mw();
      var1.pC = var0.wS();
      var1.A = new long[var1.pC];

      for (int var2 = 0; var2 < var1.pC; var2++) {
         var1.A[var2] = var0.UT();
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d threads: ", this.pC);

      for (long var5 : this.A) {
         Strings.ff(var1, "%X ", var5);
      }

      return var1.toString();
   }
}
