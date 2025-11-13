package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class lS {
   public int pC;
   public int A;

   public static lS pC(AN var0) throws IOException {
      lS var1 = new lS();
      var1.pC = var0.wS();
      var1.A = var0.wS();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("threadStatus=%s,suspended=%d", CI.pC(this.pC), this.A);
   }
}
