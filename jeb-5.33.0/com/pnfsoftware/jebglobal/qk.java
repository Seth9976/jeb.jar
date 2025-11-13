package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class qk {
   public Nv[] pC;

   public static qk pC(AN var0) throws IOException {
      qk var1 = new qk();
      int var2 = var0.wS();
      var1.pC = new Nv[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         var1.pC[var3] = Nv.pC(var0);
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "%d fields:", this.pC.length);

      for (Nv var5 : this.pC) {
         Strings.ff(var1, "\n- %s", var5);
      }

      return var1.toString();
   }
}
