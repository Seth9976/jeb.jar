package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class xi {
   public int pC;
   public int A;
   public int kS;
   public int wS;
   public int UT;

   public static xi pC(AN var0) throws IOException {
      xi var1 = new xi();
      var1.pC = var0.wS();
      var1.A = var0.wS();
      var1.kS = var0.wS();
      var1.wS = var0.wS();
      var1.UT = var0.wS();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("f=%d,m=%d,o=%d,rt=%d,fr=%d", this.pC, this.A, this.kS, this.wS, this.UT);
   }
}
