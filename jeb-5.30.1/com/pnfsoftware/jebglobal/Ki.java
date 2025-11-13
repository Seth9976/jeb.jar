package com.pnfsoftware.jebglobal;

import java.io.IOException;

public class Ki {
   public long q;
   public int RF;
   public long[] xK;

   public static Ki q(Hv var0) throws IOException {
      Ki var1 = new Ki();
      var1.q = var0.nf();
      var1.RF = var0.Uv();
      int var2 = var0.Uv();
      var1.xK = new long[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         var1.xK[var3] = var0.nf();
      }

      return var1;
   }
}
