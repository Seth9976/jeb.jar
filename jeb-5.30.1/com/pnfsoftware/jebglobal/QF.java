package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class QF {
   public String q;
   public int RF;
   public int xK;
   public String Dw;
   public String Uv;

   public static QF q(Hv var0) throws IOException {
      QF var1 = new QF();
      var1.q = var0.xW();
      var1.RF = var0.Uv();
      var1.xK = var0.Uv();
      var1.Dw = var0.xW();
      var1.Uv = var0.xW();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("JDWP:\"%s\" v%d.%d (VM:%s v%s)", this.q, this.RF, this.xK, this.Uv, this.Dw);
   }
}
