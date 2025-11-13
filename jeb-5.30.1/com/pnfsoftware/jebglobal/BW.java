package com.pnfsoftware.jebglobal;

import java.io.IOException;

public class BW {
   public long[] q;

   public static BW q(Hv var0) throws IOException {
      BW var1 = new BW();
      int var2 = var0.Uv();
      var1.q = new long[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         var1.q[var3] = var0.gP();
      }

      return var1;
   }
}
