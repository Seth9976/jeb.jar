package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

class Hj implements oi {
   Hj(aI var1) {
      this.pC = var1;
   }

   @Override
   public String pC(String var1) throws IOException, Ae {
      int var2 = Integer.parseInt(var1);
      return this.pC.kS(Strings.ff("qRegisterInfo%x", var2));
   }
}
