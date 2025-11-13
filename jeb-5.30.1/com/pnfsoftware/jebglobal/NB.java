package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class NB {
   public Jz[] q;

   public static NB q(Hv var0) throws IOException {
      NB var1 = new NB();
      int var2 = var0.Uv();
      var1.q = new Jz[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         Jz var4 = new Jz(var0.PV(), var0.oQ());
         var1.q[var3] = var4;
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d frames:", this.q.length);

      for (Jz var5 : this.q) {
         Strings.ff(var1, "\n- %s", var5);
      }

      return var1.toString();
   }
}
