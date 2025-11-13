package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class rg {
   public byte q;
   public long RF;

   public static rg q(Hv var0) throws IOException {
      rg var1 = new rg();
      var1.q = var0.RF();
      var1.RF = var0.HF();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("tag=%d,typeId=%d", this.q, this.RF);
   }
}
