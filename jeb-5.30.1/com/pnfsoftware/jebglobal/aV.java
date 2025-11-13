package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

class aV implements nO {
   aV(oH var1) {
      this.q = var1;
   }

   @Override
   public String q(String var1) throws IOException, WI {
      int var2 = Integer.parseInt(var1);
      return this.q.xK(Strings.ff("qRegisterInfo%x", var2));
   }
}
