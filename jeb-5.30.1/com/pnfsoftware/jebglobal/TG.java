package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class TG {
   public int q;
   public int RF;

   public static TG q(Hv var0) throws IOException {
      TG var1 = new TG();
      var1.q = var0.Uv();
      var1.RF = var0.Uv();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("threadStatus=%s,suspended=%d", jw.q(this.q), this.RF);
   }
}
