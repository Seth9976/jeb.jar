package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class tI {
   public int q;

   public static tI q(Hv var0) throws IOException {
      tI var1 = new tI();
      var1.q = var0.Uv();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("frameCnt=%d", this.q);
   }
}
