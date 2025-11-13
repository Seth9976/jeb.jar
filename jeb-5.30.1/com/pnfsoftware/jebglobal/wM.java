package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class wM {
   public ih[] q;

   public static wM q(Hv var0) throws IOException {
      wM var1 = new wM();
      int var2 = var0.Uv();
      var1.q = new ih[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         var1.q[var3] = ih.q(var0);
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d methods:", this.q.length);

      for (ih var5 : this.q) {
         Strings.ff(var1, "\n- %s", var5);
      }

      return var1.toString();
   }
}
