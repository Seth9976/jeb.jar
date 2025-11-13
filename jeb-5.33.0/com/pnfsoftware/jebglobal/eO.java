package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class eO {
   public long pC;
   public String A;
   public String kS;
   public String wS;
   public int UT;

   public static eO pC(AN var0) throws IOException {
      eO var1 = new eO();
      var1.pC = var0.gp();
      var1.A = var0.NS();
      var1.kS = var0.NS();
      var1.wS = var0.NS();
      var1.UT = var0.wS();
      return var1;
   }

   public boolean pC(int var1) {
      return (this.UT & var1) != 0;
   }

   @Override
   public String toString() {
      return Strings.ff("id=%d,mod=0x%X,name=%s,sig=%s%s", this.pC, this.UT, this.A, this.kS, Strings.isBlank(this.wS) ? "" : ",genSig=" + this.wS);
   }
}
