package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class Zg {
   public String pC;
   public int A;
   public int kS;
   public String wS;
   public String UT;

   public static Zg pC(AN var0) throws IOException {
      Zg var1 = new Zg();
      var1.pC = var0.NS();
      var1.A = var0.wS();
      var1.kS = var0.wS();
      var1.wS = var0.NS();
      var1.UT = var0.NS();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("JDWP:\"%s\" v%d.%d (VM:%s v%s)", this.pC, this.A, this.kS, this.UT, this.wS);
   }
}
