package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class Gl {
   public hL[] q;

   public static Gl q(Hv var0) throws IOException {
      Gl var1 = new Gl();
      int var2 = var0.Uv();
      var1.q = new hL[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         var1.q[var3] = hL.q(var0);
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d classes", this.q.length);

      for (hL var5 : this.q) {
         Strings.ff(var1, "\n- %s", var5.toString());
      }

      return var1.toString();
   }
}
