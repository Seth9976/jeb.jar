package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class Nv {
   public long pC;
   public String A;
   public String kS;
   public String wS;
   public int UT;

   public static Nv pC(AN var0) throws IOException {
      Nv var1 = new Nv();
      var1.pC = var0.oT();
      var1.A = var0.NS();
      var1.kS = var0.NS();
      var1.wS = var0.NS();
      var1.UT = var0.wS();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("id=%d,mod=0x%X,name=%s,sig=%s%s", this.pC, this.UT, this.A, this.kS, Strings.isBlank(this.wS) ? "" : ",genSig=" + this.wS);
   }
}
