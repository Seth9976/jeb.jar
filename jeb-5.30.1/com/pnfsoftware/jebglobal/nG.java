package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class nG {
   public int q;
   public int RF;
   public int xK;
   public int Dw;
   public int Uv;

   public static nG q(Hv var0) throws IOException {
      nG var1 = new nG();
      var1.q = var0.Uv();
      var1.RF = var0.Uv();
      var1.xK = var0.Uv();
      var1.Dw = var0.Uv();
      var1.Uv = var0.Uv();
      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("f=%d,m=%d,o=%d,rt=%d,fr=%d", this.q, this.RF, this.xK, this.Dw, this.Uv);
   }
}
