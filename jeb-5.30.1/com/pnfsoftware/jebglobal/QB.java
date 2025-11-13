package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class QB {
   public hw[] q;

   public static QB q(Hv var0) throws IOException {
      QB var1 = new QB();
      int var2 = var0.Uv();
      var1.q = new hw[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         var1.q[var3] = hw.q(var0);
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d fields:", this.q.length);

      for (hw var5 : this.q) {
         Strings.ff(var1, "\n- %s", var5);
      }

      return var1.toString();
   }
}
