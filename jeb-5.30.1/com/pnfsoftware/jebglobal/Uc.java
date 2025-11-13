package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class Uc {
   public int q;
   public long[] RF;

   public static Uc q(Hv var0) throws IOException {
      Uc var1 = new Uc();
      var1.q = var0.Uv();
      var1.RF = new long[var1.q];

      for (int var2 = 0; var2 < var1.q; var2++) {
         var1.RF[var2] = var0.oW();
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d threads: ", this.q);

      for (long var5 : this.RF) {
         Strings.ff(var1, "%X ", var5);
      }

      return var1.toString();
   }
}
